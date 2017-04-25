<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=basePath%>script/jquery-1.8.3.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<center>
		<input type="button" onclick="dopost()" value="获取资料" /> 
			<img id="codevalidate"
			src="<%=basePath%>code/<%=new Date().getTime()%>" width="90"
			height="30" style="margin-left: 10px" onclick="changeUrl()"/>
	</center>
</body>

<script type="text/javascript">
	var dataSource = {
		username : "201411044",
		password : "qaz123456",
		data : {
			user : '123'
		}
	};

	var dataObject = JSON.stringify(dataSource);
	function dopost() {
		$.ajax({
			type : 'post',
			url : './user',
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify(dataObject),
			dataType : 'json',
			success : function(data) {
				alert(data.data.password);
			},
			error : function(jqXHR) {
				alert(jqXHR.status);
			}
		})
	}
</script>
</html>