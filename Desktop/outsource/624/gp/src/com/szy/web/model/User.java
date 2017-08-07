package com.szy.web.model;

public class User {
	
	long userid;
	String username;
	String password;
	String phone;
	String avatar;
	int ispay;
	int paytype;
	String paymoney;
	String imei;
	int islogin;
	long logintime;
	long paytime;
	long installtime;
	long endtime;
	long regtime;
	String token;
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIspay() {
		return ispay;
	}
	public void setIspay(int ispay) {
		this.ispay = ispay;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public int getIslogin() {
		return islogin;
	}
	public void setIslogin(int islogin) {
		this.islogin = islogin;
	}
	public long getLogintime() {
		return logintime;
	}
	public void setLogintime(long logintime) {
		this.logintime = logintime;
	}
	public long getPaytime() {
		return paytime;
	}
	public void setPaytime(long paytime) {
		this.paytime = paytime;
	}
	public long getInstalltime() {
		return installtime;
	}
	public void setInstalltime(long installtime) {
		this.installtime = installtime;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	public long getRegtime() {
		return regtime;
	}
	public void setRegtime(long regtime) {
		this.regtime = regtime;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	

}
