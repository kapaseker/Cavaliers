package com.bfdelivery.cavaliers.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.database.PreferenceRecorder;
import com.bfdelivery.cavaliers.background.server.bean.request.JPushParam;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.background.server.request.DistributeService;
import com.bfdelivery.cavaliers.config.LocationErrorCode;
import com.bfdelivery.cavaliers.config.NecessaryPermission;
import com.bfdelivery.cavaliers.constant.DeliveryStatus;
import com.bfdelivery.cavaliers.ui.activities.base.BaseActivity;
import com.bfdelivery.cavaliers.ui.adapters.OrderFragmentPageAdapter;
import com.bfdelivery.cavaliers.util.LocationClientFactory;
import com.bfdelivery.cavaliers.util.LocationSaver;
import com.cmccmap.permissionchecker.PermissionChecker;
import com.cmccmap.permissionchecker.PermissionRequestor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndexActivity extends BaseActivity
		implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AMapLocationListener {

	TabLayout mTabs = null;
	ViewPager mPagers = null;
	DrawerLayout mDrawer = null;

	View mPortraitView = null;
	View mNameView = null;
	View mDescriptionVew = null;

	DistributeService mDistributeService = null;

	private static final int REQUEST_LOGIN = 1;

	private static final int REQUEST_PERMISSION = 2;

	private AMapLocationClient mLocationClient = null;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDistributeService = CavV1Service.createDistributeService();
		mLocationClient = LocationClientFactory.createLocationClient(this);
		mLocationClient.setLocationListener(this);

		if (PreferenceRecorder.needLogin()) {
			// login
			startActivityForResult(new Intent(this, LoginActivity.class), REQUEST_LOGIN);
		} else {
			afterLogin();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (!mLocationClient.isStarted()
				&&
				PackageManager.PERMISSION_GRANTED == PermissionChecker.checkSelfPermission(this, NecessaryPermission.FINE_LOCATIION)) {
			mLocationClient.startLocation();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mLocationClient.isStarted()) {
			mLocationClient.stopLocation();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mLocationClient.onDestroy();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
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
		mPagers.setAdapter(new OrderFragmentPageAdapter(getSupportFragmentManager(),
				getResources().getStringArray(R.array.index_order_list), new int[]{DeliveryStatus.NEW_RECEIVED, DeliveryStatus.WAITING_TAKE, DeliveryStatus.DEIVERING}));
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.userCenter:
				startActivity(new Intent(this, UserCenterActivity.class));
				mDrawer.closeDrawer(GravityCompat.START);
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
			case REQUEST_LOGIN:

				if (resultCode == Activity.RESULT_OK) {

					mDistributeService.jpushToken(new JPushParam(PreferenceRecorder.getJpushId())).enqueue(new Callback<Void>() {
						@Override
						public void onResponse(Call<Void> call, Response<Void> response) {

						}

						@Override
						public void onFailure(Call<Void> call, Throwable t) {

						}
					});

					afterLogin();

				} else {
					finish();
				}

				return;

		}


		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

		switch (requestCode) {

			case REQUEST_PERMISSION:

				if (!PermissionChecker.hasDeniedPermission(grantResults)) {
					afterPermissionGranted();
				}

				break;

		}

		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}

	@Override
	public void onLocationChanged(AMapLocation aMapLocation) {

		if (aMapLocation.getErrorCode() == LocationErrorCode.OK) {
			LocationSaver.instance().saveLocation(aMapLocation.getLatitude(), aMapLocation.getLongitude());
		} else {

		}

		Log.d("AMAPPOS", aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());
	}

	private void afterLogin() {
		processNecessaryPermission();
	}

	private void processNecessaryPermission() {

		String[] rationalePermission = PermissionChecker.extractRationalePermission(this, NecessaryPermission.GROUPS);

		if (rationalePermission.length > 0) {

			new AlertDialog.Builder(this).setPositiveButton(R.string.i_know, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					requestNecessaryPermission();
				}

			}).setTitle(R.string.title_permission_request).setMessage(R.string.permission_necessary_tip).show();

		} else {
			requestNecessaryPermission();
		}
	}

	private void requestNecessaryPermission() {
		if (!PermissionRequestor.reqeustPermissionInAct(this, NecessaryPermission.GROUPS, REQUEST_PERMISSION)) {
			afterPermissionGranted();
		}
	}

	private void afterPermissionGranted() {
		mLocationClient.startLocation();
	}

	private ActivityOptionsCompat createUserCenterShareOption() {

		Pair<View, String> headPair = Pair.create(mPortraitView, getString(R.string.transition_head));
		Pair<View, String> namePair = Pair.create(mNameView, getString(R.string.transition_title));
		Pair<View, String> descPair = Pair.create(mDescriptionVew, getString(R.string.transition_desc));

		return ActivityOptionsCompat.makeSceneTransitionAnimation(this, headPair, namePair, descPair);
	}


}
