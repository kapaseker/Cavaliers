package com.bfdelivery.cavaliers.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.server.bean.response.PersonInfoBean;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.database.userinfo.UserInfo;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;
import com.bfdelivery.cavaliers.ui.views.OrderDetailItemView;
import com.bfdelivery.cavaliers.util.SignManager;
import com.bfdelivery.cavaliers.util.UserInfoManager;

import retrofit2.Call;
import retrofit2.Response;

public class UserCenterActivity extends BasePageActivity implements View.OnClickListener {

	TextView mTxtUsrName;
	TextView mTxtUsrPhone;
	View mBtnSignOut;

	OrderDetailItemView mItemUsrId;
	OrderDetailItemView mItemOrderCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_user_center);
	}

	@Override
	protected void initView() {
		mTxtUsrName = (TextView) findViewById(R.id.txtUsrName);
		mTxtUsrPhone = (TextView) findViewById(R.id.txtUsrPhone);
		mItemUsrId = (OrderDetailItemView) findViewById(R.id.itemId);
		mItemOrderCount = (OrderDetailItemView) findViewById(R.id.itemCount);
		mBtnSignOut = findViewById(R.id.btnSignOut);
		getSupportActionBar().setTitle(R.string.user_center);

		mBtnSignOut.setOnClickListener(this);
	}

	@Override
	protected void handleData(Bundle data) {

	}

	@Override
	public void onClick(View v) {
		if (v == mBtnSignOut) {
			finish();
			SignManager.instance().reSignIn(this);
		}
	}

	@Override
	protected void processViewAndData() {

		UserInfo userInfo = UserInfoManager.instance().getUserInfo();

		if (userInfo != null) {
			mTxtUsrName.setText(userInfo.getUserName());
			mTxtUsrPhone.setText(userInfo.getUserPhone());
			mItemUsrId.setDetail(userInfo.getId() + "");
		}

		CavV1Service.createDistributeService().personInfo().enqueue(new BaseCallback<PersonInfoBean>() {

			@Override
			public void onResponse(Call<PersonInfoBean> call, Response<PersonInfoBean> response) {
				super.onResponse(call, response);
				if (response.code() == HttpStatus.SC_OK) {
					refreshUserInfo(response.body());
				}
			}

			@Override
			public void onComplete() {

			}
		});
	}

	private void refreshUserInfo(PersonInfoBean info) {
		if (info == null) return;
		mTxtUsrName.setText(info.getName());
		mTxtUsrPhone.setText(info.getMobile());
		mItemUsrId.setDetail(info.getId() + "");
		mItemOrderCount.setDetail(getString(R.string.suffix_count, info.getTotal_orders()));
	}
}
