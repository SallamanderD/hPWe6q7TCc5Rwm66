<%--
  Created by IntelliJ IDEA.
  User: kingsdwarf
  Date: 09.09.17
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Names</title>
</head>
<body>
<%@include file="menu.jsp"%>
<h4>Enter name: </h4>
<form action="/names" method="post">
    <input type="text" name="name">
    <input type="submit">
</form>
<a href="/remove">Удалить</a>
</body>
</html>
