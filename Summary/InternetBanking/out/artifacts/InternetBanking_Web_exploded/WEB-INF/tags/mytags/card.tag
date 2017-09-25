<%@ tag body-content="empty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ attribute name="cards" required="true" type="java.util.List<ua.nure.dorotenko.entities.Card>" %>
<%@ attribute name="account" required="true" type="ua.nure.dorotenko.entities.Account" %>
<fmt:setBundle basename="locale"/>
<div id="accordion${account.id}" role="tablist" style="margin-bottom: 5%">
<div class="card">
    <div class="card-header" role="tab" id="heading${account.id}">
        <h5 class="mb-0">
            <a data-toggle="collapse" href="#collapse${account.id}" aria-expanded="true" aria-controls="collapse${account.id}">
                <fmt:message key="cards.tag.account"/>${account.name}. <fmt:message key="cards.tag.balance"/>${account.balance} USD.
                <c:if test="${account.blocked == false}">
                    <a href="/controller?command=createCardPage&accId=${account.id}" class="btn btn-sm btn-info float-right"><fmt:message key="cards.tag.button.createcard"/></a>
                </c:if>
            </a>
        </h5>
    </div>
    <div id="collapse${account.id}" class="collapse show" role="tabpanel" aria-labelledby="heading${account.id}" data-parent="#accordion${account.id}">
        <div class="card-body">
            <c:if test="${empty cards}">
                <p><fmt:message key="cards.tag.nocard"/><a href="/controller?command=createCardPage&accId=${account.id}"><fmt:message key="cards.tag.link.create"/></a></p>
            </c:if>
            <c:forEach items="${cards}" var="card">
                <p><fmt:message key="cards.tag.number"/>${card.number}</p>
                <c:if test="${account.blocked == false}">
                    <a href="/controller?command=paymentPage&cardId=${card.id}" class="btn btn-sm btn-success"><fmt:message key="cards.tag.button.mkpayment"/></a>
                    <a href="/controller?command=changePIN&cardId=${card.id}" class="btn btn-sm btn-danger"><fmt:message key="cards.tag.button.changepin"/></a>
                </c:if>
                <a href="/controller?command=deleteCardPage&cardId=${card.id}" class="btn btn-sm btn-danger"><fmt:message key="cards.tag.button.deletecard"/></a>
                <br>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>
</div>