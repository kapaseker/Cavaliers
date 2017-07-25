package com.bfdelivery.cavaliers.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.base.BaseActivity;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;

public class DailySettleActivity extends BasePageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPrepareLayout() {
        setContentView(R.layout.activity_daily_settle);
        getSupportActionBar().setTitle(R.string.action_menu_daily_settle);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void handleData(Bundle data) {

    }

    @Override
    protected void processViewAndData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dailysettle,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
