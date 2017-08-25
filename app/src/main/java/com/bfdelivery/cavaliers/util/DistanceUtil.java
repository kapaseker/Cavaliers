package com.bfdelivery.cavaliers.util;

import android.content.Context;

import com.bfdelivery.cavaliers.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Panoo on 2017/8/21.
 */

public class DistanceUtil {
	public static final String formatDistance(Context context, float meter) {

		String strDistance = "";

		if (meter >= 1000F) {
			DecimalFormat decimalFormat = new DecimalFormat("#.#");
			decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);
			strDistance = context.getString(R.string.suffix_km, decimalFormat.format(meter / 1000f));
		} else {
			strDistance = context.getString(R.string.suffix_m, "" + ((int) meter));
		}
		return strDistance;
	}
}
