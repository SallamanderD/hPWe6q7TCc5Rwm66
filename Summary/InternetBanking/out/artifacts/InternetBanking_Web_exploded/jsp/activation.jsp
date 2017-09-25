<%@ include file="/partialjsp/taglibs.jspf" %>
<html>
<fmt:message key="activation.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <c:if test="${not empty error}">
        <p>${error}</p>
    </c:if>
    <div class="col">
        <form method="post" action="/controller">
            <h2><fmt:message key="activation.header.one"/>${sessionScope.user.email})<fmt:message key="activation.header.two"/></h2>
            <input type="hidden" name="command" value="activate">
            <input type="hidden" name="login" value="${sessionScope.user.login}">
            <input type="hidden" name="password" value="${sessionScope.user.password}">
            <div class="form-group">
                <input type="text" name="code" id="code" class="form-control input-lg" placeholder="<fmt:message key="activation.placeholder"/>">
                <input type="submit" class="btn btn-lg btn-success btn-block" value="<fmt:message key="activation.button.submit"/>">
            </div>
        </form>
    </div>
</div>
</body>\<%@ include file="/partialjsp/footer.jspf"%>
</html>
