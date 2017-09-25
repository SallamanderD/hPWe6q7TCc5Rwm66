<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Voting</title>
</head>
<body>
    <h2>${error}</h2>
    <form method="post" action="/vote">
        <p>Имя пользователя: </p>
        <input type="text" name="login">
        <select name="sport">
        <c:forEach items="${variants}" var="variant">
            <option value="${variant.name}">${variant.name}</option>
        </c:forEach>
        </select>
        <input type="submit">
    </form>
</body>
</html>
