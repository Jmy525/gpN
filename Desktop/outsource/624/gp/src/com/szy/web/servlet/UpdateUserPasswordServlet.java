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
public class UpdateUserPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String password = request.getParameter("password");
		String phone=request.getParameter("phone");
		//String oldPassword=request.getParameter("oldPassword");
		password = TextUtility.toUTF8(password);
		phone = TextUtility.toUTF8(phone);
		//oldPassword=TextUtility.toUTF8(oldPassword);
		JSONObject jObject = new JSONObject();
		try {
			UserDAO userDao = new UserDAO();
			int successInt = userDao.updateUserPassword(password, phone);
			if(successInt==1){
				jObject.put("status", 0);
				jObject.put("statumsg", "更新用户密码成功");
				jObject.put("result", "");
			}else if(successInt==2){
				jObject.put("status", 1);
				jObject.put("statumsg", "请到第三方平台修改密码");
				jObject.put("result", "");
			}/*else if(successInt==3){
				jObject.put("status", 1);
				jObject.put("statumsg", "旧密码不正确");
				jObject.put("result", "");
			}*/else{
				jObject.put("status", 1);
				jObject.put("statumsg", "服务器异常");
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
