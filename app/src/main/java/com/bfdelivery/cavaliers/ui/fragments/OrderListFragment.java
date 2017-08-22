package com.bfdelivery.cavaliers.ui.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.server.bean.request.OrderNumber;
import com.bfdelivery.cavaliers.background.server.bean.response.OrderList;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.background.server.request.DistributeService;
import com.bfdelivery.cavaliers.constant.DeliveryStatus;
import com.bfdelivery.cavaliers.database.location.LocationData;
import com.bfdelivery.cavaliers.dataset.ListOutlineData;
import com.bfdelivery.cavaliers.ui.activities.OrderDetailActivity;
import com.bfdelivery.cavaliers.util.DataBridge;
import com.bfdelivery.cavaliers.util.DistanceUtil;
import com.bfdelivery.cavaliers.util.LocationSaver;
import com.bfdelivery.cavaliers.util.OrderStringBridge;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 订单页面
 */

public class OrderListFragment extends Fragment implements OnListItemListener {

	RecyclerView mListOrder = null;
	OrderAdapter mOrderAdapter = null;
	ContentLoadingProgressBar mWaitingBar = null;

	DistributeService mService;

	private int mListPage = 1;

	private int mOrderType = DeliveryStatus.NEW_RECEIVED;

	public static final String BUNDLE_KEY_ORDERTYPE = "order.type";

