package com.szy.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.szy.web.model.Dictionary;
import com.szy.web.model.Word;

/**
 * @author coolszy
 * @time Dec 4, 2011 2:52:09 PM
 */
public class DictionaryDAO {
	SqlManager manager;
	String sql = "";
	ResultSet rs;

	public DictionaryDAO() throws IOException, ClassNotFoundException, SQLException {
		manager = SqlManager.createInstance();
	}

	/**
	 * 根据条件查询词典
	 * @param lang
	 * @param classify
	 * @param keyword
	 * @return
	 * @throws SQLException
	 */
	public List<Dictionary> queryList(String lang,String classify,String keyword) throws SQLException {
		ArrayList<Dictionary> list = new ArrayList<Dictionary>();
		sql = "select a.*,b.wlang english from dictionary a LEFT JOIN dictionary b on b.id=a.ext WHERE 1=1";
		if(!StringUtils.isNullOrEmpty(lang)){
			sql=sql+" and a.lang='"+lang+"'";
		}
		if(!StringUtils.isNullOrEmpty(classify)){
			sql=sql+" and a.classify='"+classify+"'";
		}
		if(!StringUtils.isNullOrEmpty(keyword)){
			sql=sql+" and (a.gp like '%"+keyword+"%' or a.wlang like '%"+keyword+"%')";
		}
		manager.connectDB();
		rs = manager.executeQuery(sql,null);
		while (rs.next()){
			Dictionary dict = new Dictionary();
			dict.setId(rs.getInt("id"));
			dict.setChinese(rs.getString("chinese"));
			dict.setClassify(rs.getString("classify"));
			dict.setWlang(rs.getString("wlang"));
			dict.setGp(rs.getString("gp"));
			dict.setLang(rs.getString("lang"));
			dict.setType(rs.getString("type")+"-"+rs.getString("engtype"));
			dict.setEg(rs.getString("eg"));
			dict.setMandarin(rs.getString("mandarin"));
			dict.setRecordNum(rs.getInt("recordNum"));
			if(rs.getString("english")!=null){
				dict.setEnglish(rs.getString("english"));
			}else{
				dict.setEnglish(this.getEnglish(rs.getString("chinese")));
			}
			list.add(dict);
		}
		manager.closeDB();
		return list;
	}
	
	/**
	 * 根据条件查询词典
	 * @param lang
	 * @param classify
	 * @param keyword
	 * @return
	 * @throws SQLException
	 */
	public List<Word> queryWord(String lang,String keyword) throws SQLException {
		ArrayList<Word> list = new ArrayList<Word>();
		sql = "SELECT * FROM word WHERE 1=1";
		if(!StringUtils.isNullOrEmpty(lang)){
			sql=sql+" and lang='"+lang+"'";
		}
		if(!StringUtils.isNullOrEmpty(keyword)){
			sql=sql+" and (gp like '%"+keyword+"%' or name like '%"+keyword+"%')";
		}
		manager.connectDB();
		rs = manager.executeQuery(sql,null);
		while (rs.next()){
			Word word = new Word();
			word.setId(rs.getInt("id"));
			word.setLang(rs.getString("lang"));
			word.setName(rs.getString("name"));
			word.setGp(rs.getString("gp"));
			word.setLevel(rs.getString("level"));
			list.add(word);
		}
		manager.closeDB();
		return list;
	}
	
	/**
	 * 获取英语
	 * @param chinese 中文
	 * @param id 
	 * @return
	 * @throws SQLException
	 */
	public String getEnglish(String chinese) throws SQLException
	{
		sql = "SELECT * FROM dictionary WHERE lang='英语' and chinese='"+chinese+"'";
		ResultSet rs1 = manager.executeQuery(sql,null);
		String english="";
		if (rs1.next())
		{
			english=rs1.getString("wlang");
		}
		return english;
	}
}
