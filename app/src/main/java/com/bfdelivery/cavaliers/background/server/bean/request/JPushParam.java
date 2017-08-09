package com.bfdelivery.cavaliers.background.server.bean.request;

/**
 * Created by Panoo on 2017/8/6.
 */

public class JPushParam {
	private String registration_id;

	public JPushParam(String registration_id) {
		this.registration_id = registration_id;
	}

	public String getRegistration_id() {
		return registration_id;
	}

	public void setRegistration_id(String registration_id) {
		this.registration_id = registration_id;
	}
}
