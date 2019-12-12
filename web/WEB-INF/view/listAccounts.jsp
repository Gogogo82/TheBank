<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
<H1>Accounts</H1>
<H2>of ${clientName}</H2>

<%--<c:url var="showUpdateAccountForm" value="/showUpdateAccountForm">--%>
<%--    <c:param name="clientName" value="${clientName}"/>--%>
<%--</c:url>--%>

<%--<form:form action="showUpdateAccountForm">--%>
<%--    <input type="submit" value="Add new account"/>--%>
<%--</form:form>--%>

<form action="showUpdateAccountForm">
    <input type="submit" value="Add new account"/>
</form>

<table>
    <tr>
        <th>Number</th>
        <th>Amount</th>
    </tr>

    <form:forEach var="account" items="${accounts}">
        <tr>
            <td>${account.number}</td>
            <td>${account.amount}</td>
        </tr>
    </form:forEach>
</table>

</body>
</html>
