<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>updateAccountForm</title>
</head>
<body>
<H1>Create or update account</H1>
<H2>of ${clientName}</H2>

<c:url var="saveAccountURL" value="/saveAccount">
    <c:param name="clientId" value="${clientId}"/>
    <c:param name="clientName" value="${clientName}"/>
</c:url>

<form:form action="${saveAccountURL}" modelAttribute="account" method="post">
    <label>Account number:</label>
    <br>
    <form:input path="number"/>
    <br>
    <label>Account amount:</label>
    <br>
    ${account.amount}
    <br>
    <input type="submit" value="Save"/>
</form:form>
<br>

<c:url var="listAccountsURL" value="/listAccounts">
    <c:param name="clientId" value="${clientId}"/>
    <c:param name="clientName" value="${clientName}"/>
</c:url>

<form:form action="${listAccountsURL}">
    <button>Cancel</button>
</form:form>
</body>
</html>
