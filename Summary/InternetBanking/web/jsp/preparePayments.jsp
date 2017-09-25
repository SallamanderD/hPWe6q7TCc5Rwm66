<%@ include file="/partialjsp/taglibs.jspf" %>
<html>
<fmt:message key="payprep.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <div style="overflow-y: scroll; height:600px; background-color: gainsboro">
        <table class="table table-hover table-inverse" id="sortedTable">
            <thead>
            <tr>
                <th onclick="sortTable(1, 'sortedTable')">#</th>
                <th onclick="sortTable(2, 'sortedTable')"><fmt:message key="payprep.table.th1"/></th>
                <th onclick="sortTable(3, 'sortedTable')"><fmt:message key="payprep.table.th2"/></th>
                <th onclick="sortTable(4, 'sortedTable')"><fmt:message key="payprep.table.th3"/></th>
                <th>-</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${preparePayments}" varStatus="loop">
                <tr>
                    <td scope="row">${loop.index + 1}</td>
                    <td>${senderCards[loop.index].number}</td>
                    <td>${receiverCards[loop.index].number}</td>
                    <td>${preparePayments[loop.index].sum}</td>
                    <td><a href="/controller?command=paymentPage&cardId=${senderCards[loop.index].id}&cardNumber=${receiverCards[loop.index].number}&sum=${preparePayments[loop.index].sum}" class="btn btn-sm btn-success"><fmt:message key="payprep.button"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf"%>
</html>
