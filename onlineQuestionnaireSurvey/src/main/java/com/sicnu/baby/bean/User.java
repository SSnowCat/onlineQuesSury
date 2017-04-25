package com.sicnu.baby.bean;


public class User {
	
	private String username;
	
	private String password;
	
	private short userType;
	
	private long userPhone;

	public User() {
	}


	public User(String username, String password, short userType, long userPhone) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.userPhone = userPhone;
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


	public short getUserType() {
		return userType;
	}


	public void setUserType(short userType) {
		this.userType = userType;
	}


	public long getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", userType=" + userType + ", userPhone="
				+ userPhone + "]";
	}
	
	
}
