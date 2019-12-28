<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accounts</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/styles.css">
</head>
<body>
<H1>Accounts of ${client.firstName}</H1>

<c:url var="addURL" value="${pageContext.request.contextPath}/account/addOrUpdateAccount">
    <c:param name="accountId" value="-1"/>
</c:url>

<form:form action="${addURL}">
    <input class="inputSubmit" type="submit" value="Add new account"/>
</form:form>

<table>
    <tr>
        <th>Number</th>
        <th>Amount</th>
        <th>Action</th>
    </tr>

    <c:forEach var="account" items="${accounts}">

        <c:url var="updateURL" value="${pageContext.request.contextPath}/account/addOrUpdateAccount">
            <c:param name="accountId" value="${account.id}"/>
        </c:url>

        <c:url var="deleteURL" value="${pageContext.request.contextPath}/account/deleteAccount">
            <c:param name="accountId" value="${account.id}"/>
        </c:url>

        <c:url var="transactionURL" value="${pageContext.request.contextPath}/transaction/listTransactions">
            <c:param name="accountId" value="${account.id}"/>
        </c:url>

        <tr>
            <td>${account.number}</td>
            <td>${account.amount}</td>
            <td><a href="${updateURL}">Edit</a> |
                <a href="${deleteURL}">Delete</a> |
                <a href="${transactionURL}">Transactions</a>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="/${pageContext.request.contextPath}client/listClients">
    <button>Back to clients list</button>
</form>

</body>
</html>
