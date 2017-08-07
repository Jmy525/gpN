package com.szy.web.model;

public class Note 
{
	private String id;
	private String userid;
	private String english;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	@Override
	public String toString() {
		return "Note [id=" + id + ", userid=" + userid + ", english=" + english
				+ "]";
	}
	
}
