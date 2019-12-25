<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>updateAccountForm</title>
</head>
<body>
<H1>Create or update account for ${clientName}</H1>

<c:url var="saveAccountURL" value="${pageContext.request.contextPath}/account/saveAccount">
    <c:param name="clientId" value="${clientId}"/>
</c:url>

<form:form action="${saveAccountURL}" modelAttribute="account" method="post">
    <form:hidden path="id"/>
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

<c:url var="listAccountsURL" value="${pageContext.request.contextPath}/account/listAccounts">
    <c:param name="clientId" value="${clientId}"/>
</c:url>

<form:form action="${listAccountsURL}">
    <button>Cancel</button>
</form:form>
</body>
</html>
