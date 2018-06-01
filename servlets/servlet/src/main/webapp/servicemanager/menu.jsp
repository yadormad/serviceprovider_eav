<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 02.04.2018
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Service menu</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css"/>
</head>
<body class = stpage>
<h1 class = stpage>Service menu</h1>
<form class="inputform" action="attribute/all.jsp" method=get>
    <button name="performButton" type="submit" class=inputform>View all attributes</button>
</form>
<form class="inputform" action="type/all.jsp" method=get>
    <button name="performButton" type="submit" class=inputform>View all service types</button>
</form>
<a href="../index.jsp" class="stpage">Back</a>
</body>
</html>
