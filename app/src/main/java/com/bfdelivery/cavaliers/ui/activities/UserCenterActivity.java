package com.bfdelivery.cavaliers.ui.activities;

import android.os.Bundle;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;

public class UserCenterActivity extends BasePageActivity {

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

	}

	@Override
	protected void handleData(Bundle data) {

	}

	@Override
	protected void processViewAndData() {
		getSupportActionBar().setTitle(R.string.user_center);
	}
}
