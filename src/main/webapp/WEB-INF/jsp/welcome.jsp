<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2017-4-2
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>欢迎!</title>
</head>
<body style="text-align: center">
    <h1>${message }</h1>
    <h1>用户列表--<a href="${pageContext.request.contextPath }/logout">退出登录</a></h1>
    当前登录用户: <shiro:principal/><br/>
    <shiro:authenticated>我已登录,但未记住<br/></shiro:authenticated>
    <shiro:user>我已登录,或已记住<br/></shiro:user>
    <shiro:guest>我是访客<br/></shiro:guest>
    <shiro:hasAnyRoles name="manager,admin">manager or admin 角色用户登录显示此内容<br/></shiro:hasAnyRoles>
    <shiro:hasRole name="管理员">我是管理员<br/></shiro:hasRole>
    <shiro:hasRole name="会员">我是会员<br/></shiro:hasRole>
    <h2>权限列表</h2>
    <shiro:hasPermission name="用户管理">具有用户管理权限用户显示此内容<br/></shiro:hasPermission>
    <shiro:hasPermission name="菜单管理">具有菜单管理权限用户显示此内容<br/></shiro:hasPermission>
    <shiro:hasPermission name="地区管理">具有地区管理权限用户显示此内容<br/></shiro:hasPermission>
    <shiro:lacksPermission name="地区管理">不具有地区管理权限的用户显示此内容 <br/></shiro:lacksPermission>

</body>
</html>
