package com.bfdelivery.cavaliers.ui.activities.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Panoo on 2017/7/23.
 */

public abstract class BaseActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onPrepareLayout();
		initView();
		handleData(getIntent().getExtras());
		processViewAndData();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		handleData(intent.getExtras());
		processViewAndData();
	}

	protected abstract void onPrepareLayout();

	protected abstract void initView();

	protected abstract void handleData(Bundle data);

	protected abstract void processViewAndData();
}
