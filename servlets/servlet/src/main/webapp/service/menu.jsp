<%@ page import="hibernate.model.service.types.ServiceType" %><%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 02.04.2018
  Time: 15:36
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
    <link rel="stylesheet" type="text/css" href="../styles/mystyle1.css"/>
</head>
<body class="stpage">
<h1 class="stpage">Service types</h1>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Action</th>
    </tr>
    <c:forEach var="serviceType" items="${userBean.allServiceTypes}">
        <tr>
            <td>${serviceType.name}</td>
            <td>${serviceType.description}</td>
            <td><a class="stpage" href="type/edit.jsp?typeId=${serviceType.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty param.errorMessage && param.errorMessage.equals('notype')}">
    <p class="error">No type was chosen</p>
</c:if>
<a href="../index.jsp" class="stpage">Back</a>
</body>
</html>
