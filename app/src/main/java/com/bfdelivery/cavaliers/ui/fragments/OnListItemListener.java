package com.bfdelivery.cavaliers.ui.fragments;

import com.bfdelivery.cavaliers.background.server.bean.response.OrderList;

/**
 * Created by Panoo on 2017/8/21.
 */

public interface OnListItemListener {
	void onDetail(int position, OrderList.DataBean dataBean);

	void onAction(int position, OrderList.DataBean dataBean);
}
