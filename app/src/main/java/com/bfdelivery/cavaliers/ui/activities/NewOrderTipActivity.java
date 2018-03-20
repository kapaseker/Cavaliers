package com.bfdelivery.cavaliers.ui.activities;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.constant.BundleKeyData;
import com.bfdelivery.cavaliers.dataset.NewOrderPushMsg;
import com.bfdelivery.cavaliers.dataset.jsons.ObjectMapperFactory;
import com.bfdelivery.cavaliers.ui.activities.base.BaseActivity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import cn.jpush.android.api.JPushInterface;

public class NewOrderTipActivity extends BaseActivity implements View.OnClickListener {

	NewOrderPushMsg mPushMsg = null;

	TextView mTxtNewOrderTip = null;
	TextView mTxtTime = null;

	Vibrator mVibrator = null;
	Ringtone mMsgRing = null;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		Uri ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		mMsgRing = RingtoneManager.getRingtone(this,ringToneUri);
	}

	@Override
	protected void onPrepareLayout() {
		getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
		);
		setTurnScreenOn(true);
		setShowWhenLocked(true);
		setContentView(R.layout.activity_new_order_tip);
	}

	@Override
	protected void initView() {
		getSupportActionBar().hide();

		findViewById(R.id.wrapper_tip).setOnClickListener(this);

	}

	@Override
	protected void handleData(Bundle data) {
		String msgContent = data.getString(JPushInterface.EXTRA_MESSAGE);
		ObjectMapper mapper = ObjectMapperFactory.createIgnorePropertiesMapper();
		try {
			mPushMsg = mapper.readValue(msgContent, NewOrderPushMsg.class);
		} catch (IOException e) {

		}
	}

	@Override
	protected void processViewAndData() {
		if (mPushMsg != null) {
			if (mPushMsg.getShop() != null) {
			}
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		handleData(intent.getExtras());
		processViewAndData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.wrapper_tip:
				goToDetail();
				break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
				|| (keyCode == KeyEvent.KEYCODE_POWER)
				|| (keyCode == KeyEvent.KEYCODE_VOLUME_UP)
				|| (keyCode == KeyEvent.KEYCODE_CAMERA)
				|| (keyCode == KeyEvent.KEYCODE_HOME)
				|| (keyCode == KeyEvent.KEYCODE_BACK)
				|| (keyCode == KeyEvent.KEYCODE_APP_SWITCH)
				) {
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void makeTipMedia() {
		mVibrator.vibrate(new long[]{0, 1000, 1000}, 0);
		mMsgRing.play();
	}

	@Override
	protected void onStart() {
		super.onStart();
		makeTipMedia();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mVibrator.cancel();
	}

	/**
	 * 去查看订单了
	 */
	private void goToDetail() {
		Intent intent = new Intent(this, IndexActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		intent.putExtra(BundleKeyData.KEY_FROM, BundleKeyData.FROM_NEW_TIP);
		startActivity(intent);
		finish();
	}
}
