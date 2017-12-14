package com.bfdelivery.cavaliers.util;

import com.bfdelivery.cavaliers.background.server.bean.response.OrderList;
import com.bfdelivery.cavaliers.background.server.bean.response.PersonInfoBean;
import com.bfdelivery.cavaliers.database.userinfo.UserInfo;
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
		if (dataBean.getShop() != null) {
			outline.setRstAddr(dataBean.getShop().getAddress());
			outline.setRstName(dataBean.getShop().getName());
		}
		outline.setUsrName(dataBean.getAddress().getName());
		outline.setUsrAddr(dataBean.getAddress().getDetail());

	}

	/**
	 * 将订单列表信息转换为列表的概要信息
	 *
	 * @param dataBean
	 * @param info
	 */
	public static final void personBeanToUserInfo(PersonInfoBean dataBean, UserInfo info) {
		info.setUserId(dataBean.getId());
		info.setEmail(dataBean.getEmail());
		info.setUserName(dataBean.getName());
		info.setUserPhone(dataBean.getMobile());
	}
}
