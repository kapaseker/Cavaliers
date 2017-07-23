package com.bfdelivery.cavaliers.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.OrderDetailActivity;

/**
 * 订单页面
 */

public class OrderListFragment extends Fragment {

	RecyclerView mListOrder = null;
	OrderAdapter mOrderAdapter = null;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_order, container, false);
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	private void initView(View view) {
		mListOrder = (RecyclerView) view.findViewById(R.id.list_order);
		mListOrder.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
		mListOrder.setHasFixedSize(true);

		mOrderAdapter = new OrderAdapter();
		mListOrder.setAdapter(mOrderAdapter);
	}

	private static final class OrderViewHolder extends RecyclerView.ViewHolder {


		public OrderViewHolder(View itemView) {
			super(itemView);
			initViewHolder(itemView);
		}

		private void initViewHolder(View itemView) {
		}

		private void bindData(int position) {
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					itemView.getContext().startActivity(new Intent(itemView.getContext(), OrderDetailActivity.class));
				}
			});
		}
	}

	private static final class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {

		@Override
		public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

			View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_item, parent, false);

			return new OrderViewHolder(itemView);
		}

		@Override
		public void onBindViewHolder(OrderViewHolder holder, int position) {
			holder.bindData(position);
		}

		@Override
		public int getItemCount() {
			return 3;
		}
	}
}
