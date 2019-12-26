<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create or update transaction form </title>
</head>
<body>
<H1>Create or update account for ${transaction.accountFrom}</H1>

<form:form action="${pageContext.request.contextPath}/transaction/saveTransaction" modelAttribute="transaction" method="post">
    <form:hidden path="id"/>
    <label>Credit:</label>
    <br>
    <form:select path="accountFromId">
        <form:options items="${accountsMap}"/>
    </form:select>
    <br>
    <label>Debit:</label>
    <br>
    <form:select path="accountToId">
        <form:options items="${accountsMap}"/>
    </form:select>
    <br>
    <label>Amount:</label>
    <br>
    <form:input path="amount"/>
    <br>
    <input type="submit" value="Save"/>
</form:form>
<br>

<c:url var="listTransactionsURL" value="${pageContext.request.contextPath}/transaction/listTransactions">
    <c:param name="accountId" value="${currentAccount.id}"/>
</c:url>

<form:form action="${listTransactionsURL}">
    <button>Cancel</button>
</form:form>
</body>
</html>
