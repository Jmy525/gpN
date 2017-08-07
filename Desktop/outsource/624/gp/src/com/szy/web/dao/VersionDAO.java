package com.szy.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.szy.web.model.Version;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author coolszy
 * @time Dec 4, 2011 2:52:09 PM
 */
public class VersionDAO {
	SqlManager manager;
	String sql = "";
	ResultSet rs;

	public VersionDAO() throws IOException, ClassNotFoundException, SQLException {
		manager = SqlManager.createInstance();
	}

	/**
	 * 获取当前服务器版本
	 * 
	 * @param uname
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws SQLException
	 */
	public Version getCurrentVersion() throws SQLException {
		Version result = null ;
		sql = "select * from version_update";
		Object[] params = new Object[] { };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			result = new Version();
			result.setVersion(rs.getString("new_version"));
			result.setPath(rs.getString("path"));
			result.setForceupdate(rs.getString("forceupdate"));
			result.setTitle(rs.getString("title"));
			result.setUpdateinfo(rs.getString("updateinfo"));
			result.setUpdatedate(rs.getString("update_time"));
		}
		manager.closeDB();
		return result;
	}

	/**
	 * 根据用户名更新登录时间和状态
	 * 
	 * @param uname
	 *            用户名
	 *            
	 * @return
	 * @throws SQLException
	 */
	public void updateCurrentVersion(String version,String updatetime) throws SQLException {
		sql = "UPDATE version_update set new_version = ?,update_time = ?";
		Object[] params = new Object[] {version, updatetime};
		manager.connectDB();
		manager.executeUpdate(sql, params);
		manager.closeDB();
	}
}
