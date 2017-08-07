package com.szy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.szy.web.util.TextUtility;

/**
 * Servlet implementation class SetOptionServlet
 */
public class SetOptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static String OPTION_VALUE="1";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetOptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 放入设置项的值
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String option = request.getParameter("option");
		option = TextUtility.toUTF8(option);
		SetOptionServlet.OPTION_VALUE=option;
		JSONObject jObject = new JSONObject();
		try {
			jObject.put("status", 0);
			jObject.put("statumsg", SetOptionServlet.OPTION_VALUE);
			jObject.put("result", "");
		} catch (Exception e) {
			try {
				jObject.put("status", 1);
				jObject.put("statumsg", e.getMessage());
				jObject.put("result", "");
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		}finally{
			PrintWriter out = response.getWriter();
			out.println(jObject);
			out.flush();
			out.close();
		}
	}

}
