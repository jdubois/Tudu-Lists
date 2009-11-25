<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>

<title><fmt:message key="error.title"/></title>

<h3><fmt:message key="error.title"/></h3>
<p>
<hr/>
<pre>
<%
    if (exception !=null) {
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
</p>

