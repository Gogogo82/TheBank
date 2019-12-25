<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>showClients</title>
</head>
<body>
<H1>Clients</H1>

    <button onclick="window.location.href='/client/addNewClient'">Add new client</button>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Address</th>
        <th>Action</th>
    </tr>

    <c:forEach var="client" items="${clientsList}">

        <c:url var="updateURL" value="/client/updateClient">
            <c:param name="clientId" value="${client.id}"/>
        </c:url>

        <c:url var="deleteURL" value="/client/deleteClient">
            <c:param name="clientId" value="${client.id}"/>
        </c:url>

        <c:url var="redirectToAccountControllerURL" value="/client/redirectToAccountController">
            <c:param name="clientId" value="${client.id}"/>
<%--            <c:param name="clientName" value="${client.name}"/>--%>
        </c:url>

        <tr>
            <td><a href="${redirectToAccountControllerURL}">${client.name}</a></td>
            <td>${client.address}</td>
            <td><a href="${updateURL}">Edit</a> <a href="${deleteURL}">Delete</a></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
