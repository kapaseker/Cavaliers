package com.bfdelivery.cavaliers.util;

import com.bfdelivery.cavaliers.background.callbacks.BaseCallback;
import com.bfdelivery.cavaliers.background.server.bean.response.PersonInfoBean;
import com.bfdelivery.cavaliers.background.server.config.HttpStatus;
import com.bfdelivery.cavaliers.background.server.request.CavV1Service;
import com.bfdelivery.cavaliers.database.userinfo.UserInfo;
import com.bfdelivery.cavaliers.database.userinfo.UserInfoDataService;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Panoo on 2017/8/23.
 */

public class UserInfoManager {

	public static interface OnUserInfoListener {
		void onUserInfoReceived(UserInfo info);

		void onBegin();
	}

	private static final UserInfoManager _SELFT = new UserInfoManager();

	public static final UserInfoManager instance() {
		return _SELFT;
	}

	public UserInfo getUserInfo() {
		return UserInfoDataService.getInstance().first();
	}

	public void forceFetchUserInfo(final OnUserInfoListener listener) {
		if (listener != null) {
			listener.onBegin();
		}
		Call<PersonInfoBean> personInfo = CavV1Service.createDistributeService().personInfo();
		personInfo.enqueue(new BaseCallback<PersonInfoBean>() {
			@Override
			public void onResponse(Call<PersonInfoBean> call, Response<PersonInfoBean> response) {
				super.onResponse(call, response);
				if (response.code() == HttpStatus.SC_OK) {
					UserInfo info = new UserInfo();
					DataBridge.personBeanToUserInfo(response.body(), info);
					UserInfoDataService.getInstance().saveUserInfo(info);
					if (listener != null) {
						listener.onUserInfoReceived(info);
					}
				} else {
					if (listener != null) {
						listener.onUserInfoReceived(null);
					}
				}
			}

			@Override
			public void onFailure(Call<PersonInfoBean> call, Throwable t) {
				super.onFailure(call, t);
				if (listener != null) {
					listener.onUserInfoReceived(null);
				}
			}

			@Override
			public void onComplete() {

			}
		});
	}

	public void fetchUserInfo(final OnUserInfoListener listener) {

		if (listener == null) return;

		UserInfo info = getUserInfo();
		if (info != null) {
			listener.onBegin();
			listener.onUserInfoReceived(info);
		} else {
			forceFetchUserInfo(listener);
		}
	}
}
