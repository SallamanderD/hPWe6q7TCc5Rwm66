<%@ include file="/partialjsp/taglibs.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<fmt:message key="homepage.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <div class="row">
        <h5 style="margin-left: 1%"><fmt:message key="homepage.accounts.title"/></h5>
        <div class="col-7">
            <fmt:message key="homepage.accounts.sort"/><a href="/controller?command=homepage&sort=name"><fmt:message key="homepage.accounts.sort.name"/></a> | <a href="/controller?command=homepage&sort=balance"><fmt:message key="homepage.accounts.sort.balance"/></a> | <a href="/controller?command=homepage&sort=number"><fmt:message key="homepage.accounts.sort.number"/></a>
            <c:if test="${not empty accounts}">
                    <c:forEach var="account" items="${accounts}">
                        <my:account account="${account}"/>
                    </c:forEach>
            </c:if>
        </div>
        <div class="col">
            <p></p>
            <h3><p><a href="javascript:changeDivVisibility('createAcc')"><fmt:message key="homepage.account.createaccount"/> </a></p></h3>
            <div style="display: none" id="createAcc">
                <div class="card">
                    <div class="card-header">
                        <label for="name">
                            <fmt:message key="homepage.account.label"/>
                        </label>
                    </div>
                    <div class="card-block">
                        <form method="post" action="/controller">
                            <input type="hidden" name="command" value="createAcc">
                            <div class="form-group" style="margin: 3%">
                                <input id="name" type="text" name="name" class="form-control" placeholder="<fmt:message key="homepage.placeholder.name"/>">
                                <input type="submit" style="margin-top: 3%" class="btn btn-sm btn-success btn-block" value="<fmt:message key="homepage.button.createacc"/>">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf"%>
</html>
