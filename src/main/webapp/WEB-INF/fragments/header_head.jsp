<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="tudu.service.impl.ConfigurationServiceImpl" %>
<%
    String staticContent = ConfigurationServiceImpl.staticContent;
    request.setAttribute("staticContent", staticContent);
    request.setAttribute("context", request.getContextPath());
    request.setAttribute("page", request.getPathInfo());
%>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
<meta name="author" content="Julien Dubois"/>
<link rel="shortcut icon" href="${staticContent}/favicon.ico"/>
<style type="text/css" title="currentStyle" media="screen">
    @import "${staticContent}/tudu.css";
    @import "${staticContent}/jquery/jquery-ui/jquery-ui-1.7.2.custom.css";
</style>
