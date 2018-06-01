<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 29.05.2018
  Time: 5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="serviceTypeEditorBean" class="hibernate.controller.ServiceTypeEditorBean" scope="session">
    <jsp:setProperty name="serviceTypeEditorBean" property="*"/>
</jsp:useBean>
<c:if test="${param.action.equals('refresh')}">
    ${serviceTypeEditorBean.refresh()}
</c:if>
${serviceTypeEditorBean.init(param.typeId)}

<c:choose>
    <c:when test="${param.action.equals('edit')}">
        ${serviceTypeEditorBean.editServiceType(param.name, param.description)}
    </c:when>
    <c:when test="${param.action.equals('removeAttribute')}">
        ${serviceTypeEditorBean.removeAttributeFromServiceType(param.attributeId)}
    </c:when>
    <c:when test="${param.action.equals('addAttribute')}">
        ${serviceTypeEditorBean.addAttributeToServiceType(param.attributeId)}
    </c:when>
    <c:when test="${param.action.equals('save')}">
        ${serviceTypeEditorBean.saveServiceType()}
    </c:when>
</c:choose>

<html>
<head>
    <title>Edit ${serviceTypeEditorBean.editedServiceType.name} service type</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css">
</head>
<body class="stpage">
<h1 class="stpage">Edit ${serviceTypeEditorBean.editedServiceType.name} service type</h1>
<form class="inputform" action="edit.jsp" method=get>
    <div style="display: inline-block">
        <label for=name class = inputform>Name</label>
        <input type=text id=name name=name required=required class = inputform value="<c:out value="${serviceTypeEditorBean.editedServiceType.name}"/>">
    </div>
    <div style="display: inline-block">
        <label for=description class = inputform>Type</label>
        <input type=text id=description name=description required=required class = inputform value="<c:out value="${serviceTypeEditorBean.editedServiceType.description}"/>">
    </div>
    <input type="hidden" name="typeId" value="${serviceTypeEditorBean.editedServiceType.id}">
    <button name=action type=submit class=inputform value="edit">Edit</button>
</form>
    <div class="chooseTables">
        <h2 class="stpage">Service attributes</h2>
        <table>
            <tr>
                <th>Name</th>
                <th>Type</th>
                <th>Action</th>
            </tr>
            <c:forEach var="serviceAttribute" items="${serviceTypeEditorBean.editedServiceType.attributeSet}">
                <tr>
                    <td>${serviceAttribute.name}</td>
                    <td>${serviceAttribute.attributeType.name}</td>
                    <td><a href="edit.jsp?typeId=${serviceTypeEditorBean.editedServiceType.id}&action=removeAttribute&attributeId=${serviceAttribute.id}" class="tableform">Remove</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="chooseTables">
        <h2 class="stpage">Available attributes</h2>
        <table>
            <tr>
                <th>Name</th>
                <th>Type</th>
                <th>Action</th>
            </tr>
            <c:forEach var="availableAttribute" items="${serviceTypeEditorBean.availableAttributes}">
            <tr>
                <td>${availableAttribute.name}</td>
                <td>${availableAttribute.attributeType.name}</td>
                <td><a href="edit.jsp?typeId=${serviceTypeEditorBean.editedServiceType.id}&action=addAttribute&attributeId=${availableAttribute.id}" class="tableform">Add</a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <br/>
    <a href="edit.jsp?typeId=${serviceTypeEditorBean.editedServiceType.id}&action=refresh" class="stpage">Refresh</a><br/>
    <a href="edit.jsp?typeId=${serviceTypeEditorBean.editedServiceType.id}&action=save" class="stpage">Save</a><br/>
    <a href="all.jsp" class="stpage">Back</a>
</body>
</html>
