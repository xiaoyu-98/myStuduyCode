<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/11
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>登录成功！</title>
</head>
<body>
欢迎你登录！！！
<shiro:hasRole name="admin">
    欢迎有admin角色的用户
</shiro:hasRole>
<shiro:hasPermission name="student:add">
    欢迎有student:add权限的用户
</shiro:hasPermission>
</body>
</html>
