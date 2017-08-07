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
import com.szy.web.util.TextUtility;

/**
 * Servlet implementation class UpdateUserName
 */
public class UpdateUserNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String userid=request.getParameter("userid");
		username = TextUtility.toUTF8(username);
		userid = TextUtility.toUTF8(userid);
		JSONObject jObject = new JSONObject();
		try {
			UserDAO userDao = new UserDAO();
			boolean issuccess = userDao.updateUserName(username, userid);
			if(issuccess){
				jObject.put("status", 0);
				jObject.put("statumsg", "更新用户名成功");
				jObject.put("result", "");
			}else{
				jObject.put("status", 1);
				jObject.put("statumsg", "没有找到对应的用户id");
				jObject.put("result", "");
			}
		} catch (Exception e) {
			try {
				jObject.put("status", 1);
				jObject.put("statumsg", e.getMessage());
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
