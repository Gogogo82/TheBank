<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        .failed{
            color: red;
        }
        .logout{
            color: green;
        }
    </style>
</head>
<body>
<H1>Login form</H1>

<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
<%-- name="username" & name="password" attributes auto-detected by Spring security --%>
    <p>
        User name: <input type="text" name="username"/>
    </p>

    <p>
        Password: <input type="password" name="password"/>
    </p>

    <input type="submit" value="Login">

    <%-- if login fails, Spring return this page with param ?error   --%>
    <c:if test="${param.error != null}">
        <i class="failed">Invalid user name /password!!!</i>
    </c:if>

    <%-- if logout called, Spring return this page with param ?logout   --%>
    <c:if test="${param.logout != null}">
        <i class="logout">You successfully logged out!</i>
    </c:if>
</form:form>

</body>
</html>
