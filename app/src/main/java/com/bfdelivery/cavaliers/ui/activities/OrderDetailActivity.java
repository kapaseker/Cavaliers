package com.bfdelivery.cavaliers.ui.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;
import com.bfdelivery.cavaliers.ui.views.OrderDetailItemView;

public class OrderDetailActivity extends BasePageActivity {

	ListView mListCommodity = null;
	OrderDetailItemView mCommodityItem = null;
	View mWrapperCommodity = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_order_detail);
	}

	@Override
	protected void initView() {
		mListCommodity = (ListView) findViewById(R.id.lstCommodities);
		mCommodityItem = (OrderDetailItemView) findViewById(R.id.itemCommodity);
		mWrapperCommodity = findViewById(R.id.wrapperCommodity);
	}

	@Override
	protected void handleData(Bundle data) {

	}

	@Override
	protected void processViewAndData() {

		mListCommodity.setAdapter(new CommodityAdapter());
		mCommodityItem.setChecked(true);

		mCommodityItem.setOnItemCheckListener(new OrderDetailItemView.OnItemCheckChangeListener() {

			@Override
			public void onCheckedChanged(OrderDetailItemView itemView, boolean checked) {
				mWrapperCommodity.setVisibility(checked ? View.VISIBLE : View.GONE);
			}
		});

	}

	private static class CommodityAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 7;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_commodity_item, parent, false);
			} else {

			}

			return convertView;
		}
	}
}
