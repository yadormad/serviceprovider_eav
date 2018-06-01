<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 31.05.2018
  Time: 2:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="controllerBean" class="hibernate.controller.ControllerBean">
    <jsp:setProperty name="controllerBean" property="*"/>
</jsp:useBean>
<jsp:useBean id="serviceTypeEditorBean" class="hibernate.controller.ServiceTypeEditorBean" scope="session">
    <jsp:setProperty name="serviceTypeEditorBean" property="*"/>
</jsp:useBean>

<c:choose>
    <c:when test="${param.action.equals('removeAttribute')}">
        ${controllerBean.removeAttribute(param.attributeId)}
        ${serviceTypeEditorBean.refresh()}
    </c:when>
    <c:when test="${param.action.equals('addAttribute')}">
        <c:choose>
            <c:when test="${not empty param.isListed && param.isListed.equals('true')}">
                <c:set var="isListed" value="true"/>
            </c:when>
            <c:otherwise>
                <c:set var="isListed" value="false"/>
            </c:otherwise>
        </c:choose>
        ${controllerBean.addAttribute(param.name, param.type, isListed)}
        ${serviceTypeEditorBean.refresh()}
    </c:when>
</c:choose>

<html>
<head>
    <title>All attributes</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css">
</head>
<body class="stpage">
<h1 class="stpage">All attributes</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Is cataloged</th>
            <th>Action</th>
        </tr>
        <c:forEach var="serviceAttribute" items="${controllerBean.allAttributes}">
            <tr>
                <td>${serviceAttribute.name}</td>
                <td>${serviceAttribute.attributeType.name}</td>
                <td>
                    <c:choose>
                        <c:when test="${serviceAttribute.listed}">
                            +
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="all.jsp?action=removeAttribute&attributeId=${serviceAttribute.id}" class="tableform">Remove</a><br/>
                    <a href="edit.jsp?attributeId=${serviceAttribute.id}" class="tableform">Edit</a><br/>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><form id="addForm"><input type="text" name="name" value="new" class="inputform"/></form></td>
            <td>
                <select form="addForm" name="type" required>
                    <option disabled>Choose type</option>
                    <c:forEach var="attributeType" items="${controllerBean.allAttributeTypes}">
                        <option value="${attributeType.id}">${attributeType.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input form="addForm" type="checkbox" name="isListed" value="true"/></td>
            <td><button form="addForm" type="submit" name="action" value="addAttribute" class="inputform">Add</button></td>
        </tr>
    </table>
<br/>
<a href="../menu.jsp" class="stpage">Back</a>
</body>
</html>