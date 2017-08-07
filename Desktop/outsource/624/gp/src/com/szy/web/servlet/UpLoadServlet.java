package com.szy.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.json.JSONObject;

import com.szy.web.dao.UserDAO;
import com.szy.web.model.User;
import com.szy.web.util.TextUtility;


/**
 * Servlet implementation class UpLoad
 */
public class UpLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 1Kb
	 */
	private final long ONE_M_SIZE=1024l;
	/**
	 * 缓存大小50M,放在内存中的缓存
	 */
	private final int BUFFERSIZE=1024*1024*50;
	/**
	 * 上传的路径
	 */
	private String PATH=null;
	/**
	 * 临时缓存目录，缓存文件设定
	 */
	private File TEMPORARY_FILE=null;
	
	public void init(ServletConfig config){
		try{
		PATH=config.getServletContext().getRealPath("\\");
		//处理linux下的路径
		if(PATH.contains("/\\"))
		    PATH=PATH.substring(0, PATH.length()-1)+"upload";
		 else
		    PATH=PATH+"upload";
		}catch(Exception e){
			e.printStackTrace();
		}
	    String TEMPORARY=PATH+"temp";
		TEMPORARY_FILE=new File(TEMPORARY);
		if(!TEMPORARY_FILE.exists())
			TEMPORARY_FILE.mkdir();
		System.out.println("临时缓存文件目录="+TEMPORARY);
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 1.该方法 处理简单的 文件上传 支持form表单支持ajax提交文件上传
	 * 2.上传的文件在上传过程中将在内存缓存10k，超过10k的放到临时缓存目录中。
	 * 3.上传完毕后，临时缓存目录中将保留最后一个缓存的文件，其他的缓存文件将会被系统删除。
	 * 4.支持的字符集为 UTF-8
	 * 5.上传到项目的根目录下upload
	 * 6.缓存目录在项目的根目录下uploadtemp
	 * 7.支持多文件上传功能
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		boolean isUpload = ServletFileUpload.isMultipartContent(request);
		System.out.println("是否是form上传文件："+isUpload);
		
		String userid=request.getParameter("userid");
				userid = TextUtility.toUTF8(userid);
		String updateAvatar=request.getParameter("updateAvatar");
				updateAvatar = TextUtility.toUTF8(updateAvatar);
		
		JSONObject jObject = new JSONObject();
		if(userid==null||userid.isEmpty()){
			
			try {
				jObject.put("status", 1);
				jObject.put("statumsg", "少参数 userid不能为空！");
				jObject.put("result", "");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.println(jObject);
			out.flush();
			out.close();
		}else{
				
		UserDAO dao=null;
		try {
			dao=new UserDAO();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if("update".equals(updateAvatar)){
			User user=null;
			try {
				user=dao.getInstallTimeByUid(userid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(user==null){
				try {
					jObject.put("status", 1);
					jObject.put("statumsg", "该用户不存在！");
					jObject.put("result", "");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else if(user.getAvatar()==null || user.getAvatar().isEmpty()){
				try {
					jObject.put("status", 1);
					jObject.put("statumsg", "您还没有上传头像，请先上传头想！");
					jObject.put("result", "");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				PrintWriter out = response.getWriter();
				out.println(jObject);
				out.flush();
				out.close();
			}else{
				String path=user.getAvatar();
				int lastIndex = path.lastIndexOf(File.separator);
				path = path.substring(lastIndex, path.length());
				File file=new File(PATH+path);
				if(file.exists()){
					file.delete();
				}
			}
		}
		
		/**
		 * 设定了10k缓存和临时缓存目录
		 * 当内存放不下的话，会放到factory的临时目录中
		 * 上传完毕后，临时目录会删除
		 */
		DiskFileItemFactory factory=new DiskFileItemFactory(BUFFERSIZE,TEMPORARY_FILE);
		ServletFileUpload up=new ServletFileUpload(factory);
		up.setHeaderEncoding("utf-8");
		List<FileItem> ls=null;
		try{
		 ls=up.parseRequest(request);
		}catch(FileUploadException e){
			try {
				jObject.put("status", -1);
				jObject.put("statumsg", "上传文件发生 FileUploadException异常："+e.getMessage());
				jObject.put("result", "");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
			PrintWriter out = response.getWriter();
			out.println(jObject);
			out.flush();
			out.close();
		}
		try {
			for(FileItem fileItem:ls){
				if(!fileItem.isFormField()){
				   String fieldName = fileItem.getFieldName();      //文件域中name属性的值
				   String fileName = fileItem.getName();            //文件的全路径，绝对路径名加文件名           
				   String contentType = fileItem.getContentType(); //文件的类型
				   long size = fileItem.getSize();                  //文件的大小，以字节为单位 
				   String nm = fileName.substring(
					       fileItem.getName().lastIndexOf("\\") + 1);
				   File mkr = new File(PATH,nm);
				   if (mkr.exists()){
					    jObject.put("status", 2);
						jObject.put("statumsg", "文件已存在！请重新命名上传的文件。文件名称："+nm);
						jObject.put("result", "");
						PrintWriter out = response.getWriter();
						out.println(jObject);
						out.flush();
						out.close();
					   }else{
					    	 new File(PATH).mkdir();
					    	 mkr.createNewFile();
					    	 fileItem.write(mkr);
					    	 String rootDir="upload"+File.separator+fileName;
				    		 boolean issuccess=dao.addUserAvatar(userid,rootDir);
				    		 if(issuccess){
				    			 if(!"update".equals(updateAvatar)){
				    				    jObject.put("status", 0);
										jObject.put("statumsg", "上传头像成功！");
										jObject.put("result", "");
										PrintWriter out = response.getWriter();
										out.println(jObject);
										out.flush();
										out.close();
				    			 }else{
				    				   jObject.put("status", 0);
										jObject.put("statumsg", "更改头像成功！");
										jObject.put("result", "");
										PrintWriter out = response.getWriter();
										out.println(jObject);
										out.flush();
										out.close();
				    			 }
				    		 }else{
				    			 File file=new File(PATH+File.separator+fileName);
				    			 if(file.exists()){
				    				 file.delete();
				    			 }
				    			 if(!"update".equals(updateAvatar)){
				    				    jObject.put("status", 0);
										jObject.put("statumsg", "上传头像成功！");
										jObject.put("result", "");
										PrintWriter out = response.getWriter();
										out.println(jObject);
										out.flush();
										out.close();
				    			 }else{
				    				   jObject.put("status", 0);
										jObject.put("statumsg", "更新头像失败！");
										jObject.put("result", "");
										PrintWriter out = response.getWriter();
										out.println(jObject);
										out.flush();
										out.close();
				    			 }
				    		 }
							 
					     }
				   System.out.println("fieldName="+fieldName);
				   System.out.println("fileName="+fileName);
				   System.out.println("contentType="+contentType);
				   System.out.println("size="+size/ONE_M_SIZE+"kb");
				}
			}
			
		}catch (Exception e) {
			try {
				jObject.put("status", -1);
				jObject.put("statumsg", "上传文件发生异常："+e.getMessage());
				jObject.put("result", "");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.println(jObject);
			out.flush();
			out.close();
		}
		//utile.writeJsonToClient(response, modelList);
	} 
	
	}
}
