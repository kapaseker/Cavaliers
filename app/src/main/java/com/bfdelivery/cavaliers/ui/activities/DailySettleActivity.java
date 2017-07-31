package com.bfdelivery.cavaliers.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;

public class DailySettleActivity extends BasePageActivity {

	RecyclerView mLstOrder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setTitle(R.string.action_menu_daily_settle);
	}

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_daily_settle);
	}

	@Override
	protected void initView() {
		mLstOrder = (RecyclerView) findViewById(R.id.lst_daily);
		mLstOrder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		mLstOrder.setHasFixedSize(true);
	}

	@Override
	protected void handleData(Bundle data) {

	}

	@Override
	protected void processViewAndData() {
		mLstOrder.setAdapter(new DailyOrderItemAdapter());
	}

	private static final class DailyOrderItemAdapter extends RecyclerView.Adapter<DailyOrderItemAdapter.DailyOrderItemViewHolder> {

		public static class DailyOrderItemViewHolder extends RecyclerView.ViewHolder {

			public DailyOrderItemViewHolder(View itemView) {
				super(itemView);
			}

			public void bindData(int position) {
				itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						itemView.getContext().startActivity(new Intent(itemView.getContext(), OrderDetailActivity.class));
					}
				});
			}
		}

		@Override
		public DailyOrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_daily_order_item, parent, false);
			return new DailyOrderItemViewHolder(itemView);
		}

		@Override
		public void onBindViewHolder(DailyOrderItemViewHolder holder, int position) {
			holder.bindData(position);
		}

		@Override
		public int getItemCount() {
			return 12;
		}

	}
}
