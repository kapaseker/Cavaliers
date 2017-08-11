package com.bfdelivery.cavaliers.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bfdelivery.cavaliers.ui.fragments.OrderListFragment;

/**
 * Created by Panoo on 2017/7/23.
 */

public class OrderFragmentPageAdapter extends FragmentPagerAdapter {

	CharSequence[] mTitles = null;
	int[] mOrderTypes = null;

	public OrderFragmentPageAdapter(FragmentManager fm, CharSequence[] titles, int[] orderType) {
		super(fm);
		this.mTitles = mTitles;
		this.mOrderTypes = orderType;
	}

	@Override
	public Fragment getItem(int position) {
		return OrderListFragment.newIntance(mOrderTypes[position]);
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
