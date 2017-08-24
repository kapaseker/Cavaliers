package com.bfdelivery.cavaliers.background.server.bean.response;

/**
 * Created by Panoo on 2017/8/23.
 */

public class PersonInfoBean {


	/**
	 * id : 1
	 * registration_id : 140fe1da9e9fb3fd8fa
	 * total_orders : 0
	 * today_done_orders : 0
	 * today_sending_orders : -1
	 * deleted_at : null
	 * created_at : null
	 * updated_at : 2017-08-23 17:25:58
	 * today_amount : 0
	 * today_wechat_amount : 0
	 * today_offline_amount : 0
	 * total_amount : 0
	 * total_offline_amount : 0
	 * total_wechat_amount : 0
	 * name : 王大海
	 * email : test@163.com
	 * mobile : 18999999999
	 */

	private String id;
	private String registration_id;
	private int total_orders;
	private int today_done_orders;
	private int today_sending_orders;
	private String deleted_at;
	private String created_at;
	private String updated_at;
	private int today_amount;
	private int today_wechat_amount;
	private int today_offline_amount;
	private int total_amount;
	private int total_offline_amount;
	private int total_wechat_amount;
	private String name;
	private String email;
	private String mobile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegistration_id() {
		return registration_id;
	}

	public void setRegistration_id(String registration_id) {
		this.registration_id = registration_id;
	}

	public int getTotal_orders() {
		return total_orders;
	}

	public void setTotal_orders(int total_orders) {
		this.total_orders = total_orders;
	}

	public int getToday_done_orders() {
		return today_done_orders;
	}

	public void setToday_done_orders(int today_done_orders) {
		this.today_done_orders = today_done_orders;
	}

	public int getToday_sending_orders() {
		return today_sending_orders;
	}

	public void setToday_sending_orders(int today_sending_orders) {
		this.today_sending_orders = today_sending_orders;
	}

	public String getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(String deleted_at) {
		this.deleted_at = deleted_at;
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

	public int getToday_amount() {
		return today_amount;
	}

	public void setToday_amount(int today_amount) {
		this.today_amount = today_amount;
	}

	public int getToday_wechat_amount() {
		return today_wechat_amount;
	}

	public void setToday_wechat_amount(int today_wechat_amount) {
		this.today_wechat_amount = today_wechat_amount;
	}

	public int getToday_offline_amount() {
		return today_offline_amount;
	}

	public void setToday_offline_amount(int today_offline_amount) {
		this.today_offline_amount = today_offline_amount;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public int getTotal_offline_amount() {
		return total_offline_amount;
	}

	public void setTotal_offline_amount(int total_offline_amount) {
		this.total_offline_amount = total_offline_amount;
	}

	public int getTotal_wechat_amount() {
		return total_wechat_amount;
	}

	public void setTotal_wechat_amount(int total_wechat_amount) {
		this.total_wechat_amount = total_wechat_amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
