package com.bfdelivery.cavaliers.ui.background.server.request;

import com.bfdelivery.cavaliers.ui.background.server.config.RequestConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Panoo on 2017/8/6.
 */

public class CavV1Service {

	private static final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
		@Override
		public Response intercept(Chain chain) throws IOException {

			Request originRequest = chain.request();
			Request.Builder requestBuilder = originRequest.newBuilder();

			if ("POST".equals(originRequest.method()) || "PUT".equals(originRequest.method())) {
				requestBuilder.header("Content-Type", "application/json");
			} else {

			}

			requestBuilder.header("Accept", "application/json").build();

			return chain.proceed(requestBuilder.build());
		}
	});

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
