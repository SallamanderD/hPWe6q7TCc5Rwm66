<%@ include file="/partialjsp/taglibs.jspf" %>
<html>
<fmt:message key="payhistory.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <div class="col">
        <h3><fmt:message key="payhistory.table1.header"/></h3>
        <div style="overflow-y: scroll; height:400px; background-color: gainsboro">
            <table class="table table-hover table-inverse" id="recTable">
                <thead>
                <tr>
                    <th>#</th>
                    <th onclick="sortTable(1, 'recTable')"><fmt:message key="payhistory.table1.th1"/></th>
                    <th onclick="sortTable(2, 'recTable')"><fmt:message key="payhistory.table1.th2"/></th>
                    <th onclick="sortTable(3, 'recTable')"><fmt:message key="payhistory.table1.th3"/></th>
                    <th onclick="sortTable(4, 'recTable')"><fmt:message key="payhistory.table1.th4"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${receivePayment}" varStatus="loop">
                    <tr>
                        <td scope="row">${loop.index + 1}</td>
                        <td>${receiveSenderCards[loop.index].number}</td>
                        <td>${receiveTargetCards[loop.index].number}</td>
                        <td>${receivePayment[loop.index].sum}</td>
                        <td>${receivePayment[loop.index].datetime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <h3><fmt:message key="payhistory.table2.header"/></h3>
        <div style="overflow-y: scroll; height:400px; background-color: gainsboro">
            <table class="table table-hover table-inverse" id="sentTable">
                <thead>
                <tr>
                    <th>#</th>
                    <th onclick="sortTable(1, 'sentTable')"><fmt:message key="payhistory.table2.th1"/></th>
                    <th onclick="sortTable(2, 'sentTable')"><fmt:message key="payhistory.table2.th2"/></th>
                    <th onclick="sortTable(3, 'sentTable')"><fmt:message key="payhistory.table2.th3"/></th>
                    <th onclick="sortTable(4, 'sentTable')"><fmt:message key="payhistory.table2.th4"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sentPayment}" varStatus="loop">
                    <tr>
                        <td scope="row">${loop.index + 1}</td>
                        <td>${sentSenderCards[loop.index].number}</td>
                        <td>${sentTargetCards[loop.index].number}</td>
                        <td>${sentPayment[loop.index].sum}</td>
                        <td>${sentPayment[loop.index].datetime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<%@ include file="/partialjsp/footer.jspf"%>
</html>
