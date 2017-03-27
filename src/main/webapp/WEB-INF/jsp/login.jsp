<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<script src="<%=basePath%>js/jquery-2.1.4/jquery.min.js"></script>
  </head>
  
  <body>
     <form action="<%=basePath%>/login" method="post">
         User Name : <input type="text" name="username" id="userName"/>
         Password: <input type="password" name="password" id="password"/>
         <input type="submit" value="Sign In"/>
         b3b33a4e-979e-4e7b-b298-57ff21579dad
     </form>
  </body>
  <script type="text/javascript">

  </script>
</html>
