<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="../error_page.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 28.03.2018
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="controllerBean" class="hibernate.controller.ControllerBean">
    <jsp:setProperty name="controllerBean" property="*"/>
</jsp:useBean>

<c:if test="${param.action.equals('add')}">
    <c:set var="addMessage" value="${controllerBean.addClient(param.login, param.info, param.pass1, param.pass2)}" scope="request"/>
</c:if>
<html>
<head>
    <title>Add client</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/mystyle1.css"/>
</head>
<body class="stpage">
<h1 class="stpage">Add client</h1>
<form class="inputform" action="new.jsp" method=post>
    <div style="display: inline-block">
        <label for=login class = inputform>Login</label>
        <input type=text id=login name=login required=required class = inputform value="">
    </div>
    <div style="display: inline-block">
        <label for=info class = inputform>Info</label>
        <input type=text id=info name=info required=required class = inputform value="">
    </div>
    <div style="margin-left: auto">
        <label for=pass1 class = inputform>Password</label>
        <input type=password id=pass1 name=pass1 required=required class = inputform value="">
        <label for=pass2 class = inputform>Confirm password</label>
        <input type=password id=pass2 name=pass2 required=required class = inputform value="">
    </div>
    <button name=action type=submit class=inputform value="add">Add</button>
</form>

<p><c:out value="${addMessage}"/></p>

<a href="all.jsp" class="stpage">Back</a>
</body>
</html>
