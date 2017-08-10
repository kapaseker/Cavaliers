package com.bfdelivery.cavaliers.config;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Panoo on 2017/8/4.
 */

public class CavApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
	}
}