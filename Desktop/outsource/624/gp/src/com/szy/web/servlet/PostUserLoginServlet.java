package com.szy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.szy.web.dao.UserDAO;
import com.szy.web.model.User;
import com.szy.web.util.MD5Util;
import com.szy.web.util.TextUtility;

/**
 *@author coolszy
 *@date Feb 24, 2012
 *@blog http://blog.92coding.com http://127.0.0.1:8080/web/postComment nid
 *       region content
 */
public class PostUserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = -7811568044252827351L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginType = request.getParameter("loginType");
		String openid = request.getParameter("openid");
		username = TextUtility.toUTF8(username);
		password = TextUtility.toUTF8(password);
		UserDAO userDAO;
		JSONObject jObject = new JSONObject();
		String token=MD5Util.string2MD5(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		try {
			userDAO = new UserDAO();
			if(loginType!=null&&loginType.equals("weixin")){
				userDAO.saveOrUpdate(openid,username,loginType);
				User userinfo = userDAO.getUserInfoByOpenid(openid);
				jObject.put("status", 0);
				jObject.put("statumsg", "登录成功");
				JSONObject result = new JSONObject();
				result.put("userid", userinfo.getUserid());
				result.put("username", username);
				result.put("installtime", userinfo.getRegtime());
				result.put("endtime", userinfo.getEndtime());
				result.put("islogin", userinfo.getIslogin());
				result.put("ispay", userinfo.getIspay());
				result.put("phone", userinfo.getPhone());
				jObject.put("token", token);
				userDAO.updateUserToken(userinfo.getUserid(), token);
				jObject.put("result", result);
			}else{
				int islogin = userDAO.validate(username, password);
				if (islogin==2) {
						long logintime = System.currentTimeMillis();
						boolean issuccess = userDAO.updatelogintime(username,
								logintime);
						if (issuccess) {
							User userinfo = userDAO.getUserInfoByUName(username);
							jObject.put("status", 0);
							jObject.put("statumsg", "登录成功");
							JSONObject result = new JSONObject();
							result.put("userid", userinfo.getUserid());
							result.put("username", username);
							result.put("installtime", userinfo.getRegtime());
							result.put("endtime", userinfo.getEndtime());
							result.put("islogin", userinfo.getIslogin());
							result.put("ispay", userinfo.getIspay());
							result.put("phone", userinfo.getPhone());
							jObject.put("result", result);
							
							jObject.put("token", token);
							userDAO.updateUserToken(userinfo.getUserid(), token);
							
						} else {
							jObject.put("status", 2);
							jObject.put("statumsg", "更新登录时间失败");
							jObject.put("result", "");
						}
					} else {
						jObject.put("status", 1);
						jObject.put("statumsg", "登录失败");
						jObject.put("result", "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jObject.put("status", 0);
				jObject.put("statumsg", "查询数据库产生错误");
				jObject.put("result", "");
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		}
		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}

}
