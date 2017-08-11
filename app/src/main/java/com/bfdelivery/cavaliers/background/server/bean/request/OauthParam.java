package com.bfdelivery.cavaliers.background.server.bean.request;

/**
 * Created by Panoo on 2017/8/11.
 */

public class OauthParam {
	private String grant_type = "password";
	private int client_id = 1;
	private String client_secret = "Ag0M4YtsdHazoGRiLbjJ6Fbe6PX2zOL0aZaiGWCP";
	private String username = "";
	private String password = "";

	public OauthParam() {
	}

	public OauthParam(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public OauthParam(String grant_type, int client_id, String client_secret, String username, String password) {
		this.grant_type = grant_type;
		this.client_id = client_id;
		this.client_secret = client_secret;
		this.username = username;
		this.password = password;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
