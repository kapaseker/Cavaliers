package com.bfdelivery.cavaliers.background.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

public class JpushMsgReceiver extends BroadcastReceiver {

	private static final String TAG = "MSG";

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();

		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
			String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
			Log.d(TAG, "我的注册ID: " + regId);
		} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
			Log.d(TAG, "自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
		} else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
			Log.d(TAG, "Unhandled intent - " + intent.getAction());
		}
	}
}
