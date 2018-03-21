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

		Log.d("MSG", msgContent);

		try {
			JSONObject jsonObject = new JSONObject(msgContent);
			String type = jsonObject.getString("type");
			if ("neworder".equals(type)) {
				if (PreferenceRecorder.needLogin()) return;
				Intent newMsgIntent = new Intent(context, NewOrderTipActivity.class);
				newMsgIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				newMsgIntent.putExtras(data);
				context.startActivity(newMsgIntent);

//				Intent intent = new Intent(context, IndexActivity.class);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
//				intent.putExtra(BundleKeyData.KEY_FROM, BundleKeyData.FROM_NEW_TIP);
//
//				PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
//						PendingIntent.FLAG_ONE_SHOT);
//
//				String title = context.getResources().getString(R.string.new_order_tip);
//				String content = context.getResources().getString(R.string.click_receive);
//
//				NotifyHelper.simpleNotify(context, (int) (System.currentTimeMillis() % 10000),
//						title, title, content, pendingIntent);
			}
		} catch (JSONException e) {

		}

	}
}
