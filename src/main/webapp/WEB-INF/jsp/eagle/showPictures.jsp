<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/xml" %> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
    <img src="<%=basePath%>image/IMG_0662.JPG" style="height:500px"/>
    <a href="javascript:history.go(-1);"><button>返回</button></a>
    <a href="<%=basePath%>eagle/showUser"><button>跳转</button></a>
    <c:forTokens items="1,5,6,8" delims="," var="i">
      ${i } <br>
    </c:forTokens>
    <c:forEach var="i" step="1" begin="1" end="5">
      ${i} <br>
    </c:forEach>
	<c:set var="salary" scope="session" value="${2000*2}"/>
    <c:out value="${salary}"/>
  </body>
</html>
