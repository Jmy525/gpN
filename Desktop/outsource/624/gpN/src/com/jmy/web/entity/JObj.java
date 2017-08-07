package com.jmy.web.entity;

import java.io.Serializable;

public class JObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JObj obj=new JObj();
	public String code;
	public String msg;
	public Object data;
	public JObj() {
		super();
	}
	public JObj(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public JObj(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "JObj [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	private static JObj getInstance(){
		if(obj==null){
			obj=new JObj();
		}
		return obj;
	}
	public static JObj getErrorJobj(String message){
		JObj obj=getInstance();
		obj.setCode("0");
		obj.setMsg(message);
		return obj;
	}
	public static JObj getErrorJobj(){
		JObj obj=getInstance();
		obj.setCode("0");
		obj.setMsg("请求失败!");
		return obj;
	}
	public static JObj getSuccessJobj(){
		JObj obj=getInstance();
		obj.setCode("1");
		obj.setMsg("请求成功!");
		return obj;
	}
	public static JObj getSuccessJobj(String message){
		JObj obj=getInstance();
		obj.setCode("1");
		obj.setMsg(message);
		return obj;
	}
}
