<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/styles.css">
</head>
<body>
<H2>Status: <%=response.getStatus()%></H2>
<p>Server error: ${exception.getMessage()}</p>
<br>
<a href="${pageContext.request.contextPath}/client/listClients">Return to homepage</a>
</body>
</html>
