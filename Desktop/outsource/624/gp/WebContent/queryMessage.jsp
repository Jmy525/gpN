<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询页面</title>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/jPages.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/github.css">
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/highlight.pack.js"></script>
<script type="text/javascript" src="js/tabifier.js"></script>
<script src="js/js.js"></script>
<script src="js/jPages.js"></script>
<script>
	/* when document is ready */
	$(function() {
		/* initiate plugin */
		$("div.holder").jPages({
			containerID : "itemContainer"
		});

	var start_date ="<%=request.getAttribute("start_date")%>";
	var end_date ="<%=request.getAttribute("end_date")%>";
	var user_name ="<%=request.getAttribute("user_name")%>";
	var user_phone ="<%=request.getAttribute("user_phone")%>";
		if (start_date != "null") {
			$("#start_date").val(start_date);
		}
		if (end_date != "null") {
			$("#end_date").val(end_date);
		}
		if (user_name != "null") {
			$("#user_name").val(user_name);
		}
		if (user_phone != "null") {
			$("#user_phone").val(user_phone);
		}
	});
</script>
<style type="text/css">
.holder {
	margin: 15px 0;
}

.holder a {
	font-size: 12px;
	cursor: pointer;
	margin: 0 5px;
	color: #333;
}

.holder a:hover {
	background-color: #222;
	color: #fff;
}

.holder a.jp-previous {
	margin-right: 15px;
}

.holder a.jp-next {
	margin-left: 15px;
}

.holder a.jp-current, a.jp-current:hover {
	color: #FF4242;
	font-weight: bold;
}

.holder a.jp-disabled, a.jp-disabled:hover {
	color: #bbb;
}

.holder a.jp-current, a.jp-current:hover, .holder a.jp-disabled, a.jp-disabled:hover
	{
	cursor: default;
	background: none;
}

.holder span {
	margin: 0 5px;
}
</style>
</head>
<body>
	<form action="<%=path%>/qm" method="post">
	<input type="hidden" name="is_login" value="yes">
		<table align="center">
			<tr>
				<td>开始日期 :<input name="start_date" id="start_date" type="text" />
					<img onclick="WdatePicker({el:$dp.$('start_date')})"
					src="My97DatePicker/skin/datePicker.gif" width="16" height="22"
					align="absmiddle"></td>
				<td>结束日期 :<input name="end_date" id="end_date" type="text" />
					<img onclick="WdatePicker({el:$dp.$('end_date')})"
					src="My97DatePicker/skin/datePicker.gif" width="16" height="22"
					align="absmiddle"></td>
			</tr>
			<tr>
				<td>用&nbsp;&nbsp;户&nbsp;&nbsp;名 :<input name="user_name"
					id="user_name" type="text" /></td>
				<td>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机 :<input
					name="user_phone" id="user_phone" type="text" /></td>
			</tr>
			<tr>
				<td colspan="1" align="center"><input id="submit" type="submit"
					value="查询" width="20px" /></td>
			</tr>
		</table>
	</form>
	<div id="author"></div>
	<div style="width: 100%;" class="clearfix">
		<div style="margin-left:1px; with: 90%; color: red;"
			class="defaults">
			<table style="width: 100%; margin: 2px;">
				<tr>
					<td
						style="font-size: 14px; font-weight: bold; text-align: center; width: 20%">用户名</td>
					<td
						style="font-size: 14px; font-weight: bold; text-align: left; width: 10%">注册手机号</td>
					<td
						style="font-size: 14px; font-weight: bold; text-align: left; width: 10%">注册日期</td>
					<td
						style="font-size: 14px; font-weight: bold; text-align: left; width: 10%">系统平台</td>
					<td
						style="font-size: 14px; font-weight: bold; text-align: left; width: 10%">语种</td>
					<td
						style="font-size: 14px; font-weight: bold; text-align: left; width: 10%">免费</td>
					<td
						style="font-size: 14px; font-weight: bold; text-align: left; width: 10%">付费金额</td>
					<td
						style="font-size: 14px; font-weight: bold; text-align: left; width: 10%">开始日期</td>
					<td
						style="font-size: 14px; font-weight: bold; text-align: left; width: 10%">停止日期</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="container" style="width: 100%" class="clearfix">
		<div id="sidebar">
			<div class="share"></div>
		</div>
		<div id="content" style="width: 90%" class="defaults">
			<!-- item container -->
			<ul id="itemContainer">
				<c:forEach var="varName" items="${pm}">
					<li style="width: 100%; margin: 10px; height: 2px">
						<table style="width: 100%; height: 100%; margin: 10px;">
							<tr>
								<td
									style="border: solid 1px #add9c0; text-align: center; width: 20%">${varName.name}</td>
								<td
									style="border: solid 1px #add9c0; text-align: center; width: 8%">${varName.phone}</td>
								<td
									style="border: solid 1px #add9c0; text-align: center; width: 10%">${varName.registerDate}</td>
								<td
									style="border: solid 1px #add9c0; text-align: center; width: 5%">${varName.platform}</td>
								<td
									style="border: solid 1px #add9c0; text-align: center; width: 5%">${varName.language}</td>
								<td
									style="border: solid 1px #add9c0; text-align: center; width: 5%">${varName.free}</td>
								<td
									style="border: solid 1px #add9c0; text-align: center; width: 5%">${varName.payMoney}</td>
								<td
									style="border: solid 1px #add9c0; text-align: center; width: 10%">${varName.startDate}</td>
								<td
									style="border: solid 1px #add9c0; text-align: center; width: 10%">${varName.endDate}</td>
							</tr>
						</table>
					</li>
				</c:forEach>
			</ul>
			<!-- navigation holder -->
			<div class="holder"></div>
		</div>
		<!--! end of #content -->
	</div>
</body>
</html>