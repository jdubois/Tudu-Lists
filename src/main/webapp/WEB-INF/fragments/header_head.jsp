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
<script type='text/javascript' src='${staticContent}/scripts/tabs.js'></script>
<script type='text/javascript' src='${staticContent}/scripts/tudu.js'></script>
<style type="text/css" title="currentStyle" media="screen">
    @import "${staticContent}/tudu.css";
</style>
<script type="text/javascript">
    var context = "${context}";
</script>
