<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>Registration successful</head>
<body>

<div align="center">
 <h3><fmt:message key="register.ok.title"/></h3>
 <p style="text-align:center">
  <fmt:message key="register.ok.text.1">
   <fmt:param value="${user.login}"/>
  </fmt:message>
  <a href="welcome.action"><fmt:message key="register.ok.text.link"/></a>
  <fmt:message key="register.ok.text.2"/>
 </p>
</div>

</body>
</html>
