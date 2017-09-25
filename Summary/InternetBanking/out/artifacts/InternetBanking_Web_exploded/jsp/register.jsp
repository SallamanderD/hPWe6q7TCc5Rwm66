<%@ include file="/partialjsp/taglibs.jspf" %>
<html>
<fmt:message key="register.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<script src="https://www.google.com/recaptcha/api.js"></script>
<body>
<%@ include file="/partialjsp/navbar.jspf" %>
<div class="container maincontainer">
    <%--<%@ include file="/partialjsp/navbar.jspf"%>--%>
    <div class="row">
        <div class="col">
        </div>
        <div class="col-8">
            <c:if test="${not empty errors}">
                <div class="col-sm-6 col-md-6">
                    <div class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                            ×
                        </button>
                        <span class="glyphicon glyphicon-hand-right"></span> <strong><fmt:message key="register.errors"/></strong>
                        <hr class="message-inner-separator">
                        <c:forEach items="${errors}" var="error">
                            <p>${error}</p>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
            <form action="/controller" method="post">
                <fieldset>
                    <h2><fmt:message key="register.header"/></h2>
                    <hr>
                    <input type="hidden" name="command" value="register">
                    <div class="form-group">
                        <input type="text" name="login" id="login" class="form-control input-lg" placeholder="<fmt:message key="register.login.placeholder"/>" pattern="\w{8,25}" title="<fmt:message key="register.login.title"/>">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" id="password" class="form-control input-lg"
                               placeholder="<fmt:message key="register.password.placeholder"/>" title="<fmt:message key="register.password.title"/>" pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*(_|[^\w])).{8,25}">
                    </div>
                    <div class="form-group">
                        <input type="text" name="fullName" id="fullName" class="form-control input-lg"
                               placeholder="<fmt:message key="register.fullname.placeholder"/>" pattern="(\w+\s*)+">
                    </div>
                    <div class="form-group">
                        <input type="email" name="email" id="email" class="form-control input-lg" placeholder="<fmt:message key="register.email.placeholder"/>">
                    </div>
                    <div class="form-group">
                        <input type="tel" name="telephone" id="telephone" class="form-control input-lg"
                               placeholder="<fmt:message key="register.telephone.placeholder"/>" pattern="\+380(95|66|50|99|67|68)\d{7}">
                    </div>
                    <div class="g-recaptcha"
                         data-sitekey="6Le6_TEUAAAAAJR5H-c3OaSRsYprU4Z2QchS_5y8"></div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <input type="submit" class="btn btn-lg btn-success btn-block" value="<fmt:message key="register.button.submit"/>">
                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6">
                            <a href="/jsp/login.jsp" class="btn btn-lg btn-primary btn-block"><fmt:message key="register.link.login"/></a>
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
