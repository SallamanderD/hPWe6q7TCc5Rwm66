<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="menu.jsp"%>
<h3>Names</h3>
    <c:forEach items="${sessionScope.names}" var="name" varStatus="loop">
        <p>${loop.index + 1}) ${name}</p>
    </c:forEach>
    <h4><a href="/names">To Name Input</a></h4>
</body>
</html>
