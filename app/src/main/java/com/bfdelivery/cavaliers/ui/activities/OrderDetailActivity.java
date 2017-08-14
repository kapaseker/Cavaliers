package com.bfdelivery.cavaliers.ui.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.server.bean.response.OrderDetail;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.background.server.request.DistributeService;
import com.bfdelivery.cavaliers.dataset.ListOutlineData;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;
import com.bfdelivery.cavaliers.ui.views.OrderDetailItemView;

import retrofit2.Call;
import retrofit2.Response;

public class OrderDetailActivity extends BasePageActivity {

	public static final String BUNDLE_KEY_POSTION = "OrderDetailActivity.index";
	public static final String BUNDLE_KEY_LISTDATA = "OrderDetailActivity.listData";

	ListView mListCommodity = null;
	OrderDetailItemView mCommodityItem = null;
	View mWrapperCommodity = null;

	View mWrapperWating = null;
	View mWrapperDeatilInfo = null;

	DistributeService mDistributeService = null;

	private int mIndex = 0;
	private ListOutlineData mOutlineData = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDistributeService = CavV1Service.createDistributeService();

		requestOrderDetial();
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
		mWrapperWating = findViewById(R.id.wrapper_waiting);
		mWrapperDeatilInfo = findViewById(R.id.wrapper_detail);
	}

	@Override
	protected void handleData(Bundle data) {
		mIndex = data.getInt(BUNDLE_KEY_POSTION);
		mOutlineData = data.getParcelable(BUNDLE_KEY_LISTDATA);
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

	private void requestOrderDetial() {
		
		Call<OrderDetail> detail = mDistributeService.orderDetail(mOutlineData.getOrderId());
		detail.enqueue(new BaseCallback<OrderDetail>() {

			@Override
			public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {
				super.onResponse(call, response);

				if (response.code() == HttpStatus.SC_OK) {

				} else {

				}
			}

			@Override
			public void onComplete() {
				mWrapperWating.setVisibility(View.GONE);
				mWrapperDeatilInfo.setVisibility(View.VISIBLE);
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
