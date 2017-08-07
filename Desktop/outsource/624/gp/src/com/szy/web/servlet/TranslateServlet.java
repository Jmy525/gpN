package com.szy.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.szy.web.dao.WordsDAO;
import com.szy.web.model.Words;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TranslateServlet extends HttpServlet {
	private static final long serialVersionUID = -7715894432269979527L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String source = name.substring(0, name.length() - 1);
		source = name.replace("?", "");
		source = source.replace(".", "");
		source = source.replace(",", " ");
		source = source.replace("!", "");
		source = source.replaceAll("(\r\n|\r|\n|\n\r)", " "+"\r\n"+" ");
		String s = "";
		for (int i = 0; i < source.length() - 1; i++) {
			if ((int) source.charAt(i) == 32
					&& (int) source.charAt(i + 1) == 32) {
				continue;
			}
			s += source.charAt(i);
		}
		if ((int) source.charAt(source.length() - 1) != 32)
			s += source.charAt(source.length() - 1);
		
		List<Words> listwords = null;
		try {
			WordsDAO word = new WordsDAO();
			listwords = word.getWordListGPByName(s.split(" "));
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String result = "";
		StringBuffer sb = new StringBuffer();
		if (listwords != null && listwords.size() > 0) {
			for (int i = 0; i < listwords.size(); i++) {
				if (listwords.get(i) != null) {
					if (listwords.get(i).getWordgp() != null) {
						sb.append(listwords.get(i).getWordgp()).append("  ");
					} else {
						sb.append(listwords.get(i).getWordname()).append("  ");
					}
				}
			}
		}
		request.getSession().setAttribute("name", name);
		
		request.getSession().setAttribute("gp", sb);
		// 跳转页面
		RequestDispatcher rd = request.getRequestDispatcher("/traslate.jsp");
		rd.forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementedException();
	}
}
