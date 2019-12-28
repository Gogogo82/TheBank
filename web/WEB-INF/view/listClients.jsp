<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Clients</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/styles.css">
</head>
<body>
<H1>Clients</H1>

<c:url var="addURL" value="${pageContext.request.contextPath}/client/addOrUpdateClient">
    <c:param name="clientId" value="-1"/>
</c:url>

<form:form action="${addURL}">
    <input class="inputSubmit" type="submit" value="Add new client">
</form:form>

<table>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Action</th>
    </tr>

    <c:forEach var="client" items="${clientsList}">

        <c:url var="updateURL" value="${pageContext.request.contextPath}/client/addOrUpdateClient">
            <c:param name="clientId" value="${client.id}"/>
        </c:url>

        <c:url var="deleteURL" value="${pageContext.request.contextPath}/client/deleteClient">
            <c:param name="clientId" value="${client.id}"/>
        </c:url>

        <c:url var="listAccountsURL" value="${pageContext.request.contextPath}/account/listAccounts">
            <c:param name="clientId" value="${client.id}"/>
        </c:url>

        <tr>
            <td>${client.firstName}</td>
            <td>${client.lastName}</td>
            <td>${client.address}</td>
            <td>${client.phone}</td>
            <td>
                <a href="${updateURL}">Edit</a> |
                <a href="${deleteURL}">Delete</a> |
                <a href="${listAccountsURL}">View accounts</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
