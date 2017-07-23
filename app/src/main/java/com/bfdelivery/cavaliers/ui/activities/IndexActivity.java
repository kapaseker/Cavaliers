package com.bfdelivery.cavaliers.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startActivity(new Intent(this, LoginActivity.class));
	}

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

		navigationView.getHeaderView(0).findViewById(R.id.userCenter).setOnClickListener(this);
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
		// Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.index, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_history) {
			startActivity(new Intent(this, OrderHistoryActivity.class));
		}

		mDrawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.userCenter:
				mDrawer.closeDrawer(GravityCompat.START);
				startActivity(new Intent(this, UserCenterActivity.class));
				break;
		}
	}
}
