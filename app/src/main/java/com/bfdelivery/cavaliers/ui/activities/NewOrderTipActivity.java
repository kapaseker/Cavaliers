package com.bfdelivery.cavaliers.ui.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
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
import com.bfdelivery.cavaliers.ui.activities.base.BaseActivity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import cn.jpush.android.api.JPushInterface;

public class NewOrderTipActivity extends BaseActivity implements View.OnClickListener, SoundPool.OnLoadCompleteListener {

	NewOrderPushMsg mPushMsg = null;

	TextView mTxtUsrAddr = null;
	TextView mTxtUsrName = null;
	TextView mTxtRstName = null;
	TextView mTxtRstAddr = null;

	Vibrator mVibrator = null;
	SoundPool mSoundPool = null;

	private int mSoundId;
	private int mStreamId;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		mSoundPool = new SoundPool(1, AudioManager.STREAM_RING, 0);
	}

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
		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		try {
			mPushMsg = mapper.readValue(msgContent, NewOrderPushMsg.class);
		} catch (IOException e) {

		}
	}

	@Override
	protected void processViewAndData() {
		if (mPushMsg != null) {
			if (mPushMsg.getShop() != null) {
				mTxtRstName.setText(mPushMsg.getShop().getName());
				mTxtRstAddr.setText(mPushMsg.getShop().getAddress());
			}
			mTxtUsrName.setText(mPushMsg.getAddress().getName());
			mTxtUsrAddr.setText(mPushMsg.getAddress().getDetail());
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
			case R.id.btnCheck:
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
		mSoundPool.setOnLoadCompleteListener(this);
		mSoundId = mSoundPool.load(this, R.raw.neworder_music, 1);
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
		mSoundPool.stop(mStreamId);
		mSoundPool.unload(mSoundId);
		mSoundPool.release();
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

	@Override
	public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		mStreamId = soundPool.play(sampleId, 1.0F, 1.0F, Integer.MAX_VALUE, -1, 1);
	}
}
