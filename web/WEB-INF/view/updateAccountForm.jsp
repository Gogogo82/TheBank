<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>updateAccountForm</title>
</head>
<body>
<H1>Create or update account</H1>
<H2>of ${account.client.name}</H2>
<H2>of ${account.client.name}</H2>
<form:form action="saveAccount" modelAttribute="account" method="post">
    <form:hidden path="id"/>
    <form:hidden path="client"/>
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
<form action="listAccounts">
    <button>Cancel</button>
</form>
</body>
</html>
