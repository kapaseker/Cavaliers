package com.bfdelivery.cavaliers.ui.activities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

/**
 * Created by Panoo on 2017/7/22.
 */

public abstract class BasePageActivity extends BaseActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case android.R.id.home:
				supportFinishAfterTransition();
				return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
