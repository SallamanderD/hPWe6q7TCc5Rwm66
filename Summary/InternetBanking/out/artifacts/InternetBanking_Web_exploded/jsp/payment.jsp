<%@ include file="/partialjsp/taglibs.jspf" %>
<html>
<fmt:message key="payment.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <c:if test="${not empty errors}">
        <div class="col-sm-6 col-md-6">
            <div class="alert alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                    ×
                </button>
                <span class="glyphicon glyphicon-hand-right"></span> <strong><fmt:message key="payment.errors"/></strong>
                <hr class="message-inner-separator">
                <c:forEach items="${errors}" var="error">
                    <p>${error}</p>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <div class="col">
        <form action="/controller" method="post">
            <input type="hidden" name="command" value="makePayment">
            <div class="form-group">
                <label for="card"><fmt:message key="payment.selcard"/></label>
                <select name="card" class="custom-select" id="card">
                    <c:forEach items="${cards}" var="card">
                        <c:if test="${card.id == cardId}">
                            <option value="${card.id}" selected>${card.number}</option>
                        </c:if>
                        <c:if test="${card.id != cardId}">
                            <option value="${card.id}">${card.number}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="pin"><fmt:message key="payment.enterpin"/></label>
                <input type="text" class="form-control" id="pin" name="PIN" placeholder="PIN" pattern="\d{4}" title="<fmt:message key="payment.pin.title"/>">
            </div>
            <div class="form-group">
                <label for="cardTo"><fmt:message key="payment.cardnum"/></label>
                <input type="text" class="form-control" id="cardTo" name="cardTo" value="${cardNumber}" placeholder="<fmt:message key="payment.cardnum.placeholder"/>" pattern="\d{16}" title="<fmt:message key="payment.cardnum.title"/>">
            </div>
            <div class="form-group">
                <label for="sum"><fmt:message key="payment.sum"/></label>
                <input type="number" class="form-control" id="sum" value="${sum}" name="sum" placeholder="<fmt:message key="payment.sum.placeholder"/>" pattern="\d+" title="<fmt:message key="payment.sum.title"/>r">
            </div>
            <div class="checkbox">
                <label><input name="prepared" type="checkbox" value="true"> <fmt:message key="payment.prepare"/></label>
            </div>
            <input type="submit" value="<fmt:message key="payment.submit"/>" class="btn btn-success">
        </form>
    </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf"%>
</html>
