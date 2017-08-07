package com.szy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.szy.web.dao.CommentDAO;
import com.szy.web.util.TextUtility;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *@author coolszy
 *@date Feb 24, 2012
 *@blog http://blog.92coding.com http://127.0.0.1:8080/web/postComment nid
 *       region content
 */
public class PostCommentServlet extends HttpServlet {
	private static final long serialVersionUID = -7811568044252827351L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String nidStr = request.getParameter("userid");
		String userinfo = request.getParameter("userinfo");
		String content = request.getParameter("feedcontent");
		int nid = TextUtility.String2Int(nidStr);
//		time = TextUtility.toUTF8(time);
		content = TextUtility.toUTF8(content);
		String ptime = TextUtility.formatDate(new Date());
		CommentDAO commentDAO;
		JSONObject jObject = new JSONObject();
		try {
			commentDAO = new CommentDAO();
			boolean issuccess = commentDAO.addComment(nid, ptime, content, userinfo);
			if(issuccess){
				jObject.put("status", 0);
				jObject.put("statumsg", "ok");
				jObject.put("data", "");
			}else{
				jObject.put("status", 0);
				jObject.put("statumsg", "反馈失败");
				jObject.put("data", "");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jObject.put("status", 1);
				jObject.put("statumsg", "error");
				jObject.put("data", "");
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
