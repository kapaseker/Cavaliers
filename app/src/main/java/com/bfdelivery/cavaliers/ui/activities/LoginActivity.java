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
import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.database.PreferenceRecorder;
import com.bfdelivery.cavaliers.background.server.bean.request.OauthParam;
import com.bfdelivery.cavaliers.background.server.bean.response.LoginInfo;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.background.server.request.OauthService;
import com.bfdelivery.cavaliers.ui.activities.base.BasePageActivity;

import retrofit2.Call;
import retrofit2.Response;

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

		OauthService service = CavV1Service.createOauthService();
		Call<LoginInfo> request = service.login(new OauthParam(mUsrnameSequence.toString(), mPasswdSequence.toString()));
		mWaittingDialog = ProgressDialog.show(this, null, getString(R.string.sign_in_ing), true);
		request.enqueue(new BaseCallback<LoginInfo>() {

			@Override
			public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
				super.onResponse(call, response);

				if (response.code() == HttpStatus.SC_OK) {

					LoginInfo result = response.body();
					PreferenceRecorder.saveAccessToken(result.getAccess_token());
					setResult(Activity.RESULT_OK);
					finish();

				} else if (response.code() == HttpStatus.SC_UNAUTHORIZED) {
					onError(R.string.sign_in_wrong_user);
					mEditPasswd.setText("");
				} else {
					onError(R.string.sign_in_fail);
				}
			}

			@Override
			public void onFailure(Call<LoginInfo> call, Throwable t) {
				super.onFailure(call, t);
				onError(R.string.sign_in_fail);
			}

			public void onError(int resID) {
				Toast.makeText(LoginActivity.this, resID, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete() {
				mWaittingDialog.dismiss();
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
