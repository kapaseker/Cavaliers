package com.bfdelivery.cavaliers.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bfdelivery.cavaliers.R;

/**
 * Created by Administrator on 2018/3/19.
 */

public class NewOrderFragment extends OrderListFragment implements View.OnClickListener {

	private View mBtnRefresh = null;

	public static NewOrderFragment newIntance(int orderType) {
		NewOrderFragment fragment = new NewOrderFragment();

		Bundle data = new Bundle();
		data.putInt(BUNDLE_KEY_ORDERTYPE, orderType);
		fragment.setArguments(data);

		return fragment;
	}

	@Override
	public int getLayoutResource() {
		return R.layout.fragment_new_order;
	}

	@Override
	protected void initView(View view) {
		super.initView(view);

		mBtnRefresh = view.findViewById(R.id.warpper_refresh);
		mBtnRefresh.setOnClickListener(this);
	}

	@Override
	protected void onRequestDone() {
		super.onRequestDone();

		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.btn_refresh_enter);
		mBtnRefresh.setAnimation(animation);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				mBtnRefresh.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});

		animation.start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.warpper_refresh:
				requestOrder();
				break;
		}
	}

	@Override
	protected void requestOrder() {
		super.requestOrder();

		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.btn_refresh_leave);
		mBtnRefresh.setAnimation(animation);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mBtnRefresh.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});

		animation.start();
	}
}
