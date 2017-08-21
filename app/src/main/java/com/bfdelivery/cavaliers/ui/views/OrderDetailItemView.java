package com.bfdelivery.cavaliers.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bfdelivery.cavaliers.R;

/**
 * Created by Panoo on 2017/7/23.
 */

public class OrderDetailItemView extends LinearLayout implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

	TextView mTxtTag = null;
	TextView mTxtDetail = null;
	CheckBox mCheckBox = null;
	OnItemCheckChangeListener mItemCheckChangeListener = null;

	public static interface OnItemCheckChangeListener {
		void onCheckedChanged(OrderDetailItemView itemView, boolean checked);
	}

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
		mCheckBox = (CheckBox) itemView.findViewById(R.id.ckbox);

		if (attrs != null) {
			TypedArray styleAttr = context.obtainStyledAttributes(attrs, R.styleable.OrderDetailView);
			try {
				String strDetail = styleAttr.getString(R.styleable.OrderDetailView_detail);
				String strTag = styleAttr.getString(R.styleable.OrderDetailView_tag);
				int detailColor = styleAttr.getColor(R.styleable.OrderDetailView_detailColor, context.getResources().getColor(R.color.colorTxtDetail));
				int tagColor = styleAttr.getColor(R.styleable.OrderDetailView_tagColor, context.getResources().getColor(R.color.colorTxtDefault));

				mTxtTag.setText(strTag);
				mTxtDetail.setText(strDetail);

				mTxtTag.setTextColor(tagColor);
				mTxtDetail.setTextColor(detailColor);
			} finally {
				styleAttr.recycle();
			}
		}

		this.setOnClickListener(this);
		mCheckBox.setOnCheckedChangeListener(this);
	}

	public void setOnItemCheckListener(OnItemCheckChangeListener listener) {
		mCheckBox.setVisibility(View.VISIBLE);
		mItemCheckChangeListener = listener;
	}

	public void setTagColor(int color) {
		mTxtTag.setTextColor(color);
	}

	public void setDetailColor(int color) {
		mTxtDetail.setTextColor(color);
	}

	public void setTag(int res) {
		mTxtTag.setText(res);
	}

	public void setTag(CharSequence tag) {
		mTxtTag.setText(tag);
	}

	public void setDetail(int res) {
		mTxtDetail.setText(res);
	}

	public void setDetail(CharSequence detail) {
		mTxtDetail.setText(detail);
	}

	/**
	 * 设置尾项是否勾选
	 *
	 * @param isChecked
	 */
	public void setChecked(boolean isChecked) {
		mCheckBox.setChecked(isChecked);
	}

	@Override
	public void onClick(View v) {
		boolean isChecked = !mCheckBox.isChecked();
		mCheckBox.setChecked(isChecked);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (mItemCheckChangeListener != null) {
			mItemCheckChangeListener.onCheckedChanged(this, isChecked);
		}
	}
}
