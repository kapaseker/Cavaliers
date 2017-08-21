package com.bfdelivery.cavaliers.util;

import com.bfdelivery.cavaliers.database.location.LocationData;
import com.bfdelivery.cavaliers.database.location.LocationDataService;

/**
 * Created by Panoo on 2017/8/21.
 */

public class LocationSaver {

	private static final LocationSaver _SAVER = new LocationSaver();

	public static LocationSaver instance() {
		return _SAVER;
	}

	public void saveLocation(double latitude, double longitude) {
		saveLocation(new LocationData(null, latitude, longitude, System.currentTimeMillis()));
	}

	public void saveLocation(LocationData data) {
		LocationDataService dataService = LocationDataService.getInstance();
		dataService.deleteAll();
		dataService.insert(data);
	}

	public LocationData getLocation() {
		return LocationDataService.getInstance().first();
	}
}
