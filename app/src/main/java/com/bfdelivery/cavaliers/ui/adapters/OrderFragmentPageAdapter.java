package com.bfdelivery.cavaliers.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.bfdelivery.cavaliers.constant.DeliveryStatus;
import com.bfdelivery.cavaliers.ui.fragments.NewOrderFragment;
import com.bfdelivery.cavaliers.ui.fragments.OrderListFragment;

/**
 * Created by Panoo on 2017/7/23.
 */

public class OrderFragmentPageAdapter extends RegisterFragmentPagerAdapter {

	String[] mTitles = null;
	int[] mOrderTypes = null;

	public OrderFragmentPageAdapter(FragmentManager fm, String[] titles, int[] orderType) {
		super(fm);
		this.mTitles = titles;
		this.mOrderTypes = orderType;
	}

	@Override
	public Fragment getItem(int position) {
		int orderType = mOrderTypes[position];
		if (orderType == DeliveryStatus.NEW_RECEIVED) {
			return NewOrderFragment.newIntance(orderType);
		}
		return OrderListFragment.newIntance(orderType);
	}

	@Override
	public int getCount() {
		return mTitles.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}
}
