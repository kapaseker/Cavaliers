package com.bfdelivery.cavaliers.util;

/**
 * Created by Administrator on 2018/3/21.
 */

public class TimeUtil {
	public static final String convertDoubleFormatTime(int time) {
		return time < 10 ? "0" + time : time + "";
	}
}
