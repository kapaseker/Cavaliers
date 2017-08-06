package com.bfdelivery.cavaliers.ui.background.server.request;


import com.bfdelivery.cavaliers.ui.background.server.bean.response.LoginInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Panoo on 2017/8/6.
 */

public interface OauthService {

	@GET("token?grant_type=password")
	Call<LoginInfo> login(@Query("grant_type") String grantType
			, @Query("client_id") String clientId
			, @Query("client_secret") String secret
			, @Query("username") String usr
			, @Query("password") String pswd
	);
}
