<%@ include file="/partialjsp/taglibs.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<fmt:message key="replenish.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <div class="col">
        <form action="/controller" method="post">
            <input type="hidden" name="command" value="replenish">
            <div class="form-group">
                <label for="account"><fmt:message key="replenish.select"/></label>
                <select name="account" class="custom-select" id="account">
                    <c:forEach items="${accounts}" var="account">
                            <option value="${account.id}">${account.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="sum"><fmt:message key="replenish.sum"/></label>
                <input type="number" class="form-control" id="sum" value="${sum}" name="sum" placeholder="<fmt:message key="replenish.sum.placeholder"/>" pattern="\d+" title="<fmt:message key="replenish.sum.title"/>">
            </div>
            <input type="submit" value="<fmt:message key="replenish.submit"/>" class="btn btn-success">
        </form>
    </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf"%>
</html>
