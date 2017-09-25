<%@ include file="/partialjsp/taglibs.jspf" %>
<html>
<fmt:message key="adminpanel.title" var="title"/>
<%@ include file="/partialjsp/resource.jspf" %>
<body>
<%@ include file="/partialjsp/navbar.jspf"%>
<div class="container maincontainer">
    <div class="col">
        <h3><fmt:message key="adminpanel.table1.header"/></h3>
        <div style="overflow-y: scroll; height:400px; background-color: gainsboro">
            <table class="table table-hover table-inverse" id="users">
                <thead>
                <tr>
                    <th>#</th>
                    <th onclick="sortTable(1, 'users')"><fmt:message key="adminpanel.table1.th1"/></th>
                    <th onclick="sortTable(2, 'users')"><fmt:message key="adminpanel.table1.th2"/></th>
                    <th onclick="sortTable(3, 'users')"><fmt:message key="adminpanel.table1.th3"/></th>
                    <th onclick="sortTable(4, 'users')"><fmt:message key="adminpanel.table1.th4"/></th>
                    <th onclick="sortTable(5, 'users')"><fmt:message key="adminpanel.table1.th5"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td scope="row">${user.id}</td>
                        <td>${user.fullName}</td>
                        <td>${user.login}</td>
                        <td>${user.telephone}</td>
                        <td>${user.email}</td>
                        <td>${user.banned}</td>
                        <c:if test="${user.roleId == 2}">
                            <td><a href="/controller?command=makeAdmin&userId=${user.id}" class="btn btn-sm btn-success"><fmt:message key="adminpanel.table1.button.makeadmin"/></a></td>
                        </c:if>
                        <c:if test="${user.roleId == 1}"><td></td></c:if>
                        <c:if test="${user.banned == false && user.roleId == 2}">
                            <td><a href="/controller?command=changeBanned&userId=${user.id}&banned=true" class="btn btn-sm btn-success"><fmt:message key="adminpanel.table1.button.ban"/></a></td>
                        </c:if>
                        <c:if test="${user.banned == true}">
                            <td><a href="/controller?command=changeBanned&userId=${user.id}&banned=false" class="btn btn-sm btn-success"><fmt:message key="adminpanel.table1.button.unban"/></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <h3><fmt:message key="adminpanel.table2.header"/></h3>
        <div style="overflow-y: scroll; height:400px; background-color: gainsboro">
            <table class="table table-hover table-inverse" id="req">
                <thead>
                <tr>
                    <th>#</th>
                    <th onclick="sortTable(1, 'req')"><fmt:message key="adminpanel.table2.th1"/></th>
                    <th onclick="sortTable(2, 'req')"><fmt:message key="adminpanel.table2.th2"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requests}" var="item">
                    <tr>
                        <td scope="row">${item.id}</td>
                        <td>${item.accountId}</td>
                        <td>${item.datetime}</td>
                        <c:if test="${item.satisfied == false}">
                            <td><a href="/controller?command=blockAccount&accId=${item.accountId}&block=false" class="btn btn-sm btn-success"><fmt:message key="adminpanel.table2.button.unblock"/></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <h3><fmt:message key="adminpanel.table3.header"/></h3>
        <div style="overflow-y: scroll; height:400px; background-color: gainsboro">
            <table class="table table-hover table-inverse" id="acc">
                <thead>
                <tr>
                    <th onclick="sortTable(1, 'acc')"><fmt:message key="adminpanel.table3.th1"/></th>
                    <th onclick="sortTable(2, 'acc')"><fmt:message key="adminpanel.table3.th2"/></th>
                    <th onclick="sortTable(3, 'acc')"><fmt:message key="adminpanel.table3.th3"/></th>
                    <th onclick="sortTable(4, 'acc')"><fmt:message key="adminpanel.table3.th4"/></th>
                    <th onclick="sortTable(5, 'acc')"><fmt:message key="adminpanel.table3.th5"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${accounts}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.userId}</td>
                        <td>${item.number}</td>
                        <td>${item.name}</td>
                        <td>${item.blocked}</td>
                        <c:if test="${item.blocked == false}">
                            <td><a href="/controller?command=blockAccount&accId=${item.id}&block=true" class="btn btn-sm btn-success"><fmt:message key="adminpanel.table3.button.block"/></a></td>
                        </c:if>
                        <c:if test="${item.blocked == true}">
                            <td><a href="/controller?command=blockAccount&accId=${item.id}&block=false" class="btn btn-sm btn-success"><fmt:message key="adminpanel.table3.button.unblock"/></a></td>
                        </c:if>
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
