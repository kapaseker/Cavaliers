package com.bfdelivery.cavaliers.background.server.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */

public class DailyInfoBean {

	/**
	 * current_page : 1
	 * data : [{"id":118,"distributer_id":4,"order_num":11,"wechat_amount":72600,"offline_amount":0,"total_amount":72600,"date":"2018-02-26","created_at":"2018-02-27 00:10:02","updated_at":"2018-02-27 00:10:02"},{"id":120,"distributer_id":12,"order_num":3,"wechat_amount":3,"offline_amount":0,"total_amount":3,"date":"2018-02-26","created_at":"2018-02-27 00:10:02","updated_at":"2018-02-27 00:10:02"},{"id":119,"distributer_id":11,"order_num":11,"wechat_amount":61500,"offline_amount":0,"total_amount":61500,"date":"2018-02-26","created_at":"2018-02-27 00:10:02","updated_at":"2018-02-27 00:10:02"},{"id":121,"distributer_id":13,"order_num":2,"wechat_amount":23800,"offline_amount":0,"total_amount":23800,"date":"2018-02-26","created_at":"2018-02-27 00:10:02","updated_at":"2018-02-27 00:10:02"},{"id":117,"distributer_id":10,"order_num":1,"wechat_amount":1,"offline_amount":0,"total_amount":1,"date":"2018-02-25","created_at":"2018-02-26 00:10:01","updated_at":"2018-02-26 00:10:01"},{"id":116,"distributer_id":4,"order_num":7,"wechat_amount":34700,"offline_amount":0,"total_amount":34700,"date":"2018-02-25","created_at":"2018-02-26 00:10:01","updated_at":"2018-02-26 00:10:01"},{"id":113,"distributer_id":10,"order_num":12,"wechat_amount":84600,"offline_amount":0,"total_amount":84600,"date":"2018-02-24","created_at":"2018-02-25 00:10:01","updated_at":"2018-02-25 00:10:01"},{"id":115,"distributer_id":12,"order_num":3,"wechat_amount":41,"offline_amount":0,"total_amount":41,"date":"2018-02-24","created_at":"2018-02-25 00:10:01","updated_at":"2018-02-25 00:10:01"},{"id":114,"distributer_id":11,"order_num":11,"wechat_amount":69700,"offline_amount":0,"total_amount":69700,"date":"2018-02-24","created_at":"2018-02-25 00:10:01","updated_at":"2018-02-25 00:10:01"},{"id":112,"distributer_id":1,"order_num":3,"wechat_amount":20000,"offline_amount":0,"total_amount":20000,"date":"2018-02-24","created_at":"2018-02-25 00:10:01","updated_at":"2018-02-25 00:10:01"},{"id":111,"distributer_id":2,"order_num":4,"wechat_amount":23100,"offline_amount":0,"total_amount":23100,"date":"2018-02-23","created_at":"2018-02-24 00:10:01","updated_at":"2018-02-24 00:10:01"},{"id":110,"distributer_id":1,"order_num":9,"wechat_amount":49100,"offline_amount":0,"total_amount":49100,"date":"2018-02-23","created_at":"2018-02-24 00:10:01","updated_at":"2018-02-24 00:10:01"},{"id":108,"distributer_id":2,"order_num":6,"wechat_amount":39000,"offline_amount":0,"total_amount":39000,"date":"2018-02-11","created_at":"2018-02-12 00:10:01","updated_at":"2018-02-12 00:10:01"},{"id":109,"distributer_id":7,"order_num":12,"wechat_amount":110200,"offline_amount":0,"total_amount":110200,"date":"2018-02-11","created_at":"2018-02-12 00:10:01","updated_at":"2018-02-12 00:10:01"},{"id":107,"distributer_id":7,"order_num":10,"wechat_amount":169200,"offline_amount":0,"total_amount":169200,"date":"2018-02-10","created_at":"2018-02-11 00:10:01","updated_at":"2018-02-11 00:10:01"}]
	 * from : 1
	 * last_page : 9
	 * next_page_url : http://bf.laowaishuosha.com/v1/distributer/distributer-statistics?page=2
	 * path : http://bf.laowaishuosha.com/v1/distributer/distributer-statistics
	 * per_page : 15
	 * prev_page_url : null
	 * to : 15
	 * total : 121
	 */

	private int current_page;
	private int from;
	private int last_page;
	private String next_page_url;
	private String path;
	private int per_page;
	private String prev_page_url;
	private int to;
	private int total;
	private List<DataBean> data;

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getLast_page() {
		return last_page;
	}

	public void setLast_page(int last_page) {
		this.last_page = last_page;
	}

	public String getNext_page_url() {
		return next_page_url;
	}

	public void setNext_page_url(String next_page_url) {
		this.next_page_url = next_page_url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getPer_page() {
		return per_page;
	}

	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}

	public String getPrev_page_url() {
		return prev_page_url;
	}

	public void setPrev_page_url(String prev_page_url) {
		this.prev_page_url = prev_page_url;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * id : 118
		 * distributer_id : 4
		 * order_num : 11
		 * wechat_amount : 72600
		 * offline_amount : 0
		 * total_amount : 72600
		 * date : 2018-02-26
		 * created_at : 2018-02-27 00:10:02
		 * updated_at : 2018-02-27 00:10:02
		 */

		private int id;
		private int distributer_id;
		private int order_num;
		private int wechat_amount;
		private int offline_amount;
		private int total_amount;
		private String date;
		private String created_at;
		private String updated_at;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getDistributer_id() {
			return distributer_id;
		}

		public void setDistributer_id(int distributer_id) {
			this.distributer_id = distributer_id;
		}

		public int getOrder_num() {
			return order_num;
		}

		public void setOrder_num(int order_num) {
			this.order_num = order_num;
		}

		public int getWechat_amount() {
			return wechat_amount;
		}

		public void setWechat_amount(int wechat_amount) {
			this.wechat_amount = wechat_amount;
		}

		public int getOffline_amount() {
			return offline_amount;
		}

		public void setOffline_amount(int offline_amount) {
			this.offline_amount = offline_amount;
		}

		public int getTotal_amount() {
			return total_amount;
		}

		public void setTotal_amount(int total_amount) {
			this.total_amount = total_amount;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public String getUpdated_at() {
			return updated_at;
		}

		public void setUpdated_at(String updated_at) {
			this.updated_at = updated_at;
		}
	}
}
