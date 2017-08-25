package com.bfdelivery.cavaliers.ui.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.server.bean.request.OrderNumber;
import com.bfdelivery.cavaliers.background.server.bean.response.OrderDetail;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.background.server.request.DistributeService;
import com.bfdelivery.cavaliers.config.LocationErrorCode;
import com.bfdelivery.cavaliers.constant.CavConfig;
import com.bfdelivery.cavaliers.constant.DeliveryStatus;
import com.bfdelivery.cavaliers.constant.PayType;
import com.bfdelivery.cavaliers.database.location.LocationData;
import com.bfdelivery.cavaliers.dataset.ListOutlineData;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;
import com.bfdelivery.cavaliers.ui.views.OrderDetailItemView;
import com.bfdelivery.cavaliers.util.DistanceUtil;
import com.bfdelivery.cavaliers.util.LocationClientFactory;
import com.bfdelivery.cavaliers.util.LocationSaver;
import com.bfdelivery.cavaliers.util.OrderStringBridge;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static android.app.ProgressDialog.show;

public class OrderDetailActivity extends BasePageActivity implements View.OnClickListener {

	public static final String BUNDLE_KEY_POSTION = "OrderDetailActivity.index";
	public static final String BUNDLE_KEY_LISTDATA = "OrderDetailActivity.listData";

	AMapLocationClient mLocationClient = null;

	OrderDetail mDetailInfo = null;

	ListView mListCommodity = null;
	OrderDetailItemView mCommodityItem = null;
	OrderDetailItemView mOrderId = null;
	OrderDetailItemView mOrderTime = null;
	OrderDetailItemView mOrderNote = null;
	OrderDetailItemView mOrderPay = null;
	ContentLoadingProgressBar mWaitingBar = null;

	View mWrapperCommodity = null;
	View mWrapperWating = null;
	View mWrapperDeatilInfo = null;

	TextView mTxtOrderIndex = null;
	TextView mTxtOrderStatus = null;

	TextView mTxtRstName = null;
	TextView mTxtRstAddr = null;
	TextView mTxtRstPhone = null;
	TextView mTxtRstDis = null;

	TextView mTxtUsrName = null;
	TextView mTxtUsrAddr = null;
	TextView mTxtUsrPhone = null;
	TextView mTxtUsrDis = null;

	TextView mTxtDiscount;
	TextView mTxtRealFee;

	View mOrderTimePrefix;
	View mOrderTimeSuffix;
	TextView mTxtOrderTime;

	TextView mBtnAction = null;

	DistributeService mDistributeService = null;

	DecimalFormat mDecimalFormat = new DecimalFormat("#.##");

	private int mIndex = 0;
	private ListOutlineData mOutlineData = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDistributeService = CavV1Service.createDistributeService();
		mDecimalFormat.setRoundingMode(RoundingMode.HALF_UP);

		requestOrderDetial();

