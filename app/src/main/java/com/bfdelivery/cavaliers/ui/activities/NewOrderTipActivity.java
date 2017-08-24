package com.bfdelivery.cavaliers.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.dataset.NewOrderPushMsg;
import com.bfdelivery.cavaliers.ui.activities.base.BaseActivity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import cn.jpush.android.api.JPushInterface;

public class NewOrderTipActivity extends BaseActivity implements View.OnClickListener {

	NewOrderPushMsg mPushMsg = null;

	TextView mTxtUsrAddr = null;
	TextView mTxtUsrName = null;
	TextView mTxtRstName = null;
	TextView mTxtRstAddr = null;

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

		mTxtRstName = (TextView) findViewById(R.id.txtRstrtName);
		mTxtRstAddr = (TextView) findViewById(R.id.txtRstrtAddr);
		mTxtUsrName = (TextView) findViewById(R.id.txtUsrName);
		mTxtUsrAddr = (TextView) findViewById(R.id.txtUsrAddr);
	}

	@Override
	protected void handleData(Bundle data) {
		String msgContent = data.getString(JPushInterface.EXTRA_MESSAGE);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			mPushMsg = mapper.readValue(msgContent, NewOrderPushMsg.class);
		} catch (IOException e) {

		}
	}

	@Override
	protected void processViewAndData() {
		mTxtRstName.setText(mPushMsg.getShop().getName());
		mTxtRstAddr.setText(mPushMsg.getShop().getAddress());
		mTxtUsrName.setText(mPushMsg.getAddress().getName());
		mTxtUsrAddr.setText(mPushMsg.getAddress().getDetail());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnCheck:
				Intent intent = new Intent(this, IndexActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				startActivity(intent);
				finish();
				break;
		}
	}
}
