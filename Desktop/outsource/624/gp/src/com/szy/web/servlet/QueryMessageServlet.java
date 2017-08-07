package com.szy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.szy.web.dao.UserDAO;
import com.szy.web.model.PageMessage;
import com.szy.web.util.TextUtility;

public class QueryMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String IS_LOGIN = request.getAttribute("is_login") == null ? ""
				: request.getAttribute("is_login").toString();
		String IS_LOGIN_ = request.getParameter("is_login") == null ? ""
				: request.getParameter("is_login").toString();
		// 判断是否已经登录 登录
		if ((!IS_LOGIN.isEmpty() && "yes".equals(IS_LOGIN))
				|| (!IS_LOGIN_.isEmpty() && "yes".equals(IS_LOGIN_))) {
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			String user_name = request.getParameter("user_name");
			String user_phone = request.getParameter("user_phone");
			start_date = TextUtility.toUTF8(start_date);
			end_date = TextUtility.toUTF8(end_date);
			user_name = TextUtility.toUTF8(user_name);
			user_phone = TextUtility.toUTF8(user_phone);
			List<PageMessage> pm = new ArrayList<PageMessage>();
			try {
				UserDAO dao = new UserDAO();
				pm = dao.queryPage(start_date, end_date, user_name, user_phone);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("pm", pm);
			request.setAttribute("start_date", start_date);
			request.setAttribute("end_date", end_date);
			request.setAttribute("user_name", user_name);
			request.setAttribute("user_phone", user_phone);
			// 跳转页面
			request.getRequestDispatcher("/queryMessage.jsp").forward(request,
					response);
		} else {
			request.setAttribute("error", "请先登录");
			request.getRequestDispatcher("/login_.jsp").forward(request,
					response);
		}
	}

}
