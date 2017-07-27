package com.xiangjia.base.model;

import com.xiangjia.enums.UserType;

public class ShiroUser {
	private Long id;
	private String userName;
	private String photo;
    private UserType userType;
    private String account;
	public ShiroUser() {
		super();
	}


	public ShiroUser(Long id,String account, String userName, String photo, UserType userType) {
		super();
		this.id = id;
		this.userName = userName;
		this.photo = photo;
		this.userType = userType;
		this.account = account;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}
}
