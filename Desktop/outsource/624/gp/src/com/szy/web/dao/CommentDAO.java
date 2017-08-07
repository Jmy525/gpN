package com.szy.web.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.szy.web.model.Comment;

/**
 *@author coolszy
 *@date Feb 23, 2012
 *@blog http://blog.92coding.com
 */
public class CommentDAO
{
	SqlManager manager;
	String sql = "";
	ResultSet rs;
	
	public CommentDAO() throws IOException, ClassNotFoundException
	{
		manager = SqlManager.createInstance();
	}
	
	/**
	 * 保存新反馈
	 * @param userid 反馈用户id
	 * @param ptime 反馈时间
	 * @param feedid 地区
	 * @param content 内容
	 * @throws SQLException
	 */
	public boolean addComment(int userid,String ptime,String content,String userinfo) throws SQLException
	{
		sql = "INSERT INTO feed_back(userid,feed_time,feed_content,userinfo) VALUES (?,?,?,?)";
		Object[] params = new Object[] { userid, ptime,content,userinfo };
		manager.connectDB();
		boolean issuccess = manager.executeUpdate(sql, params);
		manager.closeDB();
		return issuccess;
	}
	
	/**
	 * 获取用户的反馈数量
	 * @param nid
	 * @return
	 * @throws SQLException
	 */
	public long getFeedBackCount(int userid) throws SQLException
	{
		long count = 0;
		sql = "select count(feed_id) as count from feed_back where feed_user_id=?";
		Object[] params = new Object[]{ userid };
		manager.connectDB();
		rs = manager.executeQuery(sql, params);
		if (rs.next())
		{
			count = rs.getLong("count");
		}
		return count;
	}
}
