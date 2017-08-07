package com.szy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.szy.web.dao.UserDAO;
import com.szy.web.model.Product;
import com.szy.web.util.TextUtility;

public class QureyProductMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String userid = request.getParameter("userid");
		userid = TextUtility.toUTF8(userid);
//
		try {
			// JSONArray jsonArray=new JSONArray();
			JSONObject jObject = new JSONObject();
			UserDAO userDao = new UserDAO();
			List<Product> productList = userDao.queryAboutProduct(userid);
			// jsonArray.add(productList);
			jObject.put("product", productList);
			PrintWriter out = response.getWriter();
			out.println(jObject);
			out.flush();
			out.close();

		} catch (ClassNotFoundException e) {
			JSONObject jObject = new JSONObject();
			try {
				jObject.put("status", 1);
				jObject.put("statumsg", e.getMessage());
				jObject.put("result", "");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.println(jObject);
			out.flush();
			out.close();

		} catch (SQLException e) {
			JSONObject jObject = new JSONObject();
			try {
				jObject.put("status", 1);
				jObject.put("statumsg", e.getMessage());
				jObject.put("result", "");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.println(jObject);
			out.flush();
			out.close();
		} catch (JSONException e) {
			JSONObject jObject = new JSONObject();
			try {
				jObject.put("status", 1);
				jObject.put("statumsg", e.getMessage());
				jObject.put("result", "");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.println(jObject);
			out.flush();
			out.close();
		} 

	}


}
