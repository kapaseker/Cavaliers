package com.bfdelivery.cavaliers.ui.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
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
import com.bfdelivery.cavaliers.database.location.LocationData;
import com.bfdelivery.cavaliers.dataset.ListOutlineData;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;
import com.bfdelivery.cavaliers.ui.views.OrderDetailItemView;
import com.bfdelivery.cavaliers.util.DistanceUtil;
import com.bfdelivery.cavaliers.util.LocationClientFactory;
import com.bfdelivery.cavaliers.util.LocationSaver;
import com.bfdelivery.cavaliers.util.OrderStringBridge;
import com.bfdelivery.cavaliers.util.ResultProcessor;

import org.apache.commons.lang3.StringUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static android.app.ProgressDialog.show;

public class OrderDetailActivity extends BasePageActivity implements View.OnClickListener, AMapLocationListener {

	public static final String BUNDLE_KEY_POSTION = "OrderDetailActivity.index";
	public static final String BUNDLE_KEY_LISTDATA = "OrderDetailActivity.listData";

	AMapLocationClient mLocationClient = null;
	AMapLocationListener mLocationListener = null;

	OrderDetail mDetailInfo = null;

	ListView mListCommodity = null;
	OrderDetailItemView mCommodityItem = null;
	//    OrderDetailItemView mOrderId = null;
//    OrderDetailItemView mOrderTime = null;
//	OrderDetailItemView mOrderNote = null;
//	OrderDetailItemView mOrderPay = null;
	ContentLoadingProgressBar mWaitingBar = null;

	View mWrapperCommodity = null;
	View mWrapperWating = null;
	View mWrapperDeatilInfo = null;

	TextView mTxtOrderIndex = null;
	TextView mTxtOrderStatus = null;

	TextView mTxtRstName = null;
	TextView mTxtRstAddr = null;
	//    TextView mTxtRstPhone = null;
	TextView mTxtRstDis = null;

	TextView mTxtUsrName = null;
	TextView mTxtUsrAddr = null;
	//    TextView mTxtUsrPhone = null;
	TextView mTxtUsrDis = null;
	TextView mTxtLabelActualFee = null;

	/**
	 * 用户首单提示
	 */
	TextView mTxtFirstOrder;

	View mWrapperDiscount;
	TextView mTxtDiscount;
	TextView mTxtRealFee;

	/**
	 * 打包盒相关
	 */
	View mWrapperPackage;
	TextView mTxtPackageFee;

	TextView mTxtSubtotal;
	/**
	 * 服务费相关
	 */
	View mWrapperServe;
	TextView mTxtServiceFee;

	TextView mTxtMkOrderTime;
	TextView mTxtOrderNum;

	TextView mTxtOrderTimes;

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

		requestOrderDetial(false);

		mLocationClient = LocationClientFactory.createOnceTimeLocationClient(this);
		mLocationClient.setLocationListener(this);
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
		mTxtMkOrderTime = (TextView) findViewById(R.id.txtMakeOrderTime);
		mTxtOrderNum = (TextView) findViewById(R.id.txtOrderNumber);

		mTxtRstName = (TextView) findViewById(R.id.txtRstName);
		mTxtRstAddr = (TextView) findViewById(R.id.txtRstAddr);
//        mTxtRstPhone = (TextView) findViewById(R.id.txtRstPhone);
		mTxtRstDis = (TextView) findViewById(R.id.txtRstDis);

		mTxtUsrName = (TextView) findViewById(R.id.txtUsrName);
		mTxtUsrAddr = (TextView) findViewById(R.id.txtUsrAddr);
//        mTxtUsrPhone = (TextView) findViewById(R.id.txtUsrPhone);
		mTxtUsrDis = (TextView) findViewById(R.id.txtUsrDis);

		mTxtOrderTimes = (TextView) findViewById(R.id.orderTimes);

		mWrapperDiscount = findViewById(R.id.wrapperDiscount);
		mTxtDiscount = (TextView) findViewById(R.id.txtDiscount);
		mTxtRealFee = (TextView) findViewById(R.id.txtRealFee);
		mTxtLabelActualFee = (TextView) findViewById(R.id.txtLabelActualFee);

		mWrapperServe = findViewById(R.id.wrapperServe);
		mTxtServiceFee = (TextView) findViewById(R.id.txtServiceFee);

