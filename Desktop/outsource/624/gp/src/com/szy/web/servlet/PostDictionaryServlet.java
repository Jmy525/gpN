package com.szy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.szy.web.dao.DictionaryDAO;
import com.szy.web.dao.UserDAO;
import com.szy.web.model.User;
import com.szy.web.util.TextUtility;

/**
 *@author miaofei 
 *@date 2015-08-02
 *url:http://127.0.0.1:8080/gpdict/dictionary 
 *参数:language=语言	classify=分类
 */
public class PostDictionaryServlet extends HttpServlet {
	private static final long serialVersionUID = -7811568044252827351L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String lang = request.getParameter("lang");
		String classify = request.getParameter("classify");
		String keyword = request.getParameter("keyword");
		String type = request.getParameter("type");//1:字词，空是例句
		String userid = request.getParameter("userid");//当前用户id
		String token = request.getParameter("token");//token
		lang = TextUtility.toUTF8(lang);
		classify = TextUtility.toUTF8(classify);
		keyword = TextUtility.toUTF8(keyword);
		DictionaryDAO dictionaryDAO;
		UserDAO userDAO;
		JSONObject jObject = new JSONObject();
		try {
			dictionaryDAO = new DictionaryDAO();
			userDAO = new UserDAO();
			User userinfo = userDAO.getUserInfoByUserid(userid);
			if(token!=null&&token!=""){
				if(userinfo!=null&&userinfo.getToken().equals(token)){
					jObject.put("status", 1);
					jObject.put("statumsg", "查询成功");
					if(type!=null&&type.equals("1")){
						jObject.put("result", dictionaryDAO.queryWord(lang,keyword));
					}else{
						jObject.put("result", dictionaryDAO.queryList(lang, classify, keyword));
					}
				}else{
					jObject.put("status", 0);
					jObject.put("statumsg", "token失效");
					jObject.put("result", "");
				}
			}else{
				jObject.put("status", 1);
				jObject.put("statumsg", "查询成功");
				if(type!=null&&type.equals("1")){
					jObject.put("result", dictionaryDAO.queryWord(lang,keyword));
				}else{
					jObject.put("result", dictionaryDAO.queryList(lang, classify, keyword));
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
