<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%
	 String base=request.getContextPath();
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function shangchuanquxiao(upload){
	//取消的方法    
	var who=document.getElementById(upload);   
	var who2= who.cloneNode(false);    
	who2.onchange= who.onchange;
	// events are not cloned     
	who.parentNode.replaceChild(who2,who);  
	}
</script>
<body>
	<form action="<%=base%>/UpLoad" id="form1" name="form1" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><input type="file" name="upload1" id="upload1" size="55"
					style="height: 27px; line-height: 27px;"><br /></td>
				<td><input value="取消" onclick="shangchuanquxiao('upload1')" type="button"></td>
			</tr>
			<tr>
				<td><input type="file" name="upload2" id="upload2" size="55"
					style="height: 27px; line-height: 27px;"><br /></td>
				<td><input value="取消" onclick="shangchuanquxiao('upload2')" type="button"></td>
			</tr>
			<tr>
			<td>
			<input value="保存" type="submit">&nbsp;&nbsp; 
			</td>
			</tr>
		</table>
	</form>
</body>
</html>