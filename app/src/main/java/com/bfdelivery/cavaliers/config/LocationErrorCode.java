package com.bfdelivery.cavaliers.config;

/**
 * 定位失败的错误码
 */
public interface LocationErrorCode {
	/**
	 * 定位成功
	 */
	int OK = 0;
	/**
	 * 一些重要参数为空，如context
	 */
	int ERROR_NULL_PARAM = 1;
	/**
	 * 定位失败，由于仅扫描到单个wifi，且没有基站信息。
	 */
	int ERROR_SINGLE_WIFI = 2;
	/**
	 * 获取到的请求参数为空，可能获取过程中出现异常。
	 */
	int ERROR_REQUEST = 3;
	/**
	 * 请求服务器过程中的异常，多为网络情况差，链路不通导致
	 */
	int ERROR_CONNECTION = 4;
	/**
	 * 请求被恶意劫持，定位结果解析失败。
	 */
	int ERROR_PARSE_RESULT = 5;
	/**
	 * 定位服务返回定位失败。
	 */
	int ERROR_SERVER = 6;
	/**
	 * KEY鉴权失败。
	 */
	int ERROR_KEY = 7;
	/**
	 * Android exception常规错误
	 */
	int ERROR_EXCEPTION = 8;
	/**
	 * 定位初始化时出现异常。
	 */
	int ERROR_INIT = 9;
	/**
	 * 定位客户端启动失败。
	 */
	int ERROR_NO_SERVICE = 10;
	/**
	 * 定位时的基站信息错误。
	 */
	int ERROR_BASE = 11;
	/**
	 * 缺少定位权限。
	 */
	int ERROR_LACK_PERMISSION = 12;
	/**
	 * 定位失败，由于未获得WIFI列表和基站信息，且GPS当前不可用。
	 */
	int ERROR_DEVICE = 13;
	/**
	 * GPS 定位失败，由于设备当前 GPS 状态差。
	 */
	int ERROR_GPS_LOCATION = 14;
	/**
	 * 定位结果被模拟导致定位失败
	 */
	int ERROR_MOCK = 15;
	/**
	 * 当前POI检索条件、行政区划检索条件下，无可用地理围栏
	 */
	int ERROR_RETRIEVE = 16;
	/**
	 * 定位失败，由于手机WIFI功能被关闭同时设置为飞行模式
	 */
	int ERROR_PLANEMODE = 17;
	/**
	 * 定位失败，由于手机没插sim卡且WIFI功能被关闭
	 */
	int ERROR_NO_SIM = 18;
}
