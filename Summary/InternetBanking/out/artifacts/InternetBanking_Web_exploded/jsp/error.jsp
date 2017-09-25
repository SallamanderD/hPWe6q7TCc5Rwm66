<%@ include file="/partialjsp/taglibs.jspf" %>
<%@ page isErrorPage="true" %>

<html>
<c:set var="title" value="IB: Error Page" />
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
<c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
<div class="container maincontainer">
    <div class="row">
        <div class="col" style="margin-top: 7%">
            <c:if test="${not empty code}">
                <h3>Error code: ${code}</h3>
            </c:if>
            <c:if test="${not empty message}">
                <h3>${message}</h3>
            </c:if>
            <c:if test="${not empty error}">
                <h3>${error} Sorry :(</h3>
            </c:if>
        </div>
        <div class="col">
            <img src="../img/wumpus.jpg">
        </div>
    </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf"%>
</html>
