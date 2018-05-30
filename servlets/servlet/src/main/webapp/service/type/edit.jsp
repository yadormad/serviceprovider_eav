<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 29.05.2018
  Time: 5:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="userBean" class="hibernate.controller.ControllerBean">
    <jsp:setProperty name="userBean" property="*"/>
</jsp:useBean>

<c:choose>
    <c:when test="${not empty param.typeId && param.removeAttribute}">
        <c:set scope="request" var="editableServiceType" value="${userBean.getServiceType(param.typeId)}"/>
        ${editableServiceType.attributeSet.remove(userBean.getServiceAttribute(param.removeAttribute))}
        ${userBean.saveServiceType(editableServiceType)}
    </c:when>
    <c:when test="${not empty param.typeId && param.addAttribute}">
        <c:set scope="request" var="editableServiceType" value="${userBean.getServiceType(param.typeId)}"/>
        ${editableServiceType.attributeSet.add(userBean.getServiceAttribute(param.addAttribute))}
        ${userBean.saveServiceType(editableServiceType)}
    </c:when>
    <c:when test="${not empty param.typeId}">
        <c:set scope="request" var="editableServiceType" value="${userBean.getServiceType(param.typeId)}"/>
    </c:when>
    <c:when test="${not empty param.typeIdEdit}">
        <c:set scope="request" var="editableServiceType" value="${userBean.getServiceType(param.typeIdEdit)}"/>
        ${editableServiceType.setName(param.name)}
        ${editableServiceType.setDescription(param.description)}
        ${userBean.saveServiceType(editableServiceType)}
    </c:when>
</c:choose>

<c:set var="allAttributes" value="${userBean.allAttributes}" scope="request"/>
${allAttributes.removeAll(editableServiceType.attributeSet)}

<html>
<head>
    <title>Edit ${editableServiceType.name}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css/">
</head>
<body class="stpage">
<h1 class="stpage">Edit ${editableServiceType.name}</h1>
<form class="inputform" action="edit.jsp" method=get>
    <div style="display: inline-block">
        <label for=name class = inputform>Name</label>
        <input type=text id=name name=name required=required class = inputform value="<c:out value="${editableServiceType.name}"/>">
    </div>
    <div style="display: inline-block">
        <label for=decription class = inputform>Type</label>
        <input type=text id=decription name=decription required=required class = inputform value="<c:out value="${editableServiceType.decription}"/>">
    </div>
    <button name=typeIdEdit type=submit class=inputform value="<c:out value="${editableServiceType.id}"/>">Edit</button>
</form>

<div style="display: inline-block">
    <h2 class="stpage">Service attributes</h2>
    <table>
        <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Action</th>
        </tr>
        <c:forEach var="serviceAttribute" items="${editableServiceType.attributeSet}">
            <tr>
                <td>${serviceAttribute.name}</td>
                <td>${serviceAttribute.type.name}</td>
                <td><a href="edit.jsp?typeId=${editableServiceType.id}&removeAttribute=${serviceAttribute.id}" class="stpage">Remove</a></td>
            </tr>
        </c:forEach>
</div>
<div style="display: inline-block">
    <h2 class="stpage">Available attributes</h2>
    <table>
        <tr>
            <th>Name</th>
            <th>Type</th>
            <th>Action</th>
        </tr>
        <c:forEach var="serviceAttribute" items="${allAttributes}">
        <tr>
            <td>${serviceAttribute.name}</td>
            <td>${serviceAttribute.type.name}</td>
            <td><a href="edit.jsp?typeId=${editableServiceType.id}&addAttribute=${serviceAttribute.id}" class="stpage">Add</a></td>
        </tr>
        </c:forEach>
    </table>
    <a href="../all_types.jsp" class="stpage">Go to attributes page</a>
</div>
<a href="../all_types.jsp" class="stpage">Back</a>
</body>
</html>
