<%@ include file="/partialjsp/taglibs.jspf" %>
<html>
<fmt:message key="cards.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <div class="row">
        <h5 style="margin-left: 1%"><fmt:message key="cards.header"/></h5>
        <div class="col-7">
            <c:if test="${not empty accounts}">
                <c:forEach items="${accounts}" var="account" varStatus="loop">
                        <my:card cards="${cards[loop.index]}" account="${account}"/>
                </c:forEach>
            </c:if>
        </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf"%>
</html>
