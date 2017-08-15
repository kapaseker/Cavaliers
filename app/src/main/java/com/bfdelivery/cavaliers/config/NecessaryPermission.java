package com.bfdelivery.cavaliers.config;

import android.Manifest;

/**
 * Created by Panoo on 2017/8/15.
 */

public class NecessaryPermission {
	/**
	 * 读写外部存储卡
	 */
	public static final String WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
	/**
	 * 获取gps定位信息
	 */
	public static final String FINE_LOCATIION = Manifest.permission.ACCESS_FINE_LOCATION;
	/**
	 * 获取蜂窝网路定位信息
	 */
	public static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
	/**
	 * 读取电话信息
	 */
	public static final String READ_PHONE = Manifest.permission.READ_PHONE_STATE;
	/**
	 * 权限组
	 */
	public static final String[] GROUPS = new String[]{WRITE_STORAGE,
			FINE_LOCATIION,
			COARSE_LOCATION,
			READ_PHONE};
}
