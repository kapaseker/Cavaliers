package com.bfdelivery.cavaliers.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.constant.CavConfig;

import org.apache.commons.lang3.StringUtils;

/**
 * 通知提醒工具
 */

public class NotifyHelper {

	/**
	 * 简单的通知
	 */
	public static void simpleNotify(Context context, int notifyId, String ticker, String title, String message, PendingIntent intent) {

		Notification notification = createNotifyCation(context, ticker, title, message, intent);

		notify(context, notifyId, notification);
	}

	/**
	 * 创建通知
	 *
	 * @param context
	 * @param ticker
	 * @param title
	 * @param message
	 * @param intent
	 * @return
	 */
	public static Notification createNotifyCation(Context context, String ticker, String title, String message, PendingIntent intent) {

		NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(context, CavConfig.CHANNEL_NOTIFY);

		notifiBuilder
				.setSmallIcon(R.mipmap.ic_notify_mian)
				.setWhen(System.currentTimeMillis())
				.setDefaults(Notification.DEFAULT_ALL);

		if (!StringUtils.isEmpty(ticker)) {
			notifiBuilder.setTicker(ticker);
		}

		if (!StringUtils.isEmpty(title)) {
			notifiBuilder.setContentTitle(title);
		}

		if (!StringUtils.isEmpty(message)) {
			notifiBuilder.setContentText(message);
		}

		if (intent != null) {
			notifiBuilder.setContentIntent(intent);
		}

		return notifiBuilder.build();
	}

	/**
	 * 提醒通知
	 *
	 * @param context
	 * @param notifyId
	 * @param notification
	 */
	private static final void notify(Context context, int notifyId, Notification notification) {

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel notifyChannel = new NotificationChannel(CavConfig.CHANNEL_NOTIFY, CavConfig.CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
			notifyChannel.setDescription(CavConfig.CHANNEL_DESCRIPTION);
			notificationManager.createNotificationChannel(notifyChannel);
		}

		notificationManager.notify(notifyId, notification);
	}

	/**
	 * 持续通知
	 *
	 * @param context
	 * @param notifyId
	 * @param ticker
	 * @param title
	 * @param message
	 * @param intent
	 */
	public static void onGoingNotify(Context context, int notifyId, String ticker, String title, String message, PendingIntent intent) {

		Notification notification = createNotifyCation(context, ticker, title, message, intent);
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		notification.flags |= Notification.FLAG_NO_CLEAR;

		notify(context, notifyId, notification);
	}

}
