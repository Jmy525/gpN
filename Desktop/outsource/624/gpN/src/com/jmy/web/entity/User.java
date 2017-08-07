package com.jmy.web.entity;

import java.io.Serializable;

public class User implements Serializable{
	private long userid;
	private String username;
	private String password;
	private String phone;
	private String avatar;
	private int ispay;
	private int paytype;
	private String paymoney;
	
	private String imei;
	private int islogin;
	
	private long logintime;
	private long paytime;
	
	private long installtime;
	
	private long endtime;
	private long regtime;
	private String token;
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
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", password=" + password + ", phone=" + phone + ", avatar="
				+ avatar + ", ispay=" + ispay + ", paytype=" + paytype
				+ ", paymoney=" + paymoney + ", imei=" + imei + ", islogin="
				+ islogin + ", logintime=" + logintime + ", paytime=" + paytime
				+ ", installtime=" + installtime + ", endtime=" + endtime
				+ ", regtime=" + regtime + ", token=" + token + "]";
	}
}
