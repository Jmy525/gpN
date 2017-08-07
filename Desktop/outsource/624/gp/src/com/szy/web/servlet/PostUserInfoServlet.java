package com.szy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.szy.web.dao.UserDAO;
import com.szy.web.model.User;
import com.szy.web.util.TextUtility;

/**
 *@author coolszy
 *@date Feb 24, 2012
 *@blog http://blog.92coding.com http://127.0.0.1:8080/web/userregist nid
 *       region content
 */
public class PostUserInfoServlet extends HttpServlet {
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
		String phone = request.getParameter("phone");
		String imei = request.getParameter("imei");
		String paytype = request.getParameter("type");
		String platform=request.getParameter("platform");
		username = TextUtility.toUTF8(username);
		password = TextUtility.toUTF8(password);
		phone = TextUtility.toUTF8(phone);
		platform=TextUtility.toUTF8(platform);
		UserDAO userDAO;
		JSONObject jObject = new JSONObject();
		try {
			long endtime = 0;
			long regtime = System.currentTimeMillis();
			if(paytype.equals("0")){
				endtime = regtime + (long)60 * 24 * 3600 * 1000;
			}else{
				endtime = regtime + (long)365 * 24 * 3600 * 1000;
			}
			userDAO = new UserDAO();
			int issucces = userDAO.addUser(username, password, phone, imei, regtime, endtime,platform);
			if (issucces==3) {
				long logintime = System.currentTimeMillis();
				boolean isupdate = userDAO.updatelogintime(username, logintime);
				if (isupdate) {
					User userinfo = userDAO.getUserInfoByUName(username);
					jObject.put("status", 0);
					jObject.put("statumsg", "注册成功");
					JSONObject result = new JSONObject();
					result.put("userid", userinfo.getUserid());
					result.put("username", userinfo.getUsername());
					result.put("installtime", userinfo.getRegtime());
					result.put("endtime", userinfo.getEndtime());
					result.put("islogin", userinfo.getIslogin());
					result.put("ispay", userinfo.getIspay());
					result.put("phone", userinfo.getPhone());
					jObject.put("result", result);
				} else {
					jObject.put("status", 1);
					jObject.put("statumsg", "更改登录时间失败");
					jObject.put("result", "");
				}

			}else if(issucces==1){
				jObject.put("status", 2);
				jObject.put("statumsg", "用户名已经存在");
				jObject.put("result", "");
			}else if(issucces==2){
				jObject.put("status", 3);
				jObject.put("statumsg", "电话号码已经注册");
				jObject.put("result", "");
			}
			else if(issucces==0){
				jObject.put("status", 1);
				jObject.put("statumsg", "注册失败");
				jObject.put("result", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jObject.put("status", 1);
				jObject.put("statumsg", "注册失败");
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
