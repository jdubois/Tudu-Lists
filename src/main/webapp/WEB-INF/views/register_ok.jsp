<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8"
         contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>Tudu Lists</title>
    <jsp:include page="../fragments/header_head.jsp"/>
</head>
<body id="main">
<jsp:include page="../fragments/header_body.jsp"/>

<h1><fmt:message key="register.ok.title"/></h1>

<p style="text-align:center">
    <fmt:message key="register.ok.text.1">
        <fmt:param value="${user.login}"/>
    </fmt:message>
    <a href="welcome.action"><fmt:message key="register.ok.text.link"/></a>
    <fmt:message key="register.ok.text.2"/>
</p>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
