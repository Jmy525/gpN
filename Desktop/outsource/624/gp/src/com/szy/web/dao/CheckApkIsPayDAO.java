package com.szy.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.szy.web.model.CheckIsPayByImei;
import com.szy.web.model.User;

/**
 *@author coolszy
 *@date Feb 23, 2012
 *@blog http://blog.92coding.com
 */
public class CheckApkIsPayDAO {
	SqlManager manager;
	String sql = "";
	ResultSet rs;

	public CheckApkIsPayDAO() throws IOException, ClassNotFoundException {
		manager = SqlManager.createInstance();
	}

	/**
	 * 获取APK安装时间
	 * 
	 * @param nid
	 * @return
	 * @throws SQLException
	 */
	public CheckIsPayByImei getCheckIsPayByImei(String imei)
			throws SQLException {
		CheckIsPayByImei bean = null;
		sql = "select * from installtime where imei=?";
		Object[] params = new Object[] { imei };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next()) {
			String imei0 = rs.getString("imei");
			long installtime = rs.getLong("installtime");
			long endtime = rs.getLong("endtime");
			bean = new CheckIsPayByImei();
			bean.setImei(imei0);
			bean.setInstalltime(installtime);
			bean.setEndtime(endtime);
		}
		manager.closeDB();
		return bean;
	}

	/**
	 * 注册用户
	 * 
	 * @param uname
	 *            用户名
	 * @param password
	 *            密码，电话，邮箱，注册时间
	 * @return
	 * @throws SQLException
	 */
	public boolean installApk(String imei, long installtime, long endtime)
			throws SQLException {
		sql = "INSERT INTO installtime(imei,installtime,endtime) VALUES (?,?,?)";
		Object[] params = new Object[] { imei, installtime, endtime };
		manager.connectDB();
		boolean result = manager.executeUpdate(sql, params);
		manager.closeDB();
		return result;
	}

	/**
	 * 获取APK安装时间
	 * 
	 * @param nid
	 * @return
	 * @throws SQLException
	 */
	public boolean updatePayStateByUserid(String userid, long paytime,
			long endtime) throws SQLException {
		sql = "UPDATE user_table set ispay = ?,paymoney = ?, paytime = ?,endtime = ? where userid = ?";
		Object[] params = new Object[] { "1", "20", paytime, endtime, userid };
		manager.connectDB();
		boolean issuccess = manager.executeUpdate(sql, params);
		manager.closeDB();
		return issuccess;
	}

	public boolean updatePayStateByImei(String imei, long paytime, long endtime)
			throws SQLException {
		sql = "UPDATE user_table set ispay = ?,paymoney = ?, paytime = ?,endtime = ? where imei = ?";
		Object[] params = new Object[] { "1", "20", paytime, endtime, imei };
		manager.connectDB();
		boolean issuccess = manager.executeUpdate(sql, params);
		manager.closeDB();
		return issuccess;
	}
}
