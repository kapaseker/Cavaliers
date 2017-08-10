package com.bfdelivery.cavaliers.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.base.BaseActivity;

public class NewOrderTipActivity extends BaseActivity implements View.OnClickListener {

	@Override
	protected void onPrepareLayout() {
		getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
		);
		setContentView(R.layout.activity_new_order_tip);
	}

	@Override
	protected void initView() {
		getSupportActionBar().hide();

		findViewById(R.id.btnCheck).setOnClickListener(this);
	}

	@Override
	protected void handleData(Bundle data) {

	}

	@Override
	protected void processViewAndData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnCheck:
				startActivity(new Intent(this, IndexActivity.class));
				break;
		}
	}
}
