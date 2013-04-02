package com.microblog.core.web;


public class SubscriptionJson {

	private Integer idUser;
	private String userName;

	public SubscriptionJson() {
	}

	public SubscriptionJson(Integer idUser,String user) {
		this.idUser = idUser;
		this.userName = user;
	}
	
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = Integer.parseInt(idUser);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String user) {
		this.userName = user;
	}
}
