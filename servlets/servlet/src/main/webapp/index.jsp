<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 23.03.2018
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error_page.jsp" %>
<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="styles/mystyle1.css"/>
</head>
<body class = stpage>
<h1 class = stpage>Activity menu</h1>
<form class="inputform" action="client/all.jsp" method=get>
    <button name="performButton" type="submit" class=inputform>View all clients</button>
</form>
<form class="inputform" action="servicemanager/menu.jsp" method=get>
    <button name="performButton" type="submit" class=inputform>View service manager</button>
</form>
</body>
</html>
