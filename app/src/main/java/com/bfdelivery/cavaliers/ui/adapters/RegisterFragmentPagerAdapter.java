package com.bfdelivery.cavaliers.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * 可以通过位置下标获得当前的fragment类 
 * @see RegisterFragmentPagerAdapter#getRegisteredFragment
 * */
public abstract class RegisterFragmentPagerAdapter extends FragmentPagerAdapter {
	
	/**
	 * 保存注册fragment数组
	 * */
	private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
	public RegisterFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	/**
	 * 初始化fragment
	 * */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Fragment fragment = (Fragment) super.instantiateItem(container, position);
		registeredFragments.put(position, fragment);
		return fragment;
	}
	/**
	 * 销毁fragment
	 * */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
	
		registeredFragments.remove(position);
		super.destroyItem(container, position, object);
	}
	
	/**
	 * get a fragment by index
	 * @return fragment or null
	 * */
	public Fragment getRegisteredFragment(int postion){
		return registeredFragments.get(postion);
	}
}
