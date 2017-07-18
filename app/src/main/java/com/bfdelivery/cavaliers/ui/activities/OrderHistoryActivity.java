package com.bfdelivery.cavaliers.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        mTabs = (TabLayout) findViewById(R.id.sliding_tabs);
        mPagers = (ViewPager) findViewById(R.id.viewpager);

        mPagers.setAdapter(new OrderFragmentPageAdapter(getSupportFragmentManager()));
        mTabs.setupWithViewPager(mPagers);
    }

    private static class OrderFragmentPageAdapter extends FragmentPagerAdapter {

        public OrderFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new OrderFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Index #" + position;
        }
    }
}
