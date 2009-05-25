<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>Logout</head>
<body>

<h3><fmt:message key="logout.title"/></h3>

<div align="center">
 <fmt:message key="logout.description"/>
 <br/>
 <br/>
 <a href="welcome.action"><fmt:message key="logout.reconnect"/></a>
</div>

</body>
</html>
