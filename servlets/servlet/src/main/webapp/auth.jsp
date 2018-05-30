<%--
  Created by IntelliJ IDEA.
  User: Oleg
  Date: 23.03.2018
  Time: 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="authBean" class="admin.AuthentificationBean">
    <jsp:setProperty name="authBean" property="*"/>
</jsp:useBean>
<c:set var="login" value="${authBean.login(param.login, param.pass)}" scope="session"/>
<c:choose>
    <c:when test="${empty sessionScope.login}">
        <c:set var="authorized" value="${false}" scope="session"/>
    </c:when>
    <c:otherwise>
        <c:set var="authorized" value="${true}" scope="session"/>
    </c:otherwise>
</c:choose>
<!--%
    InitialContext initialContext = new InitialContext();
    AuthentificationBean authentificationBean = (AuthentificationBean) initialContext.lookup("java:app/ejb_ejb/AuthentificationEJB!admin.AuthentificationBean");

    String loginString = request.getParameter("login");
    String passString = request.getParameter("pass");
    UserSessionBean userSessionBean = authentificationBean.login(loginString, passString);
    if(userSessionBean != null) {
        request.getSession().setAttribute("authorized", true);
    }
%--!>