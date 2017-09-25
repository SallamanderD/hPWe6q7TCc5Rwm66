<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Calculate</title>
</head>
<body>
    <form action="/calculate" method="get">
        <input type="number" name="x">
        <input type="number" name="y">
        <select name="op">
            <option value="plus">+</option>
            <option value="minus">-</option>
            <option value="mult">*</option>
            <option value="divide">/</option>
        </select>
        <input type="submit">
    </form>
    <a href="index.jsp">To menu</a>
</body>
</html>
