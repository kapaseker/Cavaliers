package com.bfdelivery.cavaliers.constant;

/**
 * Created by Panoo on 2017/8/22.
 */

public interface CavConfig {
	/**
	 * 配送的判断范围
	 */
	float CAV_COMPLETE_ORDER_DISTANCE = 500F;

	/**
	 * 通知的通道
	 */
	String CHANNEL_NOTIFY = "CavNotifyChannel";

	/**
	 * 通道名称
	 */
	String CHANNEL_NAME = "CavBackService";

	/**
	 * 通道描述信息
	 */
	String CHANNEL_DESCRIPTION = "Cav back service to receive order";

	/**
	 * 定位完成
	 */
	String ACTION_LOCATION_OK = "com.bfdelivery.cavaliers.location";
	/**
	 * 信息更新广播
	 */
	String ACTION_UPDATE_DATA = "com.bfdelivery.cavaliers.update_data";
}
