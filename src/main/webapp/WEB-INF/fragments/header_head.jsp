<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="tudu.service.impl.ConfigurationServiceImpl" %>
<%
    String staticContent = ConfigurationServiceImpl.staticContent;
    request.setAttribute("staticContent", staticContent);
    request.setAttribute("context", request.getContextPath());
%>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
<meta name="author" content="Julien Dubois"/>
<meta name="Keywords"
      content="Tudu Lists, tudu, free, Open Source, todo, Todo List, Todo Lists, Spring, Spring Framework, Java, J2EE, AJAX, JQuery"/>
<meta name="Description"
      content="Tudu Lists is a free (non-commercial) on-line application for managing todo lists."/>
<link rel="shortcut icon" href="${staticContent}/favicon.ico"/>
<style type="text/css" title="currentStyle" media="screen">
    @import "${staticContent}/tudu.css";
    @import "${staticContent}/jquery/jquery-ui/jquery-ui-1.7.2.custom.css";
</style>
