package com.bfdelivery.cavaliers.background.server.bean.response;

/**
 * Created by Panoo on 2017/8/23.
 */

public class PersonInfoBean {

	/**
	 * id : 1
	 * name : 王大海
	 * mobile : 18999999999
	 * email : test@163.com
	 * score : 400
	 * order_num : 0
	 * last_order_at : null
	 * created_at : null
	 * updated_at : null
	 */

	private String id;
	private String name;
	private String mobile;
	private String email;
	private int score;
	private int order_num;
	private String last_order_at;
	private String created_at;
	private String updated_at;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public String getLast_order_at() {
		return last_order_at;
	}

	public void setLast_order_at(String last_order_at) {
		this.last_order_at = last_order_at;
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
