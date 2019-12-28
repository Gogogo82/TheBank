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
    <br>
    <br>
    <label>Last name:</label>
    <br>
    <form:input path="lastName"/>
    <br>
    <br>
    <label>Address:</label>
    <br>
    <form:input path="address"/>
    <br>
    <br>
    <label>Phone:</label>
    <br>
    <form:input path="phone"/>
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