		mWrapperPackage = findViewById(R.id.wrapperPackaging);
		mTxtPackageFee = (TextView) findViewById(R.id.txtPackaging);
		mTxtSubtotal = (TextView) findViewById(R.id.txtSubTotal);

//		mOrderNote = (OrderDetailItemView) findViewById(R.id.orderNote);
//		mOrderPay = (OrderDetailItemView) findViewById(R.id.orderPay);

		mTxtFirstOrder = (TextView) findViewById(R.id.txt_labelFirst);

		mTxtUsrName.setVisibility(View.VISIBLE);
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

	/**
	 * 通知订单更新
	 */
	private void notifyOrderUpdate() {
		sendBroadcast(new Intent(CavConfig.ACTION_UPDATE_DATA));
	}

	private void requestOrderDetial(boolean needNotifyUpdate) {

		if (needNotifyUpdate) {
			notifyOrderUpdate();
		}

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
//		SpannableString strFeeLable = new SpannableString(getString(R.string.actual_fee));
//		strFeeLable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 3, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		mTxtLabelActualFee.setText(R.string.actual_fee);

		mListCommodity.setAdapter(new CommodityAdapter(detailInfo.getOrder_products()));
		mTxtRstName.setText(mDetailInfo.getShop().getName());
		mTxtRstAddr.setText(mDetailInfo.getShop().getAddress());
//        mTxtRstPhone.setText(mDetailInfo.getShop().getPhone());

		mTxtUsrName.setText(mDetailInfo.getAddress().getName());
		mTxtUsrAddr.setText(mDetailInfo.getAddress().getDetail());

		int orderDistibuteStatus = mDetailInfo.getDistribute().getStatus();
		if (orderDistibuteStatus == DeliveryStatus.NEW_RECEIVED
				|| orderDistibuteStatus == DeliveryStatus.DEIVERING
				|| orderDistibuteStatus == DeliveryStatus.WAITING_TAKE
				) {
			mTxtRstName.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_dial), null);
			mTxtUsrName.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_dial), null);

			mTxtRstName.setOnClickListener(this);
			mTxtUsrName.setOnClickListener(this);

//            mTxtRstPhone.setText(mDetailInfo.getShop().getPhone());
//            mTxtUsrPhone.setText(mDetailInfo.getAddress().getMobile());
		} else {
			mTxtRstName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			mTxtUsrName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

			mTxtRstName.setOnClickListener(null);
			mTxtUsrName.setOnClickListener(null);

//            mTxtRstPhone.setText(OrderStringBridge.getSecretPhone(mDetailInfo.getShop().getPhone()));
//            mTxtUsrPhone.setText(OrderStringBridge.getSecretPhone(mDetailInfo.getAddress().getMobile()));
		}


		LocationData location = LocationSaver.instance().getLocation();
		if (location != null) {
			float[] distance = new float[1];
			Location.distanceBetween(location.getLatitude(), location.getLongitude(), mDetailInfo.getShop().getLatitude(), mDetailInfo.getShop().getLongitude(), distance);
			mTxtRstDis.setText(DistanceUtil.formatDistance(this, distance[0]));
			Location.distanceBetween(location.getLatitude(), location.getLongitude(), mDetailInfo.getAddress().getLatitude(), mDetailInfo.getAddress().getLongitude(), distance);
			mTxtUsrDis.setText(DistanceUtil.formatDistance(this, distance[0]));
		}

//		if (TextUtils.isEmpty(mDetailInfo.getRemark())) {
//			mOrderNote.setDetail(R.string.vacant);
//		} else {
//			mOrderNote.setDetail(mDetailInfo.getRemark());
//		}
//
//		switch (mDetailInfo.getPay_type()) {
//			case PayType.OFFLINE:
//				mOrderPay.setDetail(R.string.cash_pay);
//				mOrderPay.setDetailColor(getResources().getColor(R.color.colorCash));
//				break;
//			case PayType.WECHAT:
//				mOrderPay.setDetail(R.string.wechat_pay);
//				mOrderPay.setDetailColor(getResources().getColor(R.color.colorWeixin));
//				break;
//		}

		mTxtFirstOrder.setVisibility(View.GONE);
		mTxtOrderTimes.setVisibility(View.GONE);

		if (mDetailInfo.getOrder_num() > 1) {
			mTxtOrderTimes.setVisibility(View.VISIBLE);
			mTxtOrderTimes.setText(getString(R.string.order_count, mDetailInfo.getOrder_num()));
		} else {
			mTxtFirstOrder.setVisibility(View.VISIBLE);
		}

		mTxtOrderStatus.setText(OrderStringBridge.getStatusByOrderStatu(mDetailInfo.getDistribute().getStatus()));
		mTxtMkOrderTime.setText(getString(R.string.suffix_makeorder, mDetailInfo.getCreated_at()));
		mTxtOrderNum.setText(getString(R.string.prefix_orderNum, mDetailInfo.getNumber()));

