<%@ include file="/partialjsp/taglibs.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<fmt:message key="profile.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <div class="col">
        <h4><fmt:message key="profile.header"/></h4>
        <form method="post" action="/controller">
            <input type="hidden" name="command" value="setLang">
            <select id="language" name="language" class="custom-select">
                <option value="en" ${sessionScope.language == 'en' ? 'selected' : ''}><fmt:message key="profile.select.op1"/></option>
                <option value="ru" ${sessionScope.language == 'ru' ? 'selected' : ''} ${empty sessionScope.language ? 'selected' : ''}><fmt:message key="profile.select.op2"/></option>
            </select>
            <input type="submit" class="btn btn-success" value="<fmt:message key="profile.button"/>">
        </form>
    </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf"%>
</html>
