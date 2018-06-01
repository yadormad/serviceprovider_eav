<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 31.05.2018
  Time: 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="serviceAttributeEditorBean" class="hibernate.controller.ServiceAttributeEditorBean" scope="session">
    <jsp:setProperty name="serviceAttributeEditorBean" property="*"/>
</jsp:useBean>
<c:if test="${param.action.equals('refresh')}">
    ${serviceAttributeEditorBean.refresh()}
</c:if>
${serviceAttributeEditorBean.init(param.attributeId)}
<c:choose>
    <c:when test="${serviceAttributeEditorBean.editedServiceAttribute.attributeType.name.equals('string')}">
        <c:set var="inputType" value="text"/>
    </c:when>
    <c:when test="${serviceAttributeEditorBean.editedServiceAttribute.attributeType.name.equals('integer')}">
        <c:set var="inputType" value="number"/>
    </c:when>
</c:choose>
<c:choose>
    <c:when test="${param.action.equals('edit')}">
        ${serviceAttributeEditorBean.editServiceAttribute(param.name, param.type, param.isListed.equals('true'))}
    </c:when>
    <c:when test="${param.action.equals('editCatalog')}">
        ${serviceAttributeEditorBean.editCatalogValue(param.catalogId, param.catalogValue)}
    </c:when>
    <c:when test="${param.action.equals('removeCatalog')}">
        ${serviceAttributeEditorBean.removeCatalogValue(param.catalogId)}
    </c:when>
    <c:when test="${param.action.equals('addCatalog')}">
        ${serviceAttributeEditorBean.addCatalogValue(param.catalogValue)}
    </c:when>
    <c:when test="${param.action.equals('save')}">
        ${serviceAttributeEditorBean.saveServiceAttribute()}
    </c:when>
</c:choose>

<html>
<head>
    <title>Edit ${serviceAttributeEditorBean.editedServiceAttribute.name} service attribute</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css">
</head>
<body class="stpage">
<h1 class="stpage">Edit ${serviceAttributeEditorBean.editedServiceAttribute.name} service attribute</h1>
<form class="inputform" action="edit.jsp" method=get>
    <div style="display: inline-block">
        <label for=name class = inputform>Name</label>
        <input type=text id=name name=name required=required class = inputform value="${serviceAttributeEditorBean.editedServiceAttribute.name}"/>
    </div>
    <div style="display: inline-block">
        <label for=type class="inputform">Type</label>
        <select id="type" name="type" required>
            <option disabled>Choose type</option>
            <c:forEach var="attributeType" items="${serviceAttributeEditorBean.allTypes}">
                <c:choose>
                    <c:when test="${attributeType.equals(serviceAttributeEditorBean.editedServiceAttribute.attributeType)}">
                        <option selected value="${attributeType.id}">${attributeType.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${attributeType.id}">${attributeType.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div><br/>
    <label for=isListed class="inputform">Is cataloged</label>
    <c:choose>
        <c:when test="${serviceAttributeEditorBean.editedServiceAttribute.listed}">
            <input id=isListed type="checkbox" checked="checked" name="isListed" value="true"/>
        </c:when>
        <c:otherwise>
            <input id=isListed type="checkbox" name="isListed" value="true"/>
        </c:otherwise>
    </c:choose>
    <input type="hidden" name="attributeId" value="${serviceAttributeEditorBean.editedServiceAttribute.id}">
    <button name=action type=submit class=inputform value="edit">Edit</button>
</form>
<c:if test="${serviceAttributeEditorBean.editedServiceAttribute.listed}">
    <h2 class="stpage">Catalog values</h2>
    <table>
        <tr>
            <th>Value</th>
            <th>Action</th>
        </tr>
        <c:forEach var="catalogId" items="${serviceAttributeEditorBean.editedServiceAttribute.catalogValueMap.keySet()}">
            <tr>
                <td><form id="catalogEditForm" method="get"><input type="${inputType}" name="catalogValue" value="${serviceAttributeEditorBean.getCatalogValue(catalogId)}" class="inputform"/></form></td>
                <input form="catalogEditForm" type="hidden" name="attributeId" value="${serviceAttributeEditorBean.editedServiceAttribute.id}">
                <input form="catalogEditForm" type="hidden" name="catalogId" value="${catalogId}">
                <td><button form="catalogEditForm" type="submit" name="action" value="editCatalog" class="inputform">Edit</button><br/>
                    <button form="catalogEditForm" type="submit" name="action" value="removeCatalog" class="inputform">Remove</button></td>
            </tr>
        </c:forEach>
        <tr>
            <td><form id="catalogAddForm" method="get"><input type="${inputType}" name="catalogValue" class="inputform"/></form></td>
            <input form="catalogAddForm" type="hidden" name="attributeId" value="${serviceAttributeEditorBean.editedServiceAttribute.id}">
            <td><button form="catalogAddForm" type="submit" name="action" value="addCatalog" class="inputform">Add</button></td>
        </tr>
    </table>
</c:if>
<br/>
<a href="edit.jsp?attributeId=${serviceAttributeEditorBean.editedServiceAttribute.id}&action=refresh" class="stpage">Refresh</a><br/>
<a href="edit.jsp?attributeId=${serviceAttributeEditorBean.editedServiceAttribute.id}&action=save" class="stpage">Save</a><br/>
<a href="all.jsp" class="stpage">Back</a>
</body>
</html>
