package com.szy.web.model;
/**
 * 语言对应关系
 * 汉语-英语-韩语-西班牙语-法语-日语-德语-阿拉伯语-俄语
 * @author Administrator
 *
 */
public class Language {
	private String chinese;//1
	private String english;//2
	private String korean;//3
	private String spanish;//4
	private String french;//5
	private String japanese;//6
	private String german;//7
	private String arbic;//8
	private String russian;//9
	private String englishgp;
	private String koreangp;
	private String spanishgp;
	private String frenchgp;
	private String japanesegp;
	private String germangp;
	private String arbicgp;
	private String russiangp;
	public String getEnglishgp() {
		return englishgp;
	}
	public void setEnglishgp(String englishgp) {
		this.englishgp = englishgp;
	}
	public String getKoreangp() {
		return koreangp;
	}
	public void setKoreangp(String koreangp) {
		this.koreangp = koreangp;
	}
	public String getSpanishgp() {
		return spanishgp;
	}
	public void setSpanishgp(String spanishgp) {
		this.spanishgp = spanishgp;
	}
	public String getFrenchgp() {
		return frenchgp;
	}
	public void setFrenchgp(String frenchgp) {
		this.frenchgp = frenchgp;
	}
	public String getJapanesegp() {
		return japanesegp;
	}
	public void setJapanesegp(String japanesegp) {
		this.japanesegp = japanesegp;
	}
	public String getGermangp() {
		return germangp;
	}
	public void setGermangp(String germangp) {
		this.germangp = germangp;
	}
	public String getArbicgp() {
		return arbicgp;
	}
	public void setArbicgp(String arbicgp) {
		this.arbicgp = arbicgp;
	}

	public String getRussiangp() {
		return russiangp;
	}

	public void setRussiangp(String russiangp) {
		this.russiangp = russiangp;
	}

	public Language(String chinese, String english, String korean,
			String spanish, String french, String japanese, String german,
			String arbic, String russian) {
		super();
		this.chinese = chinese;
		this.english = english;
		this.korean = korean;
		this.spanish = spanish;
		this.french = french;
		this.japanese = japanese;
		this.german = german;
		this.arbic = arbic;
		this.russian = russian;
	}
	
	@Override
	public String toString() {
		return "Language [chinese=" + chinese + ", english=" + english
				+ ", korean=" + korean + ", spanish=" + spanish + ", french="
				+ french + ", japanese=" + japanese + ", german=" + german
				+ ", arbic=" + arbic + ", russian=" + russian + "]";
	}

	public Language() {
		super();
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
	public String getKorean() {
		return korean;
	}
	public void setKorean(String korean) {
		this.korean = korean;
	}
	public String getSpanish() {
		return spanish;
	}
	public void setSpanish(String spanish) {
		this.spanish = spanish;
	}
	public String getFrench() {
		return french;
	}
	public void setFrench(String french) {
		this.french = french;
	}
	public String getJapanese() {
		return japanese;
	}
	public void setJapanese(String japanese) {
		this.japanese = japanese;
	}
	public String getGerman() {
		return german;
	}
	public void setGerman(String german) {
		this.german = german;
	}
	public String getArbic() {
		return arbic;
	}
	public void setArbic(String arbic) {
		this.arbic = arbic;
	}
	public String getRussian() {
		return russian;
	}
	public void setRussian(String russian) {
		this.russian = russian;
	}
}
