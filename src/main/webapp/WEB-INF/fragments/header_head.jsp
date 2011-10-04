<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="tudu.service.impl.ConfigurationServiceImpl" %>
<%
    String staticContent = ConfigurationServiceImpl.staticContent;
    String googleAnalytics = ConfigurationServiceImpl.googleAnalyticsKey;
    request.setAttribute("staticContent", staticContent);
    request.setAttribute("googleAnalytics", googleAnalytics);
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
<script type="text/javascript" src="${staticContent}/scripts/scriptaculous/prototype.js"></script>
<script type="text/javascript" src="${staticContent}/scripts/scriptaculous/scriptaculous.js"></script>
<script type='text/javascript' src='${staticContent}/scripts/tudu.js'></script>
<style type="text/css" title="currentStyle" media="screen">
    @import "${staticContent}/css/tudu.css";
</style>
<script type="text/javascript">
    var context = "${context}";
</script>
<c:if test="${googleAnalytics ne ''}">
    <script type="text/javascript">
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', '${googleAnalytics}']);
        _gaq.push(['_trackPageview']);
        function tracker(url) {
            _gaq.push(['_trackEvent', url, 'clicked'])
        }
    </script>
</c:if>
<c:if test="${googleAnalytics eq ''}">
    <script type="text/javascript">
        function tracker(url) {
        }
    </script>
</c:if>
