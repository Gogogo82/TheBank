<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>showClients</title>
</head>
<body>
<H1>Clients</H1>

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

        <tr>
            <td><a href="${pageContext.request.contextPath}/listAccounts">${client.name}</a></td>
            <td>${client.address}</td>
            <td><a href="${updateURL}">Edit</a></td>
            <td><a href="${deleteURL}">Delete</a></td>
        </tr>
    </c:forEach>

</table>

<form:form action="/addOrUpdateClient" name="">
    <form:input path=""
</form:form>

</body>
</html>
