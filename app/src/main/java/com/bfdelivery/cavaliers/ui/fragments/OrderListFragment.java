package com.bfdelivery.cavaliers.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.server.bean.response.OrderList;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.background.server.request.DistributeService;
import com.bfdelivery.cavaliers.constant.DeliveryStatus;
import com.bfdelivery.cavaliers.dataset.ListOutlineData;
import com.bfdelivery.cavaliers.ui.activities.OrderDetailActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 订单页面
 */

public class OrderListFragment extends Fragment {

	RecyclerView mListOrder = null;
	OrderAdapter mOrderAdapter = null;
	ContentLoadingProgressBar mWaitingBar = null;

	DistributeService mService;

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
		requestOrder(1);
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

		mOrderAdapter = new OrderAdapter();
		mListOrder.setAdapter(mOrderAdapter);
	}

	private static final class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		OrderList.DataBean mData;
		int mIndex = 0;

		TextView mTxtIndex;

		TextView mTxtRstAddr;
		TextView mTxtRstName;
		TextView mTxtRstDis;

		TextView mTxtUsrAddr;
		TextView mTxtUsrName;
		TextView mTxtUsrDis;

		public OrderViewHolder(View itemView) {
			super(itemView);
			initViewHolder(itemView);
		}

		private void initViewHolder(View itemView) {
			mTxtIndex = (TextView) itemView.findViewById(R.id.txtOrderIndex);

			mTxtRstName = (TextView) itemView.findViewById(R.id.txtRstrtName);
			mTxtRstAddr = (TextView) itemView.findViewById(R.id.txtRstrtAddr);
			mTxtRstDis = (TextView) itemView.findViewById(R.id.txtRstrtDis);

			mTxtUsrAddr = (TextView) itemView.findViewById(R.id.txtUsrAddress);
			mTxtUsrName = (TextView) itemView.findViewById(R.id.txtUsrName);
			mTxtUsrDis = (TextView) itemView.findViewById(R.id.txtUsrDis);

			itemView.setOnClickListener(this);
		}

		private void bindData(int position, OrderList.DataBean data) {
			mIndex = position;
			mData = data;

			mTxtIndex.setText("#" + (position + 1));
			mTxtUsrAddr.setText(data.getAddress().getDetail());
			mTxtUsrName.setText(data.getAddress().getName());
		}

		@Override
		public void onClick(View v) {
			if (v == itemView) {
				ListOutlineData outlineData = new ListOutlineData();
//				DataBridge.dataBeanToListOutLine(mData, outlineData);

				Intent intent = new Intent(itemView.getContext(), OrderDetailActivity.class);
				intent.putExtra(OrderDetailActivity.BUNDLE_KEY_POSTION, mIndex);
				intent.putExtra(OrderDetailActivity.BUNDLE_KEY_LISTDATA, outlineData);
				itemView.getContext().startActivity(intent);
			}
		}
	}

	private static final class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

		List<OrderList.DataBean> mOrderList = new ArrayList<>();

		public OrderAdapter() {
		}

		public OrderAdapter(List<OrderList.DataBean> orderList) {
			mOrderList.addAll(orderList);
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

			return new OrderViewHolder(itemView);
		}

		@Override
		public void onBindViewHolder(OrderViewHolder holder, int position) {
//			holder.bindData(position, mOrderList.get(position));
		}

		@Override
		public int getItemCount() {
//			return mOrderList.size();
			return 5;
		}
	}
}
