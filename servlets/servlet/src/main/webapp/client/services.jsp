<%@ page import="javax.ejb.RemoveException" %><%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 28.03.2018
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../error_page.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="clientEditorBean" class="hibernate.controller.ClientEditorBean" scope="session">
    <jsp:setProperty name="clientEditorBean" property="*"/>
</jsp:useBean>

${clientEditorBean.init(param.clientId)}
<c:choose>
    <c:when test="${param.action.equals('addService')}">
        ${clientEditorBean.addService(param.serviceTypeId)}
    </c:when>
    <c:when test="${param.action.equals('removeService')}">
        ${clientEditorBean.removeService(param.serviceTypeId)}
    </c:when>
</c:choose>

<html>
<head>
    <title>${clientEditorBean.editedClient.login}'s services</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css"/>
</head>
<body class="stpage">
<h1 class="stpage">${clientEditorBean.editedClient.login}'s services</h1>
<p>Client info: ${clientEditorBean.editedClient.info}</p>
<table>
    <tr>
        <th>Type</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:if test="${not empty clientEditorBean.editedClient.serviceObjectMap}">
        <c:forEach items="${clientEditorBean.editedClient.serviceObjectMap.keySet()}" var="usedServiceType">
            <tr>
                <td>${usedServiceType.name}</td>
                <td>active</td>
                <td>
                    <a href="service/edit.jsp?clientId=${clientEditorBean.editedClient.id}&serviceId=${clientEditorBean.editedClient.serviceObjectMap.get(usedServiceType).id}" class="tableform">Edit</a><br/>
                    <a href="services.jsp?clientId=${clientEditorBean.editedClient.id}&serviceTypeId=${usedServiceType.id}&action=removeService" class="tableform">Remove</a><br/>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:forEach items="${clientEditorBean.unusedServiceTypes}" var="unusedServiceType">
        <tr>
            <td>${unusedServiceType.name}</td>
            <td>not active</td>
            <td>
                <a href="services.jsp?clientId=${clientEditorBean.editedClient.id}&serviceTypeId=${unusedServiceType.id}&action=addService" class="tableform">Add</a><br/>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="all.jsp" class="stpage">Back</a>

</body>
</html>
