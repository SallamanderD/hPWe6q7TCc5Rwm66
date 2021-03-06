<%@ include file="/partialjsp/taglibs.jspf" %>
<html>
<fmt:message key="login.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<script src="https://www.google.com/recaptcha/api.js"></script>
<body>
<%@ include file="/partialjsp/navbar.jspf" %>
<div class="container maincontainer">
    <%--<%@ include file="/partialjsp/navbar.jspf"%>--%>
    <div class="row">
        <c:if test="${not empty errors}">
            <div class="col-sm-6 col-md-6">
                <div class="alert alert-danger">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                        ×
                    </button>
                    <span class="glyphicon glyphicon-hand-right"></span> <strong><fmt:message key="login.errors"/></strong>
                    <hr class="message-inner-separator">
                    <c:forEach items="${errors}" var="error">
                        <p>${error}</p>
                    </c:forEach>
                </div>
            </div>
        </c:if>
        </div>
        <div class="col">
        </div>
        <div class="col-8">
            <form action="/controller" method="post">
                <fieldset>
                    <h2><fmt:message key="login.header"/></h2>
                    <hr>
                    <input type="hidden" name="command" value="login">
                    <div class="form-group">
                        <input type="text" name="login" id="login" class="form-control input-lg" placeholder="<fmt:message key="login.input.placeholder"/>" pattern="\w{8,25}" title="<fmt:message key="login.input.title"/>">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" id="password" class="form-control input-lg"
                               placeholder="<fmt:message key="login.password.placeholder"/>" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[^\w])).{8,25}" title="<fmt:message key="login.password.title"/>">
                    </div>
                    <div class="g-recaptcha"
                         data-sitekey="6Le6_TEUAAAAAJR5H-c3OaSRsYprU4Z2QchS_5y8"></div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <input type="submit" class="btn btn-lg btn-success btn-block" value="<fmt:message key="login.button.signin"/>">
                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <a href="/jsp/register.jsp" class="btn btn-lg btn-primary btn-block"><fmt:message key="login.button.register"/></a>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="col">
        </div>
    </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf" %>
</html>