	public static OrderListFragment newIntance(int orderType) {
		OrderListFragment fragment = new OrderListFragment();

		Bundle data = new Bundle();
		data.putInt(BUNDLE_KEY_ORDERTYPE, orderType);
		fragment.setArguments(data);

		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_order, container, false);
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		handleData(getArguments());
		initView(view);
		requestOrder(mListPage);
	}

	private void handleData(Bundle data) {
		mOrderType = data.getInt(BUNDLE_KEY_ORDERTYPE, DeliveryStatus.NEW_RECEIVED);
	}

	private void requestOrder(int page) {
		mService = CavV1Service.createDistributeService();
		final Call<OrderList> request = mService.listOrders(page, mOrderType);
		request.enqueue(new BaseCallback<OrderList>() {
			@Override
			public void onFailure(Call<OrderList> call, Throwable t) {
				super.onFailure(call, t);
			}

			@Override
			public void onResponse(Call<OrderList> call, Response<OrderList> response) {
				super.onResponse(call, response);
				if (response.code() == HttpStatus.SC_OK) {
					mWaitingBar.hide();
					mOrderAdapter.refreshData(response.body().getData());
					mListOrder.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onComplete() {
				mWaitingBar.setVisibility(View.GONE);
			}
		});
		mWaitingBar.setVisibility(View.VISIBLE);
	}

	private void initView(View view) {
		mListOrder = (RecyclerView) view.findViewById(R.id.list_order);
		mWaitingBar = (ContentLoadingProgressBar) view.findViewById(R.id.waitingbar);

		mListOrder.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
		mListOrder.setHasFixedSize(true);

		mOrderAdapter = new OrderAdapter(this);
		mListOrder.setAdapter(mOrderAdapter);
	}

	@Override
	public void onDetail(int position, OrderList.DataBean dataBean) {
		ListOutlineData outlineData = new ListOutlineData();
		DataBridge.dataBeanToListOutLine(dataBean, outlineData);

		Intent intent = new Intent(getContext(), OrderDetailActivity.class);
		intent.putExtra(OrderDetailActivity.BUNDLE_KEY_POSTION, position);
		intent.putExtra(OrderDetailActivity.BUNDLE_KEY_LISTDATA, outlineData);
		startActivity(intent);
	}

	@Override
	public void onAction(int position, OrderList.DataBean dataBean) {
		if (dataBean.getDistribute().getStatus() == DeliveryStatus.NEW_RECEIVED) {
			acceptOrder(dataBean);
		}
	}

	private void acceptOrder(final OrderList.DataBean dataBean) {

		new AlertDialog.Builder(getContext()).setMessage(R.string.tip_receive_order).setPositiveButton(R.string.btn_sure_take, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				acceptOrderInner(dataBean.getNumber());
			}

		}).setNegativeButton(R.string.btn_not_sure, null).show();

	}

	private void acceptOrderInner(String orderNumber) {
		Call<Void> request = null;
		OrderNumber postParam = new OrderNumber(orderNumber);
		request = mService.acceptOrder(postParam);
		request.enqueue(new BaseCallback<Void>() {
			@Override
			public void onResponse(Call<Void> call, Response<Void> response) {
				super.onResponse(call, response);
				if (response.code() == HttpStatus.SC_OK) {
//					requestOrderDetial();
				}
			}

			@Override
			public void onComplete() {

			}
		});
	}

	private static final class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		OrderList.DataBean mData;
		int mIndex = 0;
		OnListItemListener mOnListItemListener;

		TextView mTxtIndex;

		TextView mTxtRstAddr;
		TextView mTxtRstName;
		TextView mTxtRstDis;

		TextView mTxtUsrAddr;
		TextView mTxtUsrName;
		TextView mTxtUsrDis;

		View mActionWrapper;
		TextView mButtonAction;

		public OrderViewHolder(View itemView, OnListItemListener listItemListener) {
			super(itemView);
			initViewHolder(itemView);
			mOnListItemListener = listItemListener;
		}

		private void initViewHolder(View itemView) {
			mTxtIndex = (TextView) itemView.findViewById(R.id.txtOrderIndex);

			mTxtRstName = (TextView) itemView.findViewById(R.id.txtRstrtName);
			mTxtRstAddr = (TextView) itemView.findViewById(R.id.txtRstrtAddr);
			mTxtRstDis = (TextView) itemView.findViewById(R.id.txtRstrtDis);

			mTxtUsrAddr = (TextView) itemView.findViewById(R.id.txtUsrAddress);
			mTxtUsrName = (TextView) itemView.findViewById(R.id.txtUsrName);
			mTxtUsrDis = (TextView) itemView.findViewById(R.id.txtUsrDis);

			mActionWrapper = itemView.findViewById(R.id.wrapper_button);
			mButtonAction = (TextView) itemView.findViewById(R.id.btnAction);

			itemView.setOnClickListener(this);
			mButtonAction.setOnClickListener(this);
		}

		private void bindData(int position, OrderList.DataBean data) {
			mIndex = position;
			mData = data;

			mTxtIndex.setText(itemView.getContext().getString(R.string.pre_pos, position + 1));
			mTxtUsrAddr.setText(data.getAddress().getDetail());
			mTxtUsrName.setText(data.getAddress().getName());

			mTxtRstName.setText(data.getShop().getName());
			mTxtRstAddr.setText(data.getShop().getAddress());

			LocationData location = LocationSaver.instance().getLocation();
			if (location != null) {
				float[] distance = new float[1];
				Location.distanceBetween(location.getLatitude(), location.getLongitude(), data.getShop().getLatitude(), data.getShop().getLongitude(), distance);
				mTxtRstDis.setText(DistanceUtil.formatDistance(itemView.getContext(), distance[0]));
				Location.distanceBetween(location.getLatitude(), location.getLongitude(), data.getAddress().getLatitude(), data.getAddress().getLongitude(), distance);
				mTxtUsrDis.setText(DistanceUtil.formatDistance(itemView.getContext(), distance[0]));
			}

			mActionWrapper.setVisibility(View.VISIBLE);

			int resID = OrderStringBridge.getActionByOrderStatu(data.getDistribute().getStatus());

			if (resID > 0 && data.getDistribute().getStatus() == DeliveryStatus.NEW_RECEIVED) {
				mButtonAction.setText(resID);
			} else {
				mActionWrapper.setVisibility(View.GONE);
			}
		}

		@Override
		public void onClick(View v) {
			if (v == itemView) {
				if (mOnListItemListener != null) {
					mOnListItemListener.onDetail(mIndex, mData);
				}
			} else if (v == mButtonAction) {
				if (mOnListItemListener != null) {
					mOnListItemListener.onAction(mIndex, mData);
				}
			}
		}
	}

	private static final class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

		List<OrderList.DataBean> mOrderList = new ArrayList<>();

		OnListItemListener mOnListItemListener = null;

		public OrderAdapter(OnListItemListener onListItemListener) {
			mOnListItemListener = onListItemListener;
		}

		public OrderAdapter(List<OrderList.DataBean> orderList, OnListItemListener onListItemListener) {
			mOrderList.addAll(orderList);
			mOnListItemListener = onListItemListener;
		}

		public void refreshData(List<OrderList.DataBean> orderLists) {
			mOrderList.clear();
			mOrderList.addAll(orderLists);
			notifyDataSetChanged();
		}

		public void appendData(List<OrderList.DataBean> orderLists) {
			mOrderList.addAll(orderLists);
			notifyDataSetChanged();
		}

		@Override
		public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

			View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_item, parent, false);

			return new OrderViewHolder(itemView, mOnListItemListener);
		}

		@Override
		public void onBindViewHolder(OrderViewHolder holder, int position) {
			holder.bindData(position, mOrderList.get(position));
		}

		@Override
		public int getItemCount() {
			return mOrderList.size();
		}
	}
}
