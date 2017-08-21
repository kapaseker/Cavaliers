package com.bfdelivery.cavaliers.ui.activities;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.server.bean.response.OrderDetail;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.background.server.request.DistributeService;
import com.bfdelivery.cavaliers.constant.PayType;
import com.bfdelivery.cavaliers.database.location.LocationData;
import com.bfdelivery.cavaliers.dataset.ListOutlineData;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;
import com.bfdelivery.cavaliers.ui.views.OrderDetailItemView;
import com.bfdelivery.cavaliers.util.DistanceUtil;
import com.bfdelivery.cavaliers.util.LocationSaver;

import retrofit2.Call;
import retrofit2.Response;

public class OrderDetailActivity extends BasePageActivity {

	public static final String BUNDLE_KEY_POSTION = "OrderDetailActivity.index";
	public static final String BUNDLE_KEY_LISTDATA = "OrderDetailActivity.listData";

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

	TextView mTxtRstName = null;
	TextView mTxtRstAddr = null;
	TextView mTxtRstPhone = null;
	TextView mTxtRstDis = null;

	TextView mTxtUsrName = null;
	TextView mTxtUsrAddr = null;
	TextView mTxtUsrPhone = null;
	TextView mTxtUsrDis = null;

	DistributeService mDistributeService = null;

	private int mIndex = 0;
	private ListOutlineData mOutlineData = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDistributeService = CavV1Service.createDistributeService();

		requestOrderDetial();
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

		mTxtOrderIndex = (TextView) findViewById(R.id.txtOrderIndex);

		mTxtRstName = (TextView) findViewById(R.id.txtRstName);
		mTxtRstAddr = (TextView) findViewById(R.id.txtRstAddr);
		mTxtRstPhone = (TextView) findViewById(R.id.txtRstPhone);
		mTxtRstDis = (TextView) findViewById(R.id.txtRstDis);

		mTxtUsrName = (TextView) findViewById(R.id.txtUsrName);
		mTxtUsrAddr = (TextView) findViewById(R.id.txtUsrAddr);
		mTxtUsrPhone = (TextView) findViewById(R.id.txtUsrPhone);
		mTxtUsrDis = (TextView) findViewById(R.id.txtUsrDis);

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

		mListCommodity.setAdapter(new CommodityAdapter());
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
	}

	private void requestOrderDetial() {

		Call<OrderDetail> detail = mDistributeService.orderDetail(mOutlineData.getOrderId());
		detail.enqueue(new BaseCallback<OrderDetail>() {

			@Override
			public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {
				super.onResponse(call, response);

				if (response.code() == HttpStatus.SC_OK) {
					refreshData(response.body());
				} else {

				}
			}

			@Override
			public void onComplete() {
				mWrapperWating.setVisibility(View.GONE);
				mWrapperDeatilInfo.setVisibility(View.VISIBLE);
			}
		});
	}

	private void refreshData(OrderDetail detailInfo) {
		mDetailInfo = detailInfo;

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

		mOrderTime.setDetail(mDetailInfo.getCreated_at());
		mOrderId.setDetail(mDetailInfo.getNumber());
	}

	private static class CommodityAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 7;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_commodity_item, parent, false);
			} else {

			}

			return convertView;
		}
	}
}
