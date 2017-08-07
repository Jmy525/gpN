package com.jmy.web.entity;

import java.io.Serializable;

public class Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String userid;
	private String english;
	public Note() {
		super();
	}
	public Note(String userid, String english) {
		super();
		this.userid = userid;
		this.english = english;
	}
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
