<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2017-4-2
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body style="text-align: center">
    <h1>注册页面----</h1>
    <form action="${pageContext.request.contextPath}/tUser/insert" method="post">
        用户名 : <input type="text" name="userName" id="userName"/><br>
        密码: <input type="password" name="password" id="password"/><br>
        <input type="submit" value="注册"/>
    </form>
    <h1 style="color: red">${message }</h1>

</body>
</html>
