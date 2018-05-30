<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 24.05.2018
  Time: 7:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="userBean" class="hibernate.controller.ControllerBean">
    <jsp:setProperty name="userBean" property="*"/>
</jsp:useBean>

<html>
<head>
    <title>Service types</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css/">
</head>
<body class="stpage">
<h1 class="stpage">Service types</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
    </tr>
    <c:forEach var="serviceType" items="${userBean.allServiceTypes}">
        <tr>
            <td><c:out value="${serviceType.id}"/></td>
            <td><c:out value="${serviceType.name}"/></td>
            <td><c:out value="${serviceType.description}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="menu.jsp" class="stpage">Back</a>
</body>
</html>
