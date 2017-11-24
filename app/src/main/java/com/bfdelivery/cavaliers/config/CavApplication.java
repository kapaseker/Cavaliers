package com.bfdelivery.cavaliers.config;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.bfdelivery.cavaliers.background.database.PreferenceRecorder;
import com.bfdelivery.cavaliers.database.location.LocationDataService;
import com.bfdelivery.cavaliers.database.userinfo.UserInfoDataService;
import com.bfdelivery.cavaliers.log.CrashLogger;
import com.bfdelivery.cavaliers.util.CavActivityLifeCallback;

import cn.jpush.android.api.JPushInterface;

/**
 * 主Application
 */

public class CavApplication extends MultiDexApplication {

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(base);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
		PreferenceRecorder.init(this);

		LocationDataService.init(this);
		UserInfoDataService.init(this);

		registerActivityLifecycleCallbacks(CavActivityLifeCallback.instance());

		CrashLogger.getInstance().init(getApplicationContext());
	}
}
