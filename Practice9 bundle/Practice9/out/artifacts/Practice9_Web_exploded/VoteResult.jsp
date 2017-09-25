<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>VoteResult</title>
</head>
<body>
    <c:forEach var = "variant" items="${variants}">
        <p>${variant.name} ==> ${variant.count}</p>
        <c:if test="${not empty variant.logins}">
            <c:forEach items="${variant.logins}" var="login">
                <p>${login}</p>
            </c:forEach>
        </c:if>
    </c:forEach>

</body>
</html>
