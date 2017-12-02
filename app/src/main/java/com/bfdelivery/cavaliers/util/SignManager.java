package com.bfdelivery.cavaliers.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.database.PreferenceRecorder;
import com.bfdelivery.cavaliers.background.server.bean.request.OauthParam;
import com.bfdelivery.cavaliers.background.server.bean.response.LoginInfo;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.background.server.request.OauthService;
import com.bfdelivery.cavaliers.ui.activities.IndexActivity;
import com.bfdelivery.cavaliers.ui.activities.LoginActivity;

import retrofit2.Call;
import retrofit2.Response;

/**
 * 用户的登陆管理器
 */

public class SignManager {

	public static final int ERROR_WRONG_USER = 1;
	public static final int ERROR_UNKOWN = 2;

	private static final SignManager _INSTANCE = new SignManager();

	public static final SignManager instance() {
		return _INSTANCE;
	}

	public static interface OnSignInListener {
		void onSignStart();

		void onSignEnd();

		void onError(int erCode, int servCode);

		void onSuccess(LoginInfo result);
	}

	/**
	 * 重新登录
	 *
	 * @param context
	 */
	public void reSignIn(Context context) {

		Intent logIntent = new Intent(context, LoginActivity.class);
		ComponentName topComponent = CavActivityLifeCallback.instance().getTopActivity();

		/**
		 * 检测当前的顶层activity是否是登录界面
		 */
		if (topComponent != null && topComponent.toShortString().equals(logIntent.getComponent().toShortString())) {
			return;
		}

		/**
		 * 检测是否已经在登陆中了，毕竟有时候会跳得很快
		 */
//		if (PreferenceRecorder.needLogin()) {
//			return;
//		}

		PreferenceRecorder.saveAccessToken("");
		Intent intent = new Intent(context, IndexActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}

	public void signIn(String user, String password, final OnSignInListener listener) {
		OauthService service = CavV1Service.createOauthService();
		Call<LoginInfo> request = service.login(new OauthParam(user, password));

		request.enqueue(new BaseCallback<LoginInfo>() {

			@Override
			public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
				super.onResponse(call, response);

				if (response.code() == HttpStatus.SC_OK) {

					LoginInfo result = response.body();
					PreferenceRecorder.saveAccessToken(result.getAccess_token());
					if (listener != null) {
						listener.onSuccess(result);
					}
				} else if (response.code() == HttpStatus.SC_UNAUTHORIZED) {
					onError(ERROR_WRONG_USER, response.code());
				} else {
					onError(ERROR_UNKOWN, response.code());
				}
			}

			@Override
			public void onFailure(Call<LoginInfo> call, Throwable t) {
				super.onFailure(call, t);
				onError(ERROR_UNKOWN, -1);
			}

			public void onError(int erCode, int servCode) {
				if (listener != null) {
					listener.onError(erCode, servCode);
				}
			}

			@Override
			public void onComplete() {
				if (listener != null) {
					listener.onSignEnd();
				}
			}
		});

		if (listener != null) {
			listener.onSignStart();
		}
	}
}
