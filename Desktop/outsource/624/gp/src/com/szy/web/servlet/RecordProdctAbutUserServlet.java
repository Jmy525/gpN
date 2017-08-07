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

public class RecordProdctAbutUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String userid = request.getParameter("userid");
		String language_name = request.getParameter("language_name");
		String optional_buy = request.getParameter("optional_buy");
		String language_type = request.getParameter("language_type");
		String language_money  = request.getParameter("language_money");
		userid = TextUtility.toUTF8(userid);
		language_name = TextUtility.toUTF8(language_name);
		optional_buy = TextUtility.toUTF8(optional_buy);
		language_type = TextUtility.toUTF8(language_type);
		JSONObject jObject = new JSONObject();
		try {
			UserDAO userDao = new UserDAO();
			int successInt = userDao.recodeProductAboutUser(userid,language_name, optional_buy,language_type,language_money);
			if(successInt==1){
				jObject.put("status", 0);
				jObject.put("statumsg", "保存产品信息成功");
				jObject.put("result", "");
			}else if(successInt==2){
				jObject.put("status", 1);
				jObject.put("statumsg", "保存产品信息失败");
				jObject.put("result", "");
			}else{
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
