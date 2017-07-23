package com.bfdelivery.cavaliers.ui.activities;

import android.os.Bundle;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;

public class LoginActivity extends BasePageActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_login);
		getSupportActionBar().hide();
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void handleData(Bundle data) {

	}

	@Override
	protected void processViewAndData() {

	}
}
