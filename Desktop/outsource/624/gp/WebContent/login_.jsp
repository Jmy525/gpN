<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String error = request.getAttribute("error") == null ? "" : request
			.getAttribute("error").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/login/style.css" />
<script type="text/javascript" src="js/placeholder.js"></script>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<title>登录界面</title>
<script>
	/* when document is ready */
	$(function() {
	var err="<%=error%>";
	if(err.length<0){
		$("#err").hide();
	}else{
		$("#err").show();
	}
	});
</script>
</head>
<body>
	<form id="slick-login" action="<%=path%>/login_" method="post">
		<div id="err" style="font-size: 14px; font-weight: bold; color: red"><%=error%></div>
		<label for="username">username</label><input type="text"
			name="username" class="placeholder" placeholder="username"> <label
			for="password">password</label><input type="password" name="password"
			class="placeholder" placeholder="password"> <input
			type="submit" value="登录">
	</form>
</body>
</html>