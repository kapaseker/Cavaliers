package com.bfdelivery.cavaliers.ui.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;

public class LoginActivity extends BasePageActivity implements View.OnClickListener {

	View mBtnLogin = null;
	EditText mEditUserName = null;
	EditText mEditPasswd = null;
	View mGapLine = null;

	CharSequence mUsrnameSequence = null;
	CharSequence mPasswdSequence = null;

	private static final long DEF_ANIMATE_INTERVAL = 680L;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_login);
		getSupportActionBar().setTitle(R.string.sign_in);

		mBtnLogin = findViewById(R.id.btnLogin);
		mEditUserName = (EditText) findViewById(R.id.edtUsrName);
		mEditPasswd = (EditText) findViewById(R.id.edtPasswd);
		mGapLine = findViewById(R.id.gapLine);


		mBtnLogin.setOnClickListener(this);
		mEditUserName.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mUsrnameSequence = s;
				invalidateControl();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		mEditPasswd.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mPasswdSequence = s;
				invalidateControl();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
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
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnLogin:
				login();
				break;
		}
	}

	private void login() {
		finish();
	}

	private boolean isEnablePasswordInput() {
		return !(mUsrnameSequence == null || mUsrnameSequence.length() < 6);
	}

	private boolean isEnableLoginButton() {
		return !(mUsrnameSequence == null
				|| mUsrnameSequence.length() < 6
				|| mPasswdSequence == null
				|| mPasswdSequence.length() < 6);
	}

	/**
	 * 控件更新机制
	 */
	private void invalidateControl() {


		if (isEnablePasswordInput()) {

			if (mEditPasswd.getVisibility() != View.VISIBLE) {
				mEditPasswd.setVisibility(View.VISIBLE);
				mGapLine.setVisibility(View.VISIBLE);
			}

		} else {

			if (mEditPasswd.getVisibility() == View.VISIBLE) {
				mGapLine.setVisibility(View.GONE);
				mEditPasswd.setVisibility(View.GONE);
			}
		}

		if (isEnableLoginButton()) {

			if (mBtnLogin.getVisibility() != View.VISIBLE) {
				mBtnLogin.setVisibility(View.VISIBLE);
			}

		} else {

			if (mBtnLogin.getVisibility() == View.VISIBLE) {
				mBtnLogin.setVisibility(View.GONE);
			}

		}

	}
}
