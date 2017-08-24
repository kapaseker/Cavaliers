package com.bfdelivery.cavaliers.ui.activities;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.TextView;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.server.bean.response.PersonInfoBean;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.database.userinfo.UserInfo;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;
import com.bfdelivery.cavaliers.util.UserInfoManager;

import retrofit2.Call;
import retrofit2.Response;

public class DailySettleActivity extends BasePageActivity {

	private TextView mTxtUsrName;
	private TextView mTxtUsrPhone;
	private TextView mTxtOrderCount;
	private TextView mTxtOnlinePay;
	private TextView mTxtCashPay;
	private View mInfoWrapper;

	ContentLoadingProgressBar mLoadingProgressBar = null;

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

		CavV1Service.createDistributeService().personInfo().enqueue(new BaseCallback<PersonInfoBean>() {
			@Override
			public void onResponse(Call<PersonInfoBean> call, Response<PersonInfoBean> response) {
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

	private void refreshInfo(PersonInfoBean result) {
		mTxtUsrName.setText(result.getName());
		mTxtUsrPhone.setText(result.getMobile());
		mTxtOrderCount.setText(getString(R.string.suffix_count, result.getToday_done_orders()));
		mTxtCashPay.setText(getString(R.string.prefix_rmb, (result.getToday_offline_amount() / 10) / 10F));
		mTxtOnlinePay.setText(getString(R.string.prefix_rmb, (result.getToday_wechat_amount() / 10) / 10F));
	}

}
