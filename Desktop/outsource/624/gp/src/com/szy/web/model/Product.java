package com.szy.web.model;

public class Product {

	private String startDate;// 开始购买时间
	private String languageName;// 语言名称
	private String languageType;// 1外语 2方言
	private String optionalBuy;// 1 自己定制 2 购买
	private String isProductOverdue;//0不过期 1过期
	private String languageMoney;//金额

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public String getOptionalBuy() {
		return optionalBuy;
	}

	public void setOptionalBuy(String optionalBuy) {
		this.optionalBuy = optionalBuy;
	}

	public String getIsProductOverdue() {
		return isProductOverdue;
	}

	public void setIsProductOverdue(String isProductOverdue) {
		this.isProductOverdue = isProductOverdue;
	}

	public String getLanguageMoney() {
		return languageMoney;
	}

	public void setLanguageMoney(String languageMoney) {
		this.languageMoney = languageMoney;
	}

}
