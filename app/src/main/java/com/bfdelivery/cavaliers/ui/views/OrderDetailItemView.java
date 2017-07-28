package com.bfdelivery.cavaliers.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bfdelivery.cavaliers.R;

/**
 * Created by Panoo on 2017/7/23.
 */

public class OrderDetailItemView extends LinearLayout {

	TextView mTxtTag = null;
	TextView mTxtDetail = null;

	public OrderDetailItemView(Context context) {
		super(context);
		initView(context, null);
	}

	public OrderDetailItemView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	public OrderDetailItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public OrderDetailItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		initView(context, attrs);
	}

	private void initView(Context context, @Nullable AttributeSet attrs) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.layout_orderdetail, this);
		mTxtTag = (TextView) itemView.findViewById(R.id.tag);
		mTxtDetail = (TextView) itemView.findViewById(R.id.detail);

		if (attrs != null) {
			TypedArray styleAttr = context.obtainStyledAttributes(attrs, R.styleable.OrderDetailView);
			try {
				String strDetail = styleAttr.getString(R.styleable.OrderDetailView_detail);
				String strTag = styleAttr.getString(R.styleable.OrderDetailView_tag);
				int detailColor = styleAttr.getColor(R.styleable.OrderDetailView_detailColor, context.getResources().getColor(R.color.colorTxtDetail));

				mTxtTag.setText(strTag);
				mTxtDetail.setText(strDetail);
				mTxtDetail.setTextColor(detailColor);
			} finally {
				styleAttr.recycle();
			}
		}
	}
}