		mLocationClient = LocationClientFactory.createOnceTimeLocationClient(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mLocationClient.stopLocation();
		mLocationClient.onDestroy();
	}

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_order_detail);
	}

	@Override
	protected void initView() {
		mListCommodity = (ListView) findViewById(R.id.lstCommodities);
		mCommodityItem = (OrderDetailItemView) findViewById(R.id.itemCommodity);
		mWrapperCommodity = findViewById(R.id.wrapperCommodity);
		mWrapperWating = findViewById(R.id.wrapper_waiting);
		mWaitingBar = (ContentLoadingProgressBar) findViewById(R.id.waitingbar);
		mWrapperDeatilInfo = findViewById(R.id.wrapper_detail);

		mTxtOrderStatus = (TextView) findViewById(R.id.txtOrderStatus);
		mTxtOrderIndex = (TextView) findViewById(R.id.txtOrderIndex);
		mBtnAction = (TextView) findViewById(R.id.btnAction);

		mTxtRstName = (TextView) findViewById(R.id.txtRstName);
		mTxtRstAddr = (TextView) findViewById(R.id.txtRstAddr);
		mTxtRstPhone = (TextView) findViewById(R.id.txtRstPhone);
		mTxtRstDis = (TextView) findViewById(R.id.txtRstDis);

		mTxtUsrName = (TextView) findViewById(R.id.txtUsrName);
		mTxtUsrAddr = (TextView) findViewById(R.id.txtUsrAddr);
		mTxtUsrPhone = (TextView) findViewById(R.id.txtUsrPhone);
		mTxtUsrDis = (TextView) findViewById(R.id.txtUsrDis);

		mOrderTimePrefix = findViewById(R.id.txtTimePrefix);
		mOrderTimeSuffix = findViewById(R.id.txtTimeSuffix);
		mTxtOrderTime = (TextView) findViewById(R.id.orderTimes);

		mTxtDiscount = (TextView) findViewById(R.id.txtDiscount);
		mTxtRealFee = (TextView) findViewById(R.id.txtRealFee);

		mOrderId = (OrderDetailItemView) findViewById(R.id.itemOrderId);
		mOrderTime = (OrderDetailItemView) findViewById(R.id.itemOrderTime);
		mOrderNote = (OrderDetailItemView) findViewById(R.id.orderNote);
		mOrderPay = (OrderDetailItemView) findViewById(R.id.orderPay);
	}

	@Override
	protected void handleData(Bundle data) {
		mIndex = data.getInt(BUNDLE_KEY_POSTION);
		mOutlineData = data.getParcelable(BUNDLE_KEY_LISTDATA);
	}

	@Override
	protected void processViewAndData() {

		mCommodityItem.setChecked(true);

		mCommodityItem.setOnItemCheckListener(new OrderDetailItemView.OnItemCheckChangeListener() {

			@Override
			public void onCheckedChanged(OrderDetailItemView itemView, boolean checked) {
				mWrapperCommodity.setVisibility(checked ? View.VISIBLE : View.GONE);
			}
		});

		mTxtOrderIndex.setText(getString(R.string.pre_pos, mIndex + 1));

		mTxtUsrName.setText(mOutlineData.getUsrName());
		mTxtUsrAddr.setText(mOutlineData.getUsrAddr());
		mTxtRstName.setText(mOutlineData.getRstName());
		mTxtRstAddr.setText(mOutlineData.getRstAddr());
		mBtnAction.setOnClickListener(this);
	}

	private void requestOrderDetial() {

		mWrapperWating.setVisibility(View.VISIBLE);
		mWrapperDeatilInfo.setVisibility(View.GONE);
		mWaitingBar.show();

		Call<OrderDetail> detail = mDistributeService.orderDetail(mOutlineData.getOrderId());
		detail.enqueue(new BaseCallback<OrderDetail>() {

			@Override
			public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {
				super.onResponse(call, response);

				if (response.code() == HttpStatus.SC_OK) {
					refreshData(response.body());
				}
			}

			@Override
			public void onComplete() {
				mWaitingBar.hide();
				mWrapperWating.setVisibility(View.GONE);
				mWrapperDeatilInfo.setVisibility(View.VISIBLE);
			}
		});
	}

	private void refreshData(OrderDetail detailInfo) {
		mDetailInfo = detailInfo;

		mListCommodity.setAdapter(new CommodityAdapter(detailInfo.getOrder_products()));
		mTxtRstName.setText(mDetailInfo.getShop().getName());
		mTxtRstAddr.setText(mDetailInfo.getShop().getAddress());
		mTxtRstPhone.setText(mDetailInfo.getShop().getPhone());

		mTxtUsrName.setText(mDetailInfo.getAddress().getName());
		mTxtUsrAddr.setText(mDetailInfo.getAddress().getDetail());
		mTxtUsrPhone.setText(mDetailInfo.getAddress().getMobile());

		LocationData location = LocationSaver.instance().getLocation();
		if (location != null) {
			float[] distance = new float[1];
			Location.distanceBetween(location.getLatitude(), location.getLongitude(), mDetailInfo.getShop().getLatitude(), mDetailInfo.getShop().getLongitude(), distance);
			mTxtRstDis.setText(DistanceUtil.formatDistance(this, distance[0]));
			Location.distanceBetween(location.getLatitude(), location.getLongitude(), mDetailInfo.getAddress().getLatitude(), mDetailInfo.getAddress().getLongitude(), distance);
			mTxtUsrDis.setText(DistanceUtil.formatDistance(this, distance[0]));
		}

		if (TextUtils.isEmpty(mDetailInfo.getRemark())) {
			mOrderNote.setDetail(R.string.vacant);
		} else {
			mOrderNote.setDetail(mDetailInfo.getRemark());
		}

		switch (mDetailInfo.getPay_type()) {
			case PayType.OFFLINE:
				mOrderPay.setDetail(R.string.cash_pay);
				mOrderPay.setDetailColor(getResources().getColor(R.color.colorCash));
				break;
			case PayType.WECHAT:
				mOrderPay.setDetail(R.string.wechat_pay);
				mOrderPay.setDetailColor(getResources().getColor(R.color.colorWeixin));
				break;
		}

		mTxtOrderStatus.setText(OrderStringBridge.getStatusByOrderStatu(mDetailInfo.getDistribute().getStatus()));

		mOrderTime.setDetail(mDetailInfo.getCreated_at());
		mOrderId.setDetail(mDetailInfo.getNumber());

		int resId = OrderStringBridge.getActionByOrderStatu(mDetailInfo.getDistribute().getStatus());

		if (resId > 0) {
			mBtnAction.setVisibility(View.VISIBLE);
			mBtnAction.setText(resId);
		} else {
			mBtnAction.setVisibility(View.GONE);
		}

		mTxtDiscount.setText(mDecimalFormat.format(mDetailInfo.getCoupon_amount() / 100F));
		mTxtRealFee.setText(mDecimalFormat.format(mDetailInfo.getPay_amount() / 100F));
	}

	@Override
	public void onClick(View v) {
		if (v == mBtnAction) {

			switch (mDetailInfo.getDistribute().getStatus()) {
				case DeliveryStatus.NEW_RECEIVED:
					acceptOrder();
					break;
				case DeliveryStatus.WAITING_TAKE:
					takeOrder();
					break;
				case DeliveryStatus.DEIVERING:
					completeOrder();
					break;
			}
		}
	}

	private void acceptOrder() {

		new AlertDialog.Builder(this).setMessage(R.string.tip_receive_order).setPositiveButton(R.string.btn_sure_take, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				acceptOrderInner();
			}

		}).setNegativeButton(R.string.btn_not_sure, null).show();

	}

	private void acceptOrderInner() {
		OrderNumber postParam = new OrderNumber(mDetailInfo.getNumber());
		Call<Void> request = mDistributeService.acceptOrder(postParam);

		final ProgressDialog watingDialog = show(this, null, getString(R.string.receiving_order), true);

		request.enqueue(new BaseCallback<Void>() {
			@Override
			public void onResponse(Call<Void> call, Response<Void> response) {
				super.onResponse(call, response);
				if (response.code() == HttpStatus.SC_OK) {
					Toast.makeText(OrderDetailActivity.this, R.string.receive_order_succeed, Toast.LENGTH_SHORT).show();
					requestOrderDetial();
				} else {
					onFailure(call, null);
				}
			}

			@Override
			public void onFailure(Call<Void> call, Throwable t) {
				super.onFailure(call, t);
				Toast.makeText(OrderDetailActivity.this, R.string.receive_order_failed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete() {
				watingDialog.dismiss();
			}
		});
	}

	private void takeOrder() {
		new AlertDialog.Builder(this).setMessage(R.string.tip_take_order).setPositiveButton(R.string.btn_begin_deli, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				takeOrderInner();
			}

		}).setNegativeButton(R.string.btn_waiting, null).show();
	}

	private void takeOrderInner() {
		OrderNumber postParam = new OrderNumber(mDetailInfo.getNumber());
		Call<Void> request = mDistributeService.sendOrder(postParam);

		final ProgressDialog watingDialog = show(this, null, getString(R.string.tip_doing_act), true);

		request.enqueue(new BaseCallback<Void>() {
			@Override
			public void onResponse(Call<Void> call, Response<Void> response) {
				super.onResponse(call, response);
				if (response.code() == HttpStatus.SC_OK) {
					Toast.makeText(OrderDetailActivity.this, R.string.receive_order_succeed, Toast.LENGTH_SHORT).show();
					requestOrderDetial();
				} else {
					onFailure(call, null);
				}
			}

			@Override
			public void onFailure(Call<Void> call, Throwable t) {
				super.onFailure(call, t);
				Toast.makeText(OrderDetailActivity.this, R.string.receive_order_failed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete() {
				watingDialog.dismiss();
			}
		});
	}

	private void completeOrder() {

		new AlertDialog.Builder(this).setMessage(R.string.tip_complete_order).setPositiveButton(R.string.btn_complete_deli, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				affirmLocation();
			}

		}).setNegativeButton(R.string.btn_waiting, null).show();
	}


	private void affirmLocation() {

		final ProgressDialog waitingDialog = ProgressDialog.show(this, null, getString(R.string.fetching_location), false);

		mLocationClient.setLocationListener(new AMapLocationListener() {
			@Override
			public void onLocationChanged(AMapLocation aMapLocation) {
				if (aMapLocation.getErrorCode() == LocationErrorCode.OK) {

					float[] distance = new float[1];
					Location.distanceBetween(mDetailInfo.getAddress().getLatitude(), mDetailInfo.getAddress().getLongitude(), aMapLocation.getLatitude(), aMapLocation.getLongitude(), distance);
					if (distance[0] <= CavConfig.CAV_COMPLETE_ORDER_DISTANCE) {
						completeOrderInner();
					} else {
						Toast.makeText(OrderDetailActivity.this, R.string.complete_order_failed_by_distance, Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(OrderDetailActivity.this, R.string.complete_order_failed_by_location_failed, Toast.LENGTH_SHORT).show();
				}

				waitingDialog.dismiss();
			}
		});

		mLocationClient.startLocation();
	}

	private void completeOrderInner() {
		OrderNumber postParam = new OrderNumber(mDetailInfo.getNumber());
		Call<Void> request = mDistributeService.completeOrder(postParam);

		final ProgressDialog waitingDialog = show(this, null, getString(R.string.tip_doing_act), true);

		request.enqueue(new BaseCallback<Void>() {
			@Override
			public void onResponse(Call<Void> call, Response<Void> response) {
				super.onResponse(call, response);
				if (response.code() == HttpStatus.SC_OK) {
					Toast.makeText(OrderDetailActivity.this, R.string.complete_order_succeed, Toast.LENGTH_SHORT).show();
					requestOrderDetial();
				} else {
					onFailure(call, null);
				}
			}

			@Override
			public void onFailure(Call<Void> call, Throwable t) {
				super.onFailure(call, t);
				Toast.makeText(OrderDetailActivity.this, R.string.complete_order_failed, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete() {
				waitingDialog.dismiss();
			}
		});
	}

	private static class CommodityAdapter extends BaseAdapter {

		private static class CommodityViewHolder {

			TextView mTxtName;
			TextView mTxtCount;
			TextView mTxtPrice;
			DecimalFormat mFormat = new DecimalFormat("#.##");


			public CommodityViewHolder(View rootView) {
				mTxtName = (TextView) rootView.findViewById(R.id.txtCommodityName);
				mTxtCount = (TextView) rootView.findViewById(R.id.txtCommodityCount);
				mTxtPrice = (TextView) rootView.findViewById(R.id.txtCommodityPrice);
				mFormat.setRoundingMode(RoundingMode.HALF_UP);
			}

			public void bindData(int position, OrderDetail.OrderProductsBean data) {
				mTxtName.setText(data.getName());
				int comCount = data.getNum();
				if (comCount > 1) {
					mTxtCount.setTextColor(mTxtCount.getResources().getColor(R.color.colorTxtAlert));
				} else {
					mTxtCount.setTextColor(mTxtCount.getResources().getColor(R.color.colorTxtDefault));
				}
				mTxtCount.setText("x" + comCount);
				mTxtPrice.setText(mFormat.format(data.getPrice() / 100F));
			}
		}

		List<OrderDetail.OrderProductsBean> mProductsBean = new ArrayList<>();

		public CommodityAdapter() {
		}

		public CommodityAdapter(List<OrderDetail.OrderProductsBean> productsBean) {
			if (productsBean != null && productsBean.size() > 0) {
				mProductsBean.addAll(productsBean);
			}
		}

		@Override
		public int getCount() {
			return mProductsBean.size();
		}

		@Override
		public OrderDetail.OrderProductsBean getItem(int position) {
			return mProductsBean.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			CommodityViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_commodity_item, parent, false);
				holder = new CommodityViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (CommodityViewHolder) convertView.getTag();
			}

			holder.bindData(position, getItem(position));

			return convertView;
		}
	}
}
