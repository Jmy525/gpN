package com.szy.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.szy.web.model.ProductId;

public class GetProductIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String WEIXIN_BACK_URL = "";
	public static double product_id = 1d;  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		JSONObject jObject=new JSONObject();
		ProductId productId=new ProductId();
		
		try {
			synchronized (this) {
				productId.setBackUrl(WEIXIN_BACK_URL);
				productId.setProductId(product_id++);
				jObject.put("status", 0);
				jObject.put("statumsg", "获取商品产品号成功!");
				jObject.put("result",productId);
			}
		} catch (Exception e) {
			jObject.put("status", 0);
			jObject.put("statumsg", e.getMessage());
			jObject.put("result",productId);
		}
		
		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
	
}
