package com.bfdelivery.cavaliers.background.callbacks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Panoo on 2017/8/11.
 */

public abstract class BaseCallback<T> implements Callback<T> {
	@Override
	public void onResponse(Call<T> call, Response<T> response) {
		onComplete();
	}

	@Override
	public void onFailure(Call<T> call, Throwable t) {
		onComplete();
	}

	public abstract void onComplete();
}
