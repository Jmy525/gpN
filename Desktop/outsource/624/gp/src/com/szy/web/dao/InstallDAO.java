package com.szy.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author coolszy
 * @time Dec 4, 2011 2:52:09 PM
 */
public class InstallDAO {
	SqlManager manager;
	String sql = "";
	ResultSet rs;

	public InstallDAO() throws IOException, ClassNotFoundException, SQLException {
		manager = SqlManager.createInstance();
	}
	/**
	 * 保存用户手机的imei和安装时间installtime， 注意：只能根据用户名更新密码
	 * 
	 * @param uname
	 *            用户名
	 * @param password
	 *            新密码
	 * @return
	 * @throws SQLException
	 */
	public boolean saveimei(String imei, String installtime)
			throws SQLException {
		boolean result = false;
		sql = "insert into installtime(imei,installtime)values(?,?)";
		Object[] params = new Object[] { imei, installtime };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next() && rs.getLong(1) == 1) {
			result = true;
		}
		manager.closeDB();
		return result;
	}
}
