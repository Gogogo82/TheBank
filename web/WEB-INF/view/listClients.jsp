<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Clients</title>
</head>
<body>
<H1>Clients</H1>

    <button onclick="window.location.href='${pageContext.request.contextPath}/client/addNewClient'">Add new client</button>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Address</th>
        <th>Action</th>
    </tr>

    <c:forEach var="client" items="${clientsList}">

        <c:url var="updateURL" value="${pageContext.request.contextPath}/client/updateClient">
            <c:param name="clientId" value="${client.id}"/>
        </c:url>

        <c:url var="deleteURL" value="${pageContext.request.contextPath}/client/deleteClient">
            <c:param name="clientId" value="${client.id}"/>
        </c:url>

        <c:url var="listAccountsURL" value="${pageContext.request.contextPath}/account/listAccounts">
            <c:param name="clientId" value="${client.id}"/>
<%--            <c:param name="clientName" value="${client.name}"/>--%>
        </c:url>

        <tr>
            <td>${client.name}</td>
            <td>${client.address}</td>
            <td>
                <a href="${updateURL}">Edit</a>
                <a href="${deleteURL}">Delete</a>
                <a href="${listAccountsURL}">View accounts</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
