package com.bfdelivery.cavaliers.ui.activities;

import android.os.Bundle;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;

public class DailySettleActivity extends BasePageActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle(R.string.action_menu_daily_settle);
	}

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_daily_settle);
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
