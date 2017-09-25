<%@  taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scriptlet Multiplication Table</title>
</head>
<body>
<%@include file="menu.jsp"%>
<h3>Scriptlet</h3>
<table border="1">
    <%
        for(int i = 1; i < 10; i++){
    %>
    <tr>
        <%
            for(int j = 1; j < 10; j++){
        %>
        <td><%=i * j %></td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
