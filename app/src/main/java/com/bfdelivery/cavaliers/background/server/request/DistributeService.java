package com.bfdelivery.cavaliers.background.server.request;

import com.bfdelivery.cavaliers.background.server.bean.request.JPushParam;
import com.bfdelivery.cavaliers.background.server.bean.request.Number;
import com.bfdelivery.cavaliers.background.server.bean.response.OrderDetail;
import com.bfdelivery.cavaliers.background.server.bean.response.OrderList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Panoo on 2017/8/6.
 */

public interface DistributeService {

	@GET("orders")
	Call<OrderList> listOrders(@Query("pager") int pager);

	@GET("order")
	Call<OrderDetail> orderDetail(@Query("number") String number);

	@POST("accepted-orders")
	Call<Void> acceptOrder(@Body Number number);

	@POST("sending-orders")
	Call<Void> sendOrder(@Body Number number);

	@POST("finished-orders")
	Call<Void> completeOrder(@Body Number number);

	@POST("jpushs")
	Call<Void> jpushToken(@Body JPushParam param);
}
