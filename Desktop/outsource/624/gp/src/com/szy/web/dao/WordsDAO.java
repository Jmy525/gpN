package com.szy.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.szy.web.model.User;
import com.szy.web.model.Words;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author coolszy
 * @time Dec 4, 2011 2:52:09 PM
 */
public class WordsDAO {
	SqlManager manager;
	String sql = "";
	ResultSet rs;

	public WordsDAO() throws IOException, ClassNotFoundException, SQLException {
		manager = SqlManager.createInstance();
	}

	public List<Words> getWordListGPByName(String[] wordname) throws SQLException {
		List<Words> listwords = new ArrayList<Words>();
		for (int i = 0; i < wordname.length; i++) {
			sql = "select * from words where englishname=?";
			Object[] params = new Object[] { wordname[i] };
			manager.connectDB();
			rs = manager.executeQuery(sql, params);
			Words result = null;
			if (rs.next()) {
				result = new Words();
				String englishname = rs.getString("englishname");
				String englishgp = rs.getString("englishgp");
				String englishmean = rs.getString("englishmean");
				result.setWordname(englishname);
				result.setWordgp(englishgp);
				result.setWordmean(englishmean);
			}else{
				result = new Words();
				result.setWordname(wordname[i]);
			}
			listwords.add(result);
		}
		manager.closeDB();
		return listwords;
	}

}