//        mOrderTime.setDetail(mDetailInfo.getCreated_at());
//        mOrderId.setDetail(mDetailInfo.getNumber());

		int resId = OrderStringBridge.getActionByOrderStatu(mDetailInfo.getDistribute().getStatus());

		if (resId > 0) {
			mBtnAction.setVisibility(View.VISIBLE);
			mBtnAction.setText(resId);
		} else {
			mBtnAction.setVisibility(View.GONE);
		}

		{// 计算纯商品的价格
			double subTotal = mDetailInfo.getPay_amount() - mDetailInfo.getService_amount() + mDetailInfo.getCoupon_amount() - ResultProcessor.extractPackageFee(mDetailInfo.getOrder_products());
			mTxtSubtotal.setText(getString(R.string.prefix_rmb, mDecimalFormat.format(subTotal / 100F)));
		}

		{// 计算打包盒费用
			double packageFee = ResultProcessor.extractPackageFee(mDetailInfo.getOrder_products());
			if (packageFee > 0.0) {
				mWrapperPackage.setVisibility(View.VISIBLE);
				mTxtPackageFee.setText(getString(R.string.prefix_rmb, mDecimalFormat.format(packageFee / 100F)));
			} else {
				mWrapperPackage.setVisibility(View.GONE);
			}
		}

		{//计算服务费用
			if (mDetailInfo.getService_amount() > 0.0) {
				mWrapperServe.setVisibility(View.VISIBLE);
				mTxtServiceFee.setText(getString(R.string.prefix_rmb, mDecimalFormat.format(mDetailInfo.getService_amount() / 100F)));
			} else {
				mWrapperServe.setVisibility(View.GONE);
			}
		}

		int disCount = mDetailInfo.getCoupon_amount();
		if (disCount > 0) {
			mWrapperDiscount.setVisibility(View.VISIBLE);
			mTxtDiscount.setText(getString(R.string.prefix_discount_rmb, mDecimalFormat.format(mDetailInfo.getCoupon_amount() / 100F)));
		} else {
			mWrapperDiscount.setVisibility(View.GONE);
		}

		mTxtRealFee.setText(getString(R.string.prefix_rmb, mDecimalFormat.format(mDetailInfo.getPay_amount() / 100F)));
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
		} else if (v == mTxtUsrName) {
			callPhone(mDetailInfo.getAddress().getMobile());
		} else if (v == mTxtRstName) {
			callPhone(mDetailInfo.getShop().getPhone());
		}
	}

	private void callPhone(String phoneNumber) {
		Intent callIntent = new Intent(Intent.ACTION_DIAL);
		callIntent.setData(Uri.parse("tel:" + phoneNumber));
		startActivity(callIntent);
	}

	private void acceptOrder() {
		acceptOrderInner();
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
					requestOrderDetial(true);
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
				affirmRstrLocation();
			}

		}).setNegativeButton(R.string.btn_waiting, null).show();
	}

	private void affirmRstrLocation() {

//		final ProgressDialog waitingDialog = ProgressDialog.show(this, null, getString(R.string.fetching_location), false);
//		affirmLocation(new AMapLocationListener() {
//			@Override
//			public void onLocationChanged(AMapLocation aMapLocation) {
//				if (aMapLocation.getErrorCode() == LocationErrorCode.OK) {
//
//					float[] distance = new float[1];
//					Location.distanceBetween(mDetailInfo.getShop().getLatitude(), mDetailInfo.getShop().getLongitude(), aMapLocation.getLatitude(), aMapLocation.getLongitude(), distance);
//					if (distance[0] <= CavConfig.CAV_COMPLETE_ORDER_DISTANCE) {
//						takeOrderInner();
//					} else {
//						Toast.makeText(OrderDetailActivity.this, R.string.take_order_failed_by_distance, Toast.LENGTH_SHORT).show();
//					}
//				} else {
//					Toast.makeText(OrderDetailActivity.this, getString(R.string.complete_order_failed_by_location_failed, aMapLocation.getErrorCode()), Toast.LENGTH_SHORT).show();
//				}
//
//				waitingDialog.dismiss();
//			}
//		});

		takeOrderInner();
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
					Toast.makeText(OrderDetailActivity.this, R.string.take_order_succeed, Toast.LENGTH_SHORT).show();
					requestOrderDetial(true);
				} else {
					onFailure(call, null);
				}
			}

			@Override
			public void onFailure(Call<Void> call, Throwable t) {
				super.onFailure(call, t);
				Toast.makeText(OrderDetailActivity.this, R.string.take_order_failed, Toast.LENGTH_SHORT).show();
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
				affirmUsrLocation();
			}

		}).setNegativeButton(R.string.btn_waiting, null).show();
	}


	private void affirmUsrLocation() {

		final ProgressDialog waitingDialog = ProgressDialog.show(this, null, getString(R.string.fetching_location), false);
		affirmLocation(new AMapLocationListener() {
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
					Toast.makeText(OrderDetailActivity.this, getString(R.string.complete_order_failed_by_location_failed, aMapLocation.getErrorCode()), Toast.LENGTH_SHORT).show();
				}

				waitingDialog.dismiss();
			}
		});
	}

	private void affirmLocation(AMapLocationListener listener) {
		mLocationListener = listener;
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
					requestOrderDetial(true);
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

	@Override
	public void onLocationChanged(AMapLocation aMapLocation) {
		if (mLocationListener != null) {
			mLocationListener.onLocationChanged(aMapLocation);
		}
	}

	private static class CommodityAdapter extends BaseAdapter {

		private static class CommodityViewHolder {

			TextView mTxtName;
			TextView mTxtCount;
			TextView mTxtPrice;
			TextView mTxtUnit;
			TextView mTxtRemark;
			TextView mTxtSpec;
			DecimalFormat mFormat = new DecimalFormat("#.##");


			public CommodityViewHolder(View rootView) {
				mTxtName = rootView.findViewById(R.id.txtCommodityName);
				mTxtCount = rootView.findViewById(R.id.txtCommodityCount);
				mTxtPrice = rootView.findViewById(R.id.txtCommodityPrice);
				mTxtUnit = rootView.findViewById(R.id.txtUnitPrice);
				mTxtRemark = rootView.findViewById(R.id.txtRemark);
				mTxtSpec = rootView.findViewById(R.id.txtSpec);

				mFormat.setRoundingMode(RoundingMode.HALF_UP);
			}

			public void bindData(int position, OrderDetail.OrderProductsBean data) {

				Resources resource = mTxtName.getResources();

				mTxtName.setText(data.getName());
				int comCount = data.getNum();

				if (comCount > 1) {
					mTxtCount.setTextColor(mTxtCount.getResources().getColor(R.color.colorTxtAlert));
				} else {
					mTxtCount.setTextColor(mTxtCount.getResources().getColor(R.color.colorTxtDefault));
				}

				String remark = data.getRemark();
				if (StringUtils.isEmpty(remark)) {
					mTxtRemark.setVisibility(View.GONE);
				} else {
					mTxtRemark.setVisibility(View.VISIBLE);
					mTxtRemark.setText(remark);
				}

				String specTxt = ResultProcessor.extractSpec(data);

				if (StringUtils.isEmpty(specTxt)) {
					mTxtSpec.setVisibility(View.GONE);
				} else {
					mTxtSpec.setVisibility(View.VISIBLE);
					mTxtSpec.setText(specTxt);
				}

				double packageFee = data.getPacking_box_num() * data.getPacking_box_price();
				double productFee = data.getPrice() * data.getNum();

				mTxtUnit.setText(resource.getString(R.string.prefix_count, mFormat.format(data.getPrice() / 100)));
				mTxtCount.setText(mTxtCount.getResources().getString(R.string.prefix_count, data.getNum() + ""));
				mTxtPrice.setText(mTxtPrice.getResources().getString(R.string.prefix_rmb, mFormat.format(productFee / 100F)));
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
