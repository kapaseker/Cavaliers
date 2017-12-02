package com.bfdelivery.cavaliers.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bfdelivery.cavaliers.R;
import com.bfdelivery.cavaliers.background.server.bean.response.LoginInfo;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;
import com.bfdelivery.cavaliers.util.SignManager;

public class LoginActivity extends BasePageActivity implements View.OnClickListener {

	View mBtnLogin = null;
	EditText mEditUserName = null;
	EditText mEditPasswd = null;
	View mGapLine = null;

	CharSequence mUsrnameSequence = null;
	CharSequence mPasswdSequence = null;

	ProgressDialog mWaittingDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPrepareLayout() {
		setContentView(R.layout.activity_login);
	}

	@Override
	protected void initView() {
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

		SignManager.instance().signIn(mUsrnameSequence.toString(), mPasswdSequence.toString(), new SignManager.OnSignInListener() {
			@Override
			public void onSignStart() {
				mWaittingDialog = ProgressDialog.show(LoginActivity.this, null, getString(R.string.sign_in_ing), true);
			}

			@Override
			public void onSignEnd() {
				mWaittingDialog.dismiss();
			}

			@Override
			public void onError(int erCode, int servCode) {

				int resID = -1;

				switch (erCode) {
					case SignManager.ERROR_WRONG_USER:
						resID = R.string.sign_in_wrong_user;
						break;
					case SignManager.ERROR_UNKOWN:
						resID = R.string.sign_in_fail;
						break;
					default:
						resID = R.string.sign_in_fail;
				}

				String errorMsg = LoginActivity.this.getResources().getString(resID) + "[" + servCode + "]";
				Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onSuccess(LoginInfo result) {
				setResult(Activity.RESULT_OK);
				finish();
			}
		});
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
