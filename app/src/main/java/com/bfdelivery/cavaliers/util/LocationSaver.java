package com.bfdelivery.cavaliers.util;

import com.bfdelivery.cavaliers.database.location.LocationData;
import com.bfdelivery.cavaliers.database.location.LocationDataService;

/**
 * 定位位置保存器
 */

public class LocationSaver {

	private static final LocationSaver _SAVER = new LocationSaver();

	public static LocationSaver instance() {
		return _SAVER;
	}

	public void saveLocation(double latitude, double longitude) {
		saveLocation(new LocationData(null, latitude, longitude, 0L));
	}

	public LocationSaver saveLocation(LocationData data) {
		LocationDataService.getInstance().saveLocation(data);
		return this;
	}

	public LocationData getLocation() {
		return LocationDataService.getInstance().first();
	}
}
