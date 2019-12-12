<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>showClients</title>
</head>
<body>
<H1>Clients</H1>

    <button onclick="window.location.href='showUpdateClientForm'">Add new client</button>

<table>
    <tr>
        <th>Name</th>
        <th>Address</th>
        <th>Action</th>
    </tr>

    <c:forEach var="client" items="${clientsList}">

        <c:url var="updateURL" value="/updateClient">
            <c:param name="clientId" value="${client.id}"/>
        </c:url>

        <c:url var="deleteURL" value="/deleteClient">
            <c:param name="clientId" value="${client.id}"/>
        </c:url>

        <c:url var="listAccounts" value="/listAccounts">
            <c:param name="clientId" value="${client.id}"/>
            <c:param name="clientName" value="${client.name}"/>
        </c:url>


        <tr>
            <td><a href="${listAccounts}">${client.name}</a></td>
            <td>${client.address}</td>
            <td><a href="${updateURL}">Edit</a> <a href="${deleteURL}">Delete</a></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
