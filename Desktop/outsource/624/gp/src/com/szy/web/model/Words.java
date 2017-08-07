package com.szy.web.model;



/**
 *@author coolszy
 *@date Feb 19, 2012
 */
public class Words
{
	private long  wordid;
	private String wordname;
	private String wordmean;
	private String wordgp;

	public Words()
	{
		super();
	}

	public long getWordid() {
		return wordid;
	}

	public void setWordid(long wordid) {
		this.wordid = wordid;
	}

	public String getWordname() {
		return wordname;
	}

	public void setWordname(String wordname) {
		this.wordname = wordname;
	}

	public String getWordmean() {
		return wordmean;
	}

	public void setWordmean(String wordmean) {
		this.wordmean = wordmean;
	}

	public String getWordgp() {
		return wordgp;
	}

	public void setWordgp(String wordgp) {
		this.wordgp = wordgp;
	}

}
