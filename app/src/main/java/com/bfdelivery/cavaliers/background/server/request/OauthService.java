package com.bfdelivery.cavaliers.background.server.request;


import com.bfdelivery.cavaliers.background.server.bean.request.OauthParam;
import com.bfdelivery.cavaliers.background.server.bean.response.LoginInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 鉴权服务
 */

public interface OauthService {

	/**
	 * 登陆接口
	 *
	 * @param param
	 * @return
	 */
	@POST("token")
	Call<LoginInfo> login(@Body OauthParam param);
}
