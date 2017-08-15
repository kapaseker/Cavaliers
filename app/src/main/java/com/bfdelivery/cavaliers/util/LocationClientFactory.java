package com.bfdelivery.cavaliers.util;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;

/**
 * Created by Panoo on 2017/8/15.
 */

public class LocationClientFactory {

	private static final long DEFAULT_LOCATION_INTERVAL = 1 * 60 * 1000L;//1 minute
	private static final long DEFAULT_LOCATION_TIMEOUT = 15 * 1000L;//1 minute

	public static final AMapLocationClient createLocationClient(Context appContext) {
		return createLocationClient(appContext, getDefaultOption());
	}

	public static final AMapLocationClient createLocationClient(Context appContext, AMapLocationClientOption option) {
		AMapLocationClient client = new AMapLocationClient(appContext);
		client.setLocationOption(option);
		return client;
	}

	private static final AMapLocationClientOption getDefaultOption() {
		AMapLocationClientOption option = new AMapLocationClientOption();
		option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
		option.setInterval(DEFAULT_LOCATION_INTERVAL);
		option.setHttpTimeOut(DEFAULT_LOCATION_TIMEOUT);
		option.setLocationCacheEnable(true);
		return option;
	}
}
