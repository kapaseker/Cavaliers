package com.bfdelivery.cavaliers.ui.background.server.bean.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Panoo on 2017/8/6.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderList {


	/**
	 * list : [{"number":"sring","shop_id":"string","shop_name":"string","status":"int","pay_status":"int","pay_type":"int","product_amount":"number","service_amount":"number","total_amount":"number","coupon_amount":"number","pay_amount":"number","remark":"string","distribute_status":"int"}]
	 * pager : 0
	 */

	private int pager;
	private List<ListBean> list;

	public int getPager() {
		return pager;
	}

	public void setPager(int pager) {
		this.pager = pager;
	}

	public List<ListBean> getList() {
		return list;
	}

	public void setList(List<ListBean> list) {
		this.list = list;
	}

	public static class ListBean {
		/**
		 * number : sring
		 * shop_id : string
		 * shop_name : string
		 * status : int
		 * pay_status : int
		 * pay_type : int
		 * product_amount : number
		 * service_amount : number
		 * total_amount : number
		 * coupon_amount : number
		 * pay_amount : number
		 * remark : string
		 * distribute_status : int
		 */

		private String number;
		private String shop_id;
		private String shop_name;
		private int status;
		private int pay_status;
		private int pay_type;
		private double product_amount;
		private double service_amount;
		private double total_amount;
		private double coupon_amount;
		private double pay_amount;
		private String remark;
		private int distribute_status;

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getShop_id() {
			return shop_id;
		}

		public void setShop_id(String shop_id) {
			this.shop_id = shop_id;
		}

		public String getShop_name() {
			return shop_name;
		}

		public void setShop_name(String shop_name) {
			this.shop_name = shop_name;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getPay_status() {
			return pay_status;
		}

		public void setPay_status(int pay_status) {
			this.pay_status = pay_status;
		}

		public int getPay_type() {
			return pay_type;
		}

		public void setPay_type(int pay_type) {
			this.pay_type = pay_type;
		}

		public double getProduct_amount() {
			return product_amount;
		}

		public void setProduct_amount(double product_amount) {
			this.product_amount = product_amount;
		}

		public double getService_amount() {
			return service_amount;
		}

		public void setService_amount(double service_amount) {
			this.service_amount = service_amount;
		}

		public double getTotal_amount() {
			return total_amount;
		}

		public void setTotal_amount(double total_amount) {
			this.total_amount = total_amount;
		}

		public double getCoupon_amount() {
			return coupon_amount;
		}

		public void setCoupon_amount(double coupon_amount) {
			this.coupon_amount = coupon_amount;
		}

		public double getPay_amount() {
			return pay_amount;
		}

		public void setPay_amount(double pay_amount) {
			this.pay_amount = pay_amount;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public int getDistribute_status() {
			return distribute_status;
		}

		public void setDistribute_status(int distribute_status) {
			this.distribute_status = distribute_status;
		}
	}
}
