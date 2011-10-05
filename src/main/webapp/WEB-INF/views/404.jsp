<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8"
         contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title>Tudu Lists</title>
    <jsp:include page="../fragments/header_head.jsp"/>
</head>
    <jsp:include page="../fragments/header_body.jsp"/>
        <h1><fmt:message key="404.title"/></h1>
        <h2><a href="${context}/tudu/welcome"><fmt:message key="footer.home"/></a></h2>
    <jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
