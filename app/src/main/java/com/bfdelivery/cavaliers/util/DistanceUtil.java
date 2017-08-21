package com.bfdelivery.cavaliers.util;

import android.content.Context;

import com.bfdelivery.cavaliers.R;

/**
 * Created by Panoo on 2017/8/21.
 */

public class DistanceUtil {
	public static final String formatDistance(Context context, float meter) {

		String strDistance = "";

		if (meter >= 1000F) {
			strDistance = context.getString(R.string.suffix_km, "" + ((int) meter) / 1000f);
		} else {
			strDistance = context.getString(R.string.suffix_m, "" + ((int) meter));
		}
		return strDistance;
	}
}
