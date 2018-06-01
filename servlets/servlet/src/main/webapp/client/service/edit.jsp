<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 31.05.2018
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="clientEditorBean" class="hibernate.controller.ClientEditorBean" scope="session">
    <jsp:setProperty name="clientEditorBean" property="*"/>
</jsp:useBean>

${clientEditorBean.init(param.clientId)}
<c:set var="editedService" value="${clientEditorBean.getService(param.serviceTypeId)}" scope="session"/>

<html>
<head>
    <title>Edit service</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css">
</head>
<body class="stpage">
<table>
    <tr>
        <th>Attribute</th>
        <th>Value</th>
        <th>Action</th>
    </tr>
    <c:forEach var="attribute" items="${editedService.getServiceType().getAttributeSet()}">
        <c:choose>
            <c:when test="${attribute.attributeType.name.equals('string')}">
                <c:set var="inputType" value="text"/>
            </c:when>
            <c:when test="${attribute.attributeType.name.equals('integer')}">
                <c:set var="inputType" value="number"/>
            </c:when>
        </c:choose>
        <tr>
            <td>${attribute.name}</td>
            <td>
                <form id="attributeForm" action="edit.jsp" method="get">
                    <c:choose>
                        <c:when test="${attribute.isListed}">
                            <select form="attributeForm" name="catalogValue">
                                <option disabled>Choose value</option>
                                <c:forEach var="value" items="${attribute.getCatalogValueMap().values()}">
                                    <option value="${value.id}">${value.value}</option>
                                </c:forEach>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <input type="${inputType}" name="value" value="${attribute.value}" class="inputform"/>
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" name="attributeId" value="${attribute.id}"/>
                </form>
            </td>
            <td><button form="attributeForm" type="submit" name="action" value="editValue" class="inputform">Edit</button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
