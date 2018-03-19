package com.bfdelivery.cavaliers.ui.activities;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.database.PreferenceRecorder;
import com.bfdelivery.cavaliers.background.server.bean.request.JPushParam;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.background.server.request.DistributeService;
import com.bfdelivery.cavaliers.config.LocationErrorCode;
import com.bfdelivery.cavaliers.config.NecessaryPermission;
import com.bfdelivery.cavaliers.constant.BundleKeyData;
import com.bfdelivery.cavaliers.constant.CavConfig;
import com.bfdelivery.cavaliers.constant.DeliveryStatus;
import com.bfdelivery.cavaliers.database.userinfo.UserInfo;
import com.bfdelivery.cavaliers.ui.activities.base.BaseActivity;
import com.bfdelivery.cavaliers.ui.adapters.OrderFragmentPageAdapter;
import com.bfdelivery.cavaliers.util.LocationClientFactory;
import com.bfdelivery.cavaliers.util.LocationSaver;
import com.bfdelivery.cavaliers.util.UserInfoManager;
import com.cmccmap.permissionchecker.PermissionChecker;
import com.cmccmap.permissionchecker.PermissionRequestor;

public class IndexActivity extends BaseActivity
		implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AMapLocationListener {

	TabLayout mTabs = null;
	ViewPager mPagers = null;
	DrawerLayout mDrawer = null;

	View mPortraitView = null;
	TextView mNameView = null;
	TextView mDescriptionVew = null;

	DistributeService mDistributeService = null;

	private static final int ON_GOING_NOTIFY = 2;

	private static final int REQUEST_LOGIN = 1;

	private static final int REQUEST_PERMISSION = 2;

	private static final int REQUEST_INDEX_ACT = 3;

	private AMapLocationClient mLocationClient = null;

	NotificationManager mNotifyManager = null;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDistributeService = CavV1Service.createDistributeService();
		mLocationClient = LocationClientFactory.createLocationClient(this);
		mLocationClient.setLocationListener(this);
		mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		addNotify();

		afterCreate();
		processNewIntent(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		afterCreate();
		notifyOrderUpdate();
		processNewIntent(intent);
	}

	private void processNewIntent(Intent intent) {
		int where = intent.getIntExtra(BundleKeyData.KEY_FROM, 0);
		if (where == BundleKeyData.FROM_NEW_TIP) {
			mPagers.setCurrentItem(0);
		}
	}

	/**
	 * 通知订单更新
	 */
	private void notifyOrderUpdate() {
		sendBroadcast(new Intent(CavConfig.ACTION_UPDATE_DATA));
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
		mLocationClient.onDestroy();
//		removeNotify();
		super.onDestroy();
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
		mNameView = (TextView) headView.findViewById(R.id.usrName);
		mDescriptionVew = (TextView) headView.findViewById(R.id.usrDescription);

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

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.index, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onPrepareOptionsMenu(Menu menu) {
//		return super.onPrepareOptionsMenu(menu);
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		int id = item.getItemId();
//
//		if (id == R.id.action_msg) {
//			return true;
//		}
//
//		return super.onOptionsItemSelected(item);
//	}

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
					afterLogin();
				} else {
					finish();
				}

				return;

		}


		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showExitTip();
			return true;
		}

		return super.onKeyDown(keyCode, event);
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
			sendBroadcast(new Intent(CavConfig.ACTION_LOCATION_OK));
		} else {

		}

		Log.d("AMAPPOS", aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());
	}

	private void afterCreate() {

		if (PreferenceRecorder.needLogin()) {
			// login
			Intent loginIntent = new Intent(this, LoginActivity.class);
			loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(loginIntent, REQUEST_LOGIN);
		} else {
			afterLogin();
		}

	}

	private void afterLogin() {
		processNecessaryPermission();
		UserInfoManager.instance().forceFetchUserInfo(new UserInfoManager.OnUserInfoListener() {
			@Override
			public void onUserInfoReceived(UserInfo info) {
				if (info != null) {
					updateUserInfo(info);
				}
			}

			@Override
			public void onBegin() {

			}
		});

		mDistributeService.jpushToken(new JPushParam(PreferenceRecorder.getJpushId())).enqueue(new BaseCallback<Void>() {
			@Override
			public void onComplete() {

			}
		});
	}

	private void updateUserInfo(UserInfo info) {
		mDescriptionVew.setText(info.getUserPhone());
		mNameView.setText(info.getUserName());
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
		Pair<View, String> namePair = Pair.create((View) mNameView, getString(R.string.transition_title));
		Pair<View, String> descPair = Pair.create((View) mDescriptionVew, getString(R.string.transition_desc));

		return ActivityOptionsCompat.makeSceneTransitionAnimation(this, headPair, namePair, descPair);
	}

	private void showExitTip() {
		new AlertDialog.Builder(this).setTitle(R.string.title_exit).setMessage(R.string.desc_exit)
				.setNegativeButton(R.string.brutality_exit, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						finish();
					}
				})
				.setPositiveButton(R.string.waiting_for_order, null)
				.show();
	}

	private void addNotify() {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel notifyChannel = new NotificationChannel(CavConfig.CHANNEL_NOTIFY, CavConfig.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
			notifyChannel.setDescription(CavConfig.CHANNEL_DESCRIPTION);
			mNotifyManager.createNotificationChannel(notifyChannel);
		}

		NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, CavConfig.CHANNEL_NOTIFY);

		Intent intent = new Intent(this, IndexActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

		notifyBuilder.setSmallIcon(R.mipmap.ic_notify_mian)
				.setContentTitle(getString(R.string.app_name))
				.setContentIntent(PendingIntent.getActivity(this, REQUEST_INDEX_ACT, intent, PendingIntent.FLAG_UPDATE_CURRENT))
				.setContentText(getString(R.string.desc_background_run))
				.setPriority(NotificationCompat.PRIORITY_HIGH);

		Notification notify = notifyBuilder.build();
		notify.flags |= Notification.FLAG_ONGOING_EVENT;
		notify.flags |= Notification.FLAG_NO_CLEAR;

		mNotifyManager.notify(ON_GOING_NOTIFY, notify);
	}

	private void removeNotify() {
		mNotifyManager.cancel(ON_GOING_NOTIFY);
	}
}
