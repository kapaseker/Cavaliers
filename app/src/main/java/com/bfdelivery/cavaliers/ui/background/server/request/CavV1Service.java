package com.bfdelivery.cavaliers.ui.background.server.request;

import com.bfdelivery.cavaliers.ui.background.server.config.RequestConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Panoo on 2017/8/6.
 */

public class CavV1Service {
	private static final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

	private static final Retrofit distributerServiceBuilder = new Retrofit
			.Builder()
			.baseUrl(RequestConfig.DISTRIBUTER_HOST)
			.addConverterFactory(JacksonConverterFactory.create())
			.client(httpClientBuilder.build())
			.build();

	private static final Retrofit authServiceBuilder = distributerServiceBuilder
			.newBuilder()
			.baseUrl(RequestConfig.OAUTH_HOST)
			.build();


	public static final DistributeService createDistributeService() {
		return distributerServiceBuilder.create(DistributeService.class);
	}

	public static final OauthService createOauthService() {
		return authServiceBuilder.create(OauthService.class);
	}
}
