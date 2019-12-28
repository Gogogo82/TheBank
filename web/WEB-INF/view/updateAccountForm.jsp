<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update account form</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/styles.css">
</head>
<body>
<H1>Create or update account for ${client.firstName}</H1>

<form:form action="${pageContext.request.contextPath}/account/saveAccount" modelAttribute="account" method="post">
    <form:hidden path="id"/>
    <label>Account number:</label>
    <br>
    <form:input type="number" min="0" path="number"/>
    <br>
    <br>
    <label>Account amount:</label>
    <br>
    ${account.amount}
    <br>
    <br>
    <input class="inputSubmit" type="submit" value="Save"/>
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
