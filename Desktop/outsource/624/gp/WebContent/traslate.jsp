<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style>
body {
	text-align: center;
	font-size: 16px;
}
</style>
	</head>

	<body>
		<form action="traslate" method="GET">
		   <table>
		    <tr>
		    <td><input type="submit" value="转换" style="width: 100px; height: 40px" /></td>
		    </tr>
			</table>
			<br />
			请输入英文:
			<br />
			<textarea  name="name" width="500px"
				style="width: 800px; height: 200px"><%= session.getAttribute("name") %></textarea>
			<br />
		</form>
		英文GP:
			<br />
			<br />
			<textarea  name="gp" width="500px"
				style="width: 800px; height: 200px"><%= session.getAttribute("gp") %></textarea>
			<br />
	</body>
</html>
