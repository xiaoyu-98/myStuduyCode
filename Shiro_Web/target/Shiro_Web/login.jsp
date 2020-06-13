<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/11
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="/login" method="post">
    username：<input type="text" name="username"><br>
    password：<input type="password" name="password"><br>
    <input type="submit" value="登录">${errorInfo}
</form>

</body>
</html>
