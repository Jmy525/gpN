package com.szy.web.model;

/**
 *@author coolszy
 *@date Feb 23, 2012
 *@blog http://blog.92coding.com
 *新闻评论Model
 */
public class CheckIsPayByImei
{
	private String imei;
	private long installtime;
	private long endtime;
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
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
}
