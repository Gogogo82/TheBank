<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>updateClient</title>
</head>
<body>
<H1>Update client</H1>
<form:form action="saveClient" modelAttribute="client" method="post">
    <form:hidden path="id"/>
    <label>Name:</label>
    <br>
    <form:input path="name"/>
    <br>
    <label>Address:</label>
    <br>
    <form:input path="address"/>
    <br>
    <input type="submit" value="Save"/>
</form:form>
<br>
<form action="listClients">
    <button>Cancel</button>
</form>
</body>
</html>
