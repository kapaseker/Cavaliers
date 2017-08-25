package com.bfdelivery.cavaliers.ui.fragments;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.bfdelivery.cavaliers.ui.fragments.base.BaseFragment;
import com.bfdelivery.cavaliers.ui.views.EndlessRecyclerViewScrollListener;
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

public class OrderListFragment extends BaseFragment implements OnListItemListener, SwipeRefreshLayout.OnRefreshListener {

	SwipeRefreshLayout mRefreshLayout = null;
	RecyclerView mListOrder = null;
	OrderAdapter mOrderAdapter = null;
//	ContentLoadingProgressBar mWaitingBar = null;

	DistributeService mService;

	EndlessRecyclerViewScrollListener mScrollListener;

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

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handleData(getArguments());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mListOrder.removeOnScrollListener(mScrollListener);
	}

	@Override
	protected void initializeView(View rootView, Bundle savedInstanceState) {
		initView(rootView);
		requestOrder();
	}

	@Override
	public int getLayoutResource() {
		return R.layout.fragment_order;
	}

	private void handleData(Bundle data) {
		mOrderType = data.getInt(BUNDLE_KEY_ORDERTYPE, DeliveryStatus.NEW_RECEIVED);
	}

	private void requestOrder() {
		mRefreshLayout.setRefreshing(true);
		mListPage = 1;
		requestOrderInner(mListPage, false);
	}

	private void requestOrderInner(int page, final boolean append) {
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

					if (append) {
						mOrderAdapter.appendData(response.body().getData());
					} else {
						mOrderAdapter.refreshData(response.body().getData());
					}

					mListOrder.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onComplete() {
				mRefreshLayout.setRefreshing(false);
			}
		});
	}

	private void initView(View view) {
		mListOrder = (RecyclerView) view.findViewById(R.id.list_order);
		mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
//		mWaitingBar = (ContentLoadingProgressBar) view.findViewById(R.id.waitingbar);
		LinearLayoutManager layoutManger = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
		mListOrder.setLayoutManager(layoutManger);
		mListOrder.setHasFixedSize(true);

		mOrderAdapter = new OrderAdapter(this);
		mListOrder.setAdapter(mOrderAdapter);

		mRefreshLayout.setOnRefreshListener(this);
		mScrollListener = new EndlessRecyclerViewScrollListener(layoutManger) {
			@Override
			public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
				requestOrderInner(++mListPage, true);
			}
		};
		mListOrder.addOnScrollListener(mScrollListener);
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
		acceptOrderInner(dataBean.getNumber());
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
					Toast.makeText(getContext(), R.string.receive_order_succeed, Toast.LENGTH_SHORT).show();
					requestOrder();
				} else {
					onFailure(call, null);
				}
			}

			@Override
			public void onFailure(Call<Void> call, Throwable t) {
				super.onFailure(call, t);
				Toast.makeText(getContext(), R.string.receive_order_failed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete() {

			}
		});
	}

	@Override
	public void onRefresh() {
		mListPage = 1;
		requestOrderInner(mListPage, false);
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

			mTxtUsrAddr = (TextView) itemView.findViewById(R.id.txtUsrAddr);
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
			int orignStart = getItemCount();
			mOrderList.addAll(orderLists);
			notifyItemRangeInserted(orignStart, orderLists.size());
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
