<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 31.05.2018
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="clientEditorBean" class="hibernate.controller.ClientEditorBean" scope="session">
    <jsp:setProperty name="clientEditorBean" property="*"/>
</jsp:useBean>
<c:if test="${param.action.equals('refresh')}">
    ${clientEditorBean.refresh()}
</c:if>
${clientEditorBean.init(param.clientId)}
<c:choose>
    <c:when test="${param.actiom.equals('edit')}">
        <c:set var="editMessage" value="${clientEditorBean.editClient(param.info, param.pass1, param.pass2)}"/>
    </c:when>

    <c:when test="${param.actiom.equals('save')}">
        ${clientEditorBean.saveClient()}
    </c:when>

</c:choose>
<html>
<head>
    <title>Edit ${clientEditorBean.editedClient.login} client</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css">
</head>
<body class="stpage">
<h1 class="stpage">Edit ${clientEditorBean.editedClient.login} client</h1>
<form class="inputform" action="edit.jsp" method=get>
    <div style="display: inline-block">
        <label for=info class = inputform>Info</label>
        <input type=text id=info name=info required=required class = inputform value="${clientEditorBean.editedClient.info}"/>
    </div>
    <div style="display: inline-block">
        <label for=pass1 class="inputform">Password</label>
        <input type=password id=pass1 name=pass1 required=required class = inputform value="${clientEditorBean.editedClient.pass}"/>
        <label for=pass2 class="inputform">Confirm password</label>
        <input type=password id=pass2 name=pass2 required=required class = inputform value="${clientEditorBean.editedClient.pass}"/>
    </div><br/>
    <input type="hidden" name="clientId" value="${clientEditorBean.editedClient.id}">
    <button name=action type=submit class=inputform value="edit">Edit</button>
</form>
<p>${editMessage}</p>
<a href="edit.jsp?clientId=${clientEditorBean.editedClient.id}&action=refresh" class="stpage">Refresh</a><br/>
<a href="edit.jsp?clientId=${clientEditorBean.editedClient.id}&action=save" class="stpage">Save</a><br/>
<a href="all.jsp" class="stpage">Back</a><br/>
</body>
</html>