package com.bfdelivery.cavaliers.background.server.request;

import com.bfdelivery.cavaliers.background.server.bean.request.JPushParam;
import com.bfdelivery.cavaliers.background.server.bean.request.OrderNumber;
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
	Call<OrderList> listOrders(@Query("pager") int pager,@Query("status") int status);

	@GET("order")
	Call<OrderDetail> orderDetail(@Query("number") String number);

	@POST("accepted-orders")
	Call<Void> acceptOrder(@Body OrderNumber number);

	@POST("sending-orders")
	Call<Void> sendOrder(@Body OrderNumber number);

	@POST("finished-orders")
	Call<Void> completeOrder(@Body OrderNumber number);

	@POST("jpushs")
	Call<Void> jpushToken(@Body JPushParam param);
}
