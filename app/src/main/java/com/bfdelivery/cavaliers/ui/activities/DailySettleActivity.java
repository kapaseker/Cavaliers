package com.bfdelivery.cavaliers.ui.activities;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.TextView;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.server.bean.response.DailyInfoBean;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.database.userinfo.UserInfo;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;
import com.bfdelivery.cavaliers.util.UserInfoManager;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;

public class DailySettleActivity extends BasePageActivity implements UserInfoManager.OnUserInfoListener {

	private TextView mTxtUsrName;
	private TextView mTxtUsrPhone;
	private TextView mTxtOrderCount;
	private TextView mTxtOnlinePay;
	private TextView mTxtCashPay;
	private View mInfoWrapper;

	ContentLoadingProgressBar mLoadingProgressBar = null;
	DecimalFormat mDecimalFormat = new DecimalFormat("#.##");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_daily_settle);
	}

	@Override
	protected void initView() {
		getSupportActionBar().setTitle(R.string.action_menu_daily_settle);

		mTxtUsrName = (TextView) findViewById(R.id.txtUsrName);
		mTxtUsrPhone = (TextView) findViewById(R.id.txtUsrPhone);
		mTxtOrderCount = (TextView) findViewById(R.id.txtOrderCount);
		mTxtOnlinePay = (TextView) findViewById(R.id.txtOnline);
		mTxtCashPay = (TextView) findViewById(R.id.txtCash);
		mInfoWrapper = findViewById(R.id.infowrapper);

		mLoadingProgressBar = (ContentLoadingProgressBar) findViewById(R.id.waitingbar);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			mLoadingProgressBar.setIndeterminateTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
		}

		mDecimalFormat.setRoundingMode(RoundingMode.HALF_UP);
	}

	@Override
	protected void handleData(Bundle data) {

	}

	@Override
	protected void processViewAndData() {

		UserInfo userInfo = UserInfoManager.instance().getUserInfo();

		if (userInfo != null) {
			mTxtUsrName.setText(userInfo.getUserName());
			mTxtUsrPhone.setText(userInfo.getUserPhone());
		}

		UserInfoManager.instance().fetchUserInfo(this);

		CavV1Service.createDistributeService().dailyStatics().enqueue(new BaseCallback<DailyInfoBean>() {
			@Override
			public void onResponse(Call<DailyInfoBean> call, Response<DailyInfoBean> response) {
				super.onResponse(call, response);
				if (response.code() == HttpStatus.SC_OK) {
					refreshInfo(response.body());
				} else {

				}
			}

			@Override
			public void onComplete() {
				mLoadingProgressBar.hide();
				mInfoWrapper.setVisibility(View.VISIBLE);
			}
		});
	}

	private void refreshInfo(DailyInfoBean result) {

		int orderCount = 0;
		int todayOfflinePay = 0;
		int todayOnlinePay = 0;

		if (result.getData() != null && result.getData().size() > 0) {
			DailyInfoBean.DataBean todayData = result.getData().get(0);

			//匹配日期是否是今天
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());

			int todayYear = calendar.get(Calendar.YEAR);
			int todayMonth = calendar.get(Calendar.MONTH);
			int todayDay = calendar.get(Calendar.DAY_OF_MONTH);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date orderDate = dateFormat.parse(todayData.getDate());
				calendar.setTime(orderDate);

				if (todayDay == calendar.get(Calendar.DAY_OF_MONTH) && todayMonth == calendar.get(Calendar.MONTH) && todayYear == calendar.get(Calendar.YEAR)) {
					orderCount = todayData.getOrder_num();
					todayOfflinePay = todayData.getOffline_amount();
					todayOnlinePay = todayData.getWechat_amount();
				}

			} catch (ParseException e) {

			}

		}

		mTxtOrderCount.setText(getString(R.string.suffix_count, orderCount));
		mTxtCashPay.setText(getString(R.string.prefix_rmb, mDecimalFormat.format(todayOfflinePay / 100F)));
		mTxtOnlinePay.setText(getString(R.string.prefix_rmb, mDecimalFormat.format(todayOnlinePay / 100F)));
	}

	@Override
	public void onUserInfoReceived(UserInfo result) {
		mTxtUsrName.setText(result.getUserName());
		mTxtUsrPhone.setText(result.getUserPhone());
	}

	@Override
	public void onBegin() {

	}
}
