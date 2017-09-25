<%@  taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSTL Multiplication Table</title>
</head>
<body>
<%@include file="menu.jsp"%>
<h3>JSTL</h3>
<table border="1">
    <c:forEach begin="1" end="9" var="i">
        <tr>
            <c:forEach begin="1" end="9" var="j">
                <td>${i*j}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
