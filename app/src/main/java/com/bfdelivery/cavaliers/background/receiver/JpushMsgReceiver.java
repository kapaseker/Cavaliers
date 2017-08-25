package com.bfdelivery.cavaliers.background.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bfdelivery.cavaliers.background.database.PreferenceRecorder;
import com.bfdelivery.cavaliers.ui.activities.NewOrderTipActivity;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

public class JpushMsgReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();

		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
			String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
			PreferenceRecorder.saveJpushId(regId);
		} else {
			if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
				processMsg(context, bundle);
			} else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {

			}
		}
	}

	private void processMsg(Context context, Bundle data) {

		String msgContent = data.getString(JPushInterface.EXTRA_MESSAGE);

		Log.d("MSG",msgContent);

		try {
			JSONObject jsonObject = new JSONObject(msgContent);
			String type = jsonObject.getString("type");
			if ("neworder".equals(type)) {
				Intent newMsgIntent = new Intent(context, NewOrderTipActivity.class);
				newMsgIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				newMsgIntent.putExtras(data);
				context.startActivity(newMsgIntent);
			}
		} catch (JSONException e) {

		}

	}
}
