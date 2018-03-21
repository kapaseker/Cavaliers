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
import com.bfdelivery.cavaliers.ui.activities.base.BaseActivity;
import com.bfdelivery.cavaliers.util.TimeUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class NewOrderTipActivity extends BaseActivity implements View.OnClickListener {

//	NewOrderPushMsg mPushMsg = null;

	TextView mTxtNewOrderTip = null;
	TextView mTxtTime = null;

	Vibrator mVibrator = null;
	Ringtone mMsgRing = null;

	private int mOrderCount = 0;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		Uri ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		mMsgRing = RingtoneManager.getRingtone(this, ringToneUri);
		makeTipMedia();
	}

	@Override
	protected void onPrepareLayout() {
		getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_FULLSCREEN
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
		);
		setContentView(R.layout.activity_new_order_tip);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		makeTipMedia();
	}

	@Override
	protected void initView() {
		getSupportActionBar().hide();

		findViewById(R.id.wrapper_tip).setOnClickListener(this);
		mTxtNewOrderTip = findViewById(R.id.txt_ordertip);
		mTxtTime = findViewById(R.id.txt_time);
	}

	@Override
	protected void handleData(Bundle data) {
//		String msgContent = data.getString(JPushInterface.EXTRA_MESSAGE);
//		ObjectMapper mapper = ObjectMapperFactory.createIgnorePropertiesMapper();
//		try {
//			mPushMsg = mapper.readValue(msgContent, NewOrderPushMsg.class);
//		} catch (IOException e) {
//
//		}
	}

	@Override
	protected void processViewAndData() {
//		if (mPushMsg != null) {
//			if (mPushMsg.getShop() != null) {
//			}
//		}

		++mOrderCount;

		mTxtNewOrderTip.setText(getString(R.string.new_order_tip, mOrderCount));

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		calendar.setTime(new Date());
		mTxtTime.setText(TimeUtil.convertDoubleFormatTime(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
				+ TimeUtil.convertDoubleFormatTime(calendar.get(Calendar.MINUTE)));
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
		mVibrator.vibrate(new long[]{0, 1000, 1000, 1000, 1000, 1000}, -1);
		mMsgRing.play();
	}

	private void cancleTipMedia() {
		mVibrator.cancel();
	}

	@Override
	protected void onStop() {
		super.onStop();
		cancleTipMedia();
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
