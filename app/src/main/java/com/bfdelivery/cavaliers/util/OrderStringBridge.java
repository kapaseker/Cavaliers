package com.bfdelivery.cavaliers.util;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.constant.DeliveryStatus;

/**
 * Created by Panoo on 2017/8/22.
 */

public class OrderStringBridge {
	public static final int getStatusByOrderStatu(int status) {
		switch (status) {
			case DeliveryStatus.NEW_RECEIVED:
				return (R.string.wait_sure);
			case DeliveryStatus.DEIVERING:
				return (R.string.delivering);
			case DeliveryStatus.WAITING_TAKE:
				return (R.string.wait_take);
			case DeliveryStatus.EXCEPTION:
				return (R.string.order_exception);
			case DeliveryStatus.COMPLETED:
				return (R.string.order_completed);
			default:
				return (R.string.order_unknown);
		}
	}

	public static final int getActionByOrderStatu(int status) {
		switch (status) {
			case DeliveryStatus.NEW_RECEIVED:
				return (R.string.receive_order);
			case DeliveryStatus.DEIVERING:
				return (R.string.complete_order);
			case DeliveryStatus.WAITING_TAKE:
				return (R.string.taken_order);
			case DeliveryStatus.EXCEPTION:
			case DeliveryStatus.COMPLETED:
				return -1;
			default:
				return -1;
		}
	}
}
