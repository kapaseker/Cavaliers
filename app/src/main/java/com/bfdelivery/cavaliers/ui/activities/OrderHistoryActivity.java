package com.bfdelivery.cavaliers.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;
import com.bfdelivery.cavaliers.ui.adapters.OrderFragmentPageAdapter;

public class OrderHistoryActivity extends BasePageActivity {

	TabLayout mTabs = null;
	ViewPager mPagers = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_order_history);
	}

	@Override
	protected void initView() {

		getSupportActionBar().setTitle(R.string.action_menu_history_order);

		mTabs = (TabLayout) findViewById(R.id.sliding_tabs);
		mPagers = (ViewPager) findViewById(R.id.viewpager);

		mPagers.setAdapter(new OrderFragmentPageAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.history_order)));
		mTabs.setupWithViewPager(mPagers);
	}

	@Override
	protected void handleData(Bundle data) {

	}

	@Override
	protected void processViewAndData() {

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
