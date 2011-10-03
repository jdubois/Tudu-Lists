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
<script type="text/javascript">
    var context = "${context}";
</script>
<script type="text/javascript" src="${staticContent}/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${staticContent}/jquery/jquery-ui/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${staticContent}/tudu.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        initMenu();
    });
</script>
<c:if test="${googleAnalytics ne ''}">
    <script type="text/javascript">
        var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
        document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
    </script>
    <script type="text/javascript">
        try {
            var pageTracker = _gat._getTracker("${googleAnalytics}");
            pageTracker._setDomainName("none");
            pageTracker._setAllowLinker(true);
            pageTracker._trackPageview();
        } catch(err) {
        }</script>
</c:if>
