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

import com.szy.web.dao.LanguageDao;
import com.szy.web.dao.NoteDao;
import com.szy.web.model.Language;
import com.szy.web.model.Note;
import com.szy.web.util.TextUtility;

public class NoteServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NoteDao dao;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JSONObject jobj=null;
		String opt=req.getParameter("opt");//执行动作类型，如果opt为0，则插入。否则获取所有笔记
		try {
			dao=new NoteDao();
			if("0".equals(opt)){//执行插入笔记，返回成功或者失败。
				String userid=req.getParameter("userid");
				String english=req.getParameter("english");
				if(dao.addNote(userid, english)){
					//返回插入成功
					jobj=outAddNoteSuccess();
				}else{
					//返回插入失败
					jobj=outAddNoteFailed();
				}
			}else{//执行查询所有个人词典内容列表
				String userid=req.getParameter("userid");
				List<Note> notes=dao.getNote(userid);
				jobj=outNotes(notes);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	private JSONObject outAddNoteSuccess(){
		JSONObject jobj=new JSONObject();
		try {
			jobj.put("status", 1);
			jobj.put("statumsg", "添加成功！");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jobj;
	}
	private JSONObject outAddNoteFailed(){
		JSONObject jobj=new JSONObject();
		try {
			jobj.put("status", 0);
			jobj.put("statumsg", "添加失败!");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jobj;
	}
	private JSONObject outNotes(List<Note> notes){
		JSONObject jobj=new JSONObject();
			try {
				jobj.put("status", 1);
				jobj.put("statumsg", "查询成功！");
				jobj.put("result",notes);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return jobj;
	}
	
	
	
}
