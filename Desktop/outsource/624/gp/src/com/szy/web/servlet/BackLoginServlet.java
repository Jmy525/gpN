package com.szy.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.szy.web.util.TextUtility;

/**
 * Servlet implementation class BackLoginServlet 后台查询页面查询登录
 */
public class BackLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * 整体后台只有一个用户
	 */
	private String NAME="admin";//用户名
	private String PWD="admin123";//密码

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		username = TextUtility.toUTF8(username);
		password = TextUtility.toUTF8(password);

		if(username==null || username.isEmpty() || password==null || password.isEmpty()){
			//用户名或者密码不正确，跳转到登录页面，提示错误信息
			request.setAttribute("error", "用户名或者密码不能为空");
			request.getRequestDispatcher("/login_.jsp").forward(request,
					response);
		}else if(username.equals(this.NAME) && password.equals(this.PWD)){
			//登录成功
			request.setAttribute("is_login", "yes");
			//跳转到后台查询页面的servlet
			request.getRequestDispatcher("qm").forward(request, response);
		}else{
			//用户名或者密码不正确，跳转到登录页面，提示错误信息
			request.setAttribute("error", "用户名或者密码不正确");
			request.getRequestDispatcher("/login_.jsp").forward(request,
					response);
		}
	}

}
