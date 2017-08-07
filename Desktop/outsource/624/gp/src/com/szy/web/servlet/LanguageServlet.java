package com.szy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.szy.web.dao.LanguageDao;
import com.szy.web.model.Language;
import com.szy.web.util.TextUtility;

public class LanguageServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String language=req.getParameter("language");
		String word=req.getParameter("word");
		language=TextUtility.toUTF8(language);
		word=TextUtility.toUTF8(word);
		Language lan=requestTranslate(language, word);
		JSONObject jobj=null;
		if(lan!=null){
			jobj=outJson(lan);
		}else{
			jobj=outError(lan);
		}
		out(resp, jobj);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String language=req.getParameter("language");
		String word=req.getParameter("word");
		Language lan=requestTranslate(language, word);
		JSONObject jobj=null;
		if(lan!=null){
			jobj=outJson(lan);
		}else{
			jobj=outError(lan);
		}
		out(resp, jobj);
	}
	private void out(HttpServletResponse resp,JSONObject jobj) throws IOException{
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(jobj);
		out.flush();
		out.close();
	}
	private JSONObject outError(Language lan){
		JSONObject jobj=new JSONObject();
			try {
				jobj.put("status", 1);
				jobj.put("statumsg", "没有查到!");
				jobj.put("result",lan);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return jobj;
	}
	private JSONObject outJson(Language lan){
		JSONObject jobj=new JSONObject();
			try {
				jobj.put("status", 0);
				jobj.put("statumsg", "查询词典成功!");
				jobj.put("result",lan);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return jobj;
	}
    private  Language requestTranslate(String language,String word){
    	try {
			LanguageDao ld=new LanguageDao();
			return ld.translate(language, word);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
	
}
