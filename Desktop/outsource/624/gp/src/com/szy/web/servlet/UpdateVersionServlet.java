package com.szy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.szy.web.dao.CommentDAO;
import com.szy.web.dao.NewsDAO;
import com.szy.web.dao.VersionDAO;
import com.szy.web.model.News;
import com.szy.web.model.Version;
import com.szy.web.util.TextUtility;

/**
 *@author coolszy
 *@date Feb 19, 2012
 *@blog http://blog.92coding.com http://localhost:8080/web/getNews?nid=1
 */
public class UpdateVersionServlet extends HttpServlet {
	private static final long serialVersionUID = -7715894432269979527L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String currentversion = request.getParameter("version");
		int version = 0;
		version = TextUtility.String2Int(currentversion);
		JSONObject jObject = new JSONObject();
		try {
			VersionDAO versionDAO = new VersionDAO();
			Version versionbean = versionDAO.getCurrentVersion();
			if (versionbean!=null&&!TextUtility.isNull(versionbean.getVersion())) {
				int cversion = TextUtility.String2Int(versionbean.getVersion());
				if (version >=cversion) {
					//不需要更新
//					String updatetime = TextUtility.formatDate(new Date());
//					versionDAO.updateCurrentVersion(TextUtility.toUTF8(version
//							+ ""), updatetime);
					jObject.put("status", 3);
					jObject.put("statusmsg", "当前已经是最新版本");
					jObject.put("result", 1);
					
				}else {
					jObject.put("status", 0);
					jObject.put("statusmsg", "当前版本需要更新");
					jObject.put("versionnumber", versionbean.getVersion());
					jObject.put("path", versionbean.getPath());
					jObject.put("forceupdate", versionbean.getForceupdate());
					jObject.put("prompt", versionbean.getUpdateinfo());
					jObject.put("title", versionbean.getTitle());
					jObject.put("result", 0);
				}
			}else{
				jObject.put("status", 1);
				jObject.put("statusmsg", "数据库查询失败");
				jObject.put("result", 2);
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				jObject.put("status", 1);
				jObject.put("statusmsg", e.getMessage());
				jObject.put("result", 2);
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
