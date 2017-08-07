package com.szy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.szy.web.dao.CheckApkIsPayDAO;
import com.szy.web.model.CheckIsPayByImei;

/**
 *@author coolszy
 *@date Feb 19, 2012
 *@blog http://blog.92coding.com http://localhost:8080/web/checkispay?nid=1
 */
public class GetIsPayByImeiServlet extends HttpServlet {
	private static final long serialVersionUID = -7715894432269979527L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String imei = request.getParameter("imei");
		JSONObject jObject = new JSONObject();
		try {
			CheckApkIsPayDAO checkapkDAO = new CheckApkIsPayDAO();
			CheckIsPayByImei ispaybean = checkapkDAO
					.getCheckIsPayByImei(imei);
			jObject.put("status", 0);
			jObject.put("statumsg", "ok");
			JSONObject ispay = new JSONObject();
			ispay.put("imei", ispaybean.getImei());
			ispay.put("installtime", ispaybean.getInstalltime());
			ispay.put("endtime", ispaybean.getEndtime());
			jObject.put("result", ispay);
		} catch (Exception e) {
			e.printStackTrace();
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementedException();
	}
}
