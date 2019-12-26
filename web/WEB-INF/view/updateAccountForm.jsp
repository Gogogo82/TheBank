<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update account form</title>
</head>
<body>
<H1>Create or update account for ${client.name}</H1>

<form:form action="${pageContext.request.contextPath}/account/saveAccount" modelAttribute="account" method="post">
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
    <c:param name="clientId" value="${client.id}"/>
</c:url>

<form:form action="${listAccountsURL}">
    <button>Cancel</button>
</form:form>
</body>
</html>
