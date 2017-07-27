package com.bfdelivery.cavaliers.ui.activities.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by Panoo on 2017/7/23.
 */

public abstract class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		onPrepareLayout();
		initView();
		handleData(getIntent().getExtras());
		processViewAndData();
	}

	protected abstract void onPrepareLayout();

	protected abstract void initView();

	protected abstract void handleData(Bundle data);

	protected abstract void processViewAndData();
}
