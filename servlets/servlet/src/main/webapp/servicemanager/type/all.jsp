<%@ page import="hibernate.model.service.types.ServiceType" %><%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 02.04.2018
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="controllerBean" class="hibernate.controller.ControllerBean">
    <jsp:setProperty name="controllerBean" property="*"/>
</jsp:useBean>
<c:choose>
    <c:when test="${param.action.equals('addType')}">
        ${controllerBean.addServiceType(param.name, param.description)}
    </c:when>
    <c:when test="${param.action.equals('removeType')}">
        ${controllerBean.removeServiceType(param.typeId)}
    </c:when>
</c:choose>

<html>
<head>
    <title>Service types</title>
    <link rel="stylesheet" type="text/css" href="../../styles/mystyle1.css"/>
</head>
<body class="stpage">
<h1 class="stpage">Service types</h1>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Action</th>
    </tr>
    <c:forEach var="serviceType" items="${controllerBean.allServiceTypes}">
        <tr>
            <td>${serviceType.name}</td>
            <td>${serviceType.description}</td>
            <td><a class="tableform" href="edit.jsp?typeId=${serviceType.id}">Edit</a><br/>
                <a class="tableform" href="all.jsp?typeId=${serviceType.id}&action=removeType">Remove</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td><form id="addForm" action="all.jsp" method="get"><input type="text" name="name" required="required" class="inputform"/></form></td>
        <td><input form="addForm" type="text" name="description" required="required" class="inputform"/></td>
        <td><button form="addForm" type="submit" name="action" value="addType" class="inputform">Add</button></td>
    </tr>
</table>
<a href="../menu.jsp" class="stpage">Back</a>
</body>
</html>
