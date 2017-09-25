<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="account" required="true" type="ua.nure.dorotenko.entities.Account" %>
<fmt:setBundle basename="locale"/>
<div id="accordion${account.id}" role="tablist" style="margin-bottom: 5%">
<div class="card">
    <div class="card-header" role="tab" id="heading${account.id}">
        <h5 class="mb-0">
            <a data-toggle="collapse" href="#collapse${account.id}" aria-expanded="true" aria-controls="collapse${account.id}">
                <fmt:message key="tag.account.name"/> ${account.name}.
            </a>
        </h5>
    </div>
    <div id="collapse${account.id}" class="collapse show" role="tabpanel" aria-labelledby="heading${account.id}" data-parent="#accordion${account.id}">
        <div class="card-body">
            <p><fmt:message key="tag.account.balance"/>${account.balance} USD.</p>
            <p><fmt:message key="tag.account.accountnumber"/>${account.number}</p>
            <c:if test="${account.blocked == false}">
                <a href="/controller?command=replenishPage&accId=${account.id}" class="btn btn-sm btn-success"><fmt:message key="tag.account.button.replenish"/></a>
                <a href="/controller?command=createCardPage&accId=${account.id}" class="btn btn-sm btn-success"><fmt:message key="tag.account.button.createcard"/></a>
                <a href="/controller?command=block&accId=${account.id}" class="btn btn-sm btn-danger"><fmt:message key="tag.account.button.blockaccount"/></a>
            </c:if>
            <c:if test="${account.blocked == true}">
                <a href="/controller?command=sendUnblock&accId=${account.id}" class="btn btn-sm btn-danger"><fmt:message key="tag.account.button.sendunreq"/></a>
            </c:if>
            <br>
            <a href="/controller?command=cards&accId=${account.id}" class="float-right"><fmt:message key="tag.account.vctta"/></a>
        </div>
    </div>
</div>
</div>