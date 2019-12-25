<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
<H1>Accounts</H1>
<H2>of ${clientName}</H2>

<c:url var="showUpdateAccountFormURL" value="/showUpdateAccountForm">
    <c:param name="clientId" value="${clientId}"/>
    <c:param name="clientName" value="${clientName}"/>
</c:url>

<form:form action="${showUpdateAccountFormURL}">
    <input type="submit" value="Add new account"/>
</form:form>

<table>
    <tr>
        <th>Number</th>
        <th>Amount</th>
    </tr>

    <c:forEach var="account" items="${accounts}">

        <c:url var="updateURL" value="/updateAccount">
            <c:param name="accountId" value="${account.id}"/>
            <c:param name="clientId" value="${clientId}"/>
            <c:param name="clientName" value="${clientName}"/>
        </c:url>

        <c:url var="deleteURL" value="/deleteAccount">
            <c:param name="accountId" value="${account.id}"/>
        </c:url>

        <tr>
            <td>${account.number}</td>
            <td>${account.amount}</td>
            <td><a href="${updateURL}">Edit</a> <a href="${deleteURL}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<form action="listClients">
    <button>Cancel</button>
</form>

</body>
</html>
