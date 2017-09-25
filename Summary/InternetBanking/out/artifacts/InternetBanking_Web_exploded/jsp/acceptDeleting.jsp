<%@ include file="/partialjsp/taglibs.jspf" %>
<html>
<fmt:message key="acceptdeleting.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <p>${error}</p>
    <div class="col">
        <form action="/controller" method="post">
            <input type="hidden" value="deleteCard" name="command"/>
            <input type="hidden" value="${card.id}" name="cardId"/>
            <label for="PIN"><fmt:message key="acceptdeleting.enterpin"/>${card.number}</label>
            <div class="form-group">
                <input type="text" name="PIN" id="PIN" class="form-control input-lg" placeholder="PIN" pattern="\d{4}" title="<fmt:message key="acceptdeleting.inputtitle"/>">
            </div>
            <div>
                <input type="submit" class="btn btn-lg btn-success btn-block" value="<fmt:message key="acceptdeleting.button.submit"/>">
            </div>
        </form>
    </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf"%>
</html>
