<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transactions</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/styles.css">
</head>
<body>
<H1>Transactions on ${currentAccount.number}</H1>

<c:url var="addTransactionURL" value="${pageContext.request.contextPath}/transaction/addOrUpdateTransaction">
    <c:param name="transactionId" value="-1"/>
</c:url>

<form:form action="${addTransactionURL}">
    <input class="inputSubmit" type="submit" value="Add new transaction"/>
</form:form>

<table>
    <tr>
        <th>Date</th>
        <th>Credit</th>
        <th>Debit</th>
        <th>Amount</th>
        <th>Action</th>
    </tr>

    <c:forEach var="transaction" items="${transactionList}">

        <c:url var="updateURL" value="${pageContext.request.contextPath}/transaction/addOrUpdateTransaction">
            <c:param name="transactionId" value="${transaction.id}"/>
        </c:url>

        <c:url var="deleteURL" value="${pageContext.request.contextPath}/transaction/deleteTransaction">
            <c:param name="transactionId" value="${transaction.id}"/>
        </c:url>

        <tr>
            <td>${transaction.date}</td>
            <td>${transaction.accountFrom.number}</td>
            <td>${transaction.accountTo.number}</td>
            <td>${transaction.amount}</td>
            <td><a href="${updateURL}">Edit</a> |
                <a href="${deleteURL}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:url var="listAccountsURL" value="${pageContext.request.contextPath}/account/listAccounts">
    <c:param name="clientId" value="${currentAccount.client.id}"/>
</c:url>

<form:form action="${listAccountsURL}">
    <input class="inputSubmit" type="submit" value="Back to accounts list"/>
</form:form>

</body>
</html>
