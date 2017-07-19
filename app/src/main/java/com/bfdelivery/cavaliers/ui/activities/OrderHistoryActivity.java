package com.bfdelivery.cavaliers.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.fragments.OrderFragment;

public class OrderHistoryActivity extends AppCompatActivity {

    TabLayout mTabs = null;
    ViewPager mPagers = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        initView();
    }

    private void initView() {

        getSupportActionBar().setTitle(R.string.action_menu_history_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTabs = (TabLayout) findViewById(R.id.sliding_tabs);
        mPagers = (ViewPager) findViewById(R.id.viewpager);

        mPagers.setAdapter(new OrderFragmentPageAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.history_order)));
        mTabs.setupWithViewPager(mPagers);
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

    private static class OrderFragmentPageAdapter extends FragmentPagerAdapter {

        CharSequence[] mTitles = null;

        public OrderFragmentPageAdapter(FragmentManager fm, CharSequence[] mTitles) {
            super(fm);
            this.mTitles = mTitles;
        }

        @Override
        public Fragment getItem(int position) {
            return new OrderFragment();
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
