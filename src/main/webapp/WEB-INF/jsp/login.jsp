<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login</title>
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
  
  <body style="margin-left: 500px">
     <h1 style="margin-left: 30px">登录页面----</h1>
     <form action="<%=basePath%>/login" method="post">
         用户名 : <input type="text" name="email" id="email"/><br>
         密码: <input type="password" name="pswd" id="pswd"/><br>
         验证码：<input type="text" name="gifCode" id="gifCode"/>
         <img alt="验证码" src="<%=basePath%>gif/getGifCode"><br>
         <input type="checkbox" name="rememberMe" />记住我<br>
         <input style="margin-left: 100px" type="submit" value="登录"/><input style="left: 50px" onclick="register()" type="button" value="注册"/>
     </form>
     <h1 style="color: red">${message }</h1>
  </body>
  <script type="text/javascript">

      function register(){
          location.href="<%=basePath%>permission/userInsert";
      }

  </script>
</html>
