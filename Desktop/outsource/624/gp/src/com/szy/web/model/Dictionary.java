package com.szy.web.model;

/**
 *@author miaofei
 *@date 2015-08-02
 *
 * 外语词典Dictionary
 */
public class Dictionary
{
	private int id;
	private String lang;//语言
	private String classify;//分类  
	private String type;//类型
	private String chinese;//汉
	private String english;//英文
	
	private String wlang;//外语
	private String gp;//GP/方言单词
	private String eg;//方言例句
	private String mandarin;//普通话
	private Integer recordNum;//录音编号

	public Dictionary(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getWlang() {
		return wlang;
	}

	public void setWlang(String wlang) {
		this.wlang = wlang;
	}

	public String getGp() {
		return gp;
	}

	public void setGp(String gp) {
		this.gp = gp;
	}

	public String getEg() {
		return eg;
	}

	public void setEg(String eg) {
		this.eg = eg;
	}

	public String getMandarin() {
		return mandarin;
	}

	public void setMandarin(String mandarin) {
		this.mandarin = mandarin;
	}

	public Integer getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(Integer recordNum) {
		this.recordNum = recordNum;
	}

}
