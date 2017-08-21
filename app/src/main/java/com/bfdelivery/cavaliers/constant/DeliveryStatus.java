package com.bfdelivery.cavaliers.constant;

/**
 * 配送状态的状态码
 */

public class DeliveryStatus {
	/**
	 * 刚接受到
	 */
	public static final int NEW_RECEIVED = 1;
	/**
	 * 等待取餐
	 */
	public static final int WAITING_TAKE = 2;
	/**
	 * 配送中
	 */
	public static final int DEIVERING = 3;
	/**
	 * 已完成
	 */
	public static final int COMPLETED = 4;
	/**
	 * 异常状态
	 */
	public static final int EXCEPTION = 5;
}
