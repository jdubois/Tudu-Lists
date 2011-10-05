<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" isErrorPage="true" pageEncoding="UTF-8"
         contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
    <title><fmt:message key="error.title"/></title>
    <jsp:include page="../fragments/header_head.jsp"/>
</head>
<body id="main">
<jsp:include page="../fragments/header_body.jsp"/>

<h3><fmt:message key="error.title"/></h3>

<p>
<hr/>
<pre>
<%
    if (exception != null) {
        if (exception instanceof org.springframework.orm.ObjectRetrievalFailureException) {
%>
<fmt:message key="error.object.retrieval"/>
<%
        } else {
            exception.printStackTrace(new java.io.PrintWriter(out));
        }
    }
%>
</pre>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>

