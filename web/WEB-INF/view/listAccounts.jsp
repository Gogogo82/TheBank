<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
<H1>Accounts of ${client.name}</H1>

<form:form action="${pageContext.request.contextPath}/account/addNewAccount">
    <input type="submit" value="Add new account"/>
</form:form>

<table border="1">
    <tr>
        <th>Number</th>
        <th>Amount</th>
    </tr>

    <c:forEach var="account" items="${accounts}">

        <c:url var="updateURL" value="${pageContext.request.contextPath}/account/updateAccount">
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
            <td><a href="${updateURL}">Edit</a>
                <a href="${deleteURL}">Delete</a>
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
