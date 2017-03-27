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
    <input type="button" value="欢迎44444" onclick="go()" id="ss">
  </body>
  <script type="text/javascript">
   $(function(){
	   $("#ss").bind('click',function(){
		   console.log(22);
	   })
   });
   function go(){
	   console.log(111);
   }
   function hh(){
	   console.log(22);
   }
  </script>
</html>
