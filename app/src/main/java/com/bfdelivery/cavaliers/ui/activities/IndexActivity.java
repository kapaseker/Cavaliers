package com.bfdelivery.cavaliers.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.base.BaseActivity;
import com.bfdelivery.cavaliers.ui.adapters.OrderFragmentPageAdapter;

public class IndexActivity extends BaseActivity
		implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

	TabLayout mTabs = null;
	ViewPager mPagers = null;
	DrawerLayout mDrawer = null;

	View mPortraitView = null;
	View mNameView = null;
	View mDescriptionVew = null;

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_index);
	}

	@Override
	protected void initView() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		mDrawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		mTabs = (TabLayout) findViewById(R.id.sliding_tabs);
		mPagers = (ViewPager) findViewById(R.id.viewpager);

		View headView = navigationView.getHeaderView(0);

		mPortraitView = headView.findViewById(R.id.imgHeadPortrait);
		mNameView = headView.findViewById(R.id.usrName);
		mDescriptionVew = headView.findViewById(R.id.usrDescription);

		headView.setOnClickListener(this);
	}

	@Override
	protected void handleData(Bundle data) {

	}

	@Override
	protected void processViewAndData() {
		mPagers.setAdapter(new OrderFragmentPageAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.index_order_list)));
		mTabs.setupWithViewPager(mPagers);
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.index, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_msg) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.nav_history) {
			startActivity(new Intent(this, OrderHistoryActivity.class));
		} else if (id == R.id.nav_dailysettle) {
			ActivityCompat.startActivity(this, new Intent(this, DailySettleActivity.class), createUserCenterShareOption().toBundle());
		}

		mDrawer.closeDrawer(GravityCompat.START);
		return true;
	}

	private ActivityOptionsCompat createUserCenterShareOption() {

		Pair<View, String> headPair = Pair.create(mPortraitView, getString(R.string.transition_head));
		Pair<View, String> namePair = Pair.create(mNameView, getString(R.string.transition_title));
		Pair<View, String> descPair = Pair.create(mDescriptionVew, getString(R.string.transition_desc));

		return ActivityOptionsCompat.makeSceneTransitionAnimation(this, headPair, namePair, descPair);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.userCenter:
				startActivity(new Intent(this, UserCenterActivity.class));
				mDrawer.closeDrawer(GravityCompat.START);
				break;
		}
	}
}
