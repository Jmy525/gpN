package com.szy.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.szy.web.model.Language;

public class LanguageDao {
	SqlManager manager;
	String sql = "";
	ResultSet rs;

	public LanguageDao() throws IOException, ClassNotFoundException {
		manager = SqlManager.createInstance();
	}
	//select * from language where english like a ?
	public Language translate(String type,String word) throws SQLException{
		sql="select * from language where "+type+" like ?";
		Object[] params = new Object[]{word};
		Language language=null;
		manager.connectDB();
		//System.out.println(sql);
		rs = manager.executeQuery(sql, params);
		if(rs.next()){
			language=new Language();
			language.setEnglish(rs.getString("english"));
			language.setKorean(rs.getString("korean"));
			language.setSpanish(rs.getString("spanish"));
			language.setFrench(rs.getString("french"));
			language.setJapanese(rs.getString("japanese"));
			language.setGerman(rs.getString("german"));
			language.setArbic(rs.getString("arbic"));
			language.setRussian(rs.getString("russian"));
			language.setKoreangp(rs.getString("koreangp"));
			language.setSpanishgp(rs.getString("spanishgp"));
			language.setFrenchgp(rs.getString("frenchgp"));
			language.setJapanesegp(rs.getString("japanesegp"));
			language.setGermangp(rs.getString("germangp"));
			language.setArbicgp(rs.getString("arbicgp"));
			language.setRussiangp(rs.getString("russiangp"));
		}
		manager.closeDB();
		return language;
	}
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
		LanguageDao dao=new LanguageDao();
		Language l=dao.translate("arbic", "واحد");
		System.out.println(l.toString());
	}
}
