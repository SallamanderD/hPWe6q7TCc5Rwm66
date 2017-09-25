<%@ include file="/partialjsp/taglibs.jspf" %>
<html>
<fmt:message key="createcard.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <p>${error}</p>
    <div class="col">
        <form action="/controller" method="post">
            <input type="hidden" value="createCard" name="command"/>
            <input type="hidden" value="${account.id}" name="accountId"/>
            <label for="PIN"><fmt:message key="createcard.pin"/></label>
            <div class="form-group">
                <input type="number" name="PIN" id="PIN" class="form-control input-lg" placeholder="PIN" pattern="\d{4}" title="<fmt:message key="createcard.title"/>">
            </div>
            <div>
                <input type="submit" class="btn btn-lg btn-success btn-block" value="<fmt:message key="createcard.button.submit"/>">
            </div>
        </form>
    </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf"%>
</html>
