package com.bfdelivery.cavaliers.util;

import com.bfdelivery.cavaliers.background.server.bean.response.OrderList;
import com.bfdelivery.cavaliers.dataset.ListOutlineData;

/**
 * 数据桥接
 * 用户多项数据转换
 */

public class DataBridge {

	/**
	 * 将订单列表信息转换为列表的概要信息
	 *
	 * @param dataBean
	 * @param outline
	 */
	public static final void dataBeanToListOutLine(OrderList.DataBean dataBean, ListOutlineData outline) {

		outline.setOrderId(dataBean.getNumber());
		outline.setRstAddr("");
		outline.setRstName("");
		outline.setUsrName(dataBean.getAddress().getName());
		outline.setUsrAddr(dataBean.getAddress().getDetail());

	}
}
