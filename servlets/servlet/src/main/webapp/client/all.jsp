<%@ page errorPage="../error_page.jsp" %><%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 23.03.2018
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="controllerBean" class="hibernate.controller.ControllerBean">
    <jsp:setProperty name="controllerBean" property="*"/>
</jsp:useBean>
<c:if test="${param.action.equals('removeClient')}">
    ${controllerBean.deleteClient(param.clientId)}
</c:if>
<html>
<head>
    <title>Clients table</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css"/>
</head>
<body class="stpage">
<h1 class="stpage">Clients table</h1>
<table>
    <tr>
        <th>Login</th>
        <th>Action</th>
    </tr>
    <c:forEach var="client" items="${controllerBean.allClients}">
        <tr>
            <td>${client.login}</td>
            <td>
                <a href="all.jsp?action=removeClient&clientId=${client.id}" class="tableform">Remove</a><br/>
                <a href="edit.jsp?clientId=${client.id}" class="tableform">Edit</a>
                <a href="services.jsp?clientId=${client.id}" class="tableform">Services</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="new.jsp" class="stpage">New</a><br/>
<a href="../index.jsp" class="stpage">Back</a>
</body>
</html>
