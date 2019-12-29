<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update client form</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/styles.css">
</head>
<body>
<H1>Create or update client</H1>
<form:form action="${pageContext.request.contextPath}/client/saveClient" modelAttribute="client" method="post">
    <form:hidden path="id"/>
    <label>First name:</label>
    <br>
    <form:input path="firstName"/>
    <form:errors path="firstName" cssClass="error"/>
    <br>
    <br>
    <label>Last name:</label>
    <br>
    <form:input path="lastName"/>
    <form:errors path="lastName" cssClass="error"/>
    <br>
    <br>
    <label>Address:</label>
    <br>
    <form:input path="address"/>
    <form:errors path="address" cssClass="error"/>
    <br>
    <br>
    <label>Phone (format: +71234567890):</label>
    <br>
    <form:input path="phone"/>
    <form:errors path="phone" cssClass="error"/>
    <br>
    <br>
    <input class="inputSubmit" type="submit" value="Save"/>
</form:form>
<br>
<form action="${pageContext.request.contextPath}/client/listClients">
    <button>Cancel</button>
</form>
</body>
</html>
