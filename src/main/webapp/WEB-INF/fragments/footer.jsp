<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="tudu.service.impl.ConfigurationServiceImpl" %>
<%
    String staticContent = ConfigurationServiceImpl.staticContent;
    String googleAnalytics = ConfigurationServiceImpl.googleAnalyticsKey;
    request.setAttribute("staticContent", staticContent);
    request.setAttribute("googleAnalytics", googleAnalytics);
    request.setAttribute("currentPage", request.getPathInfo());
%>
<div id="about"><a href="${context}/tudu/welcome"><fmt:message key="footer.home"/></a> | <a href="http://github.com/jdubois/Tudu-Lists/issues"><fmt:message key="footer.bug"/></a> | <a href="http://github.com/jdubois/Tudu-Lists"><fmt:message key="footer.dev"/></a> | <a href="${context}/tudu/license"><fmt:message key="footer.license"/></a> | <a href="http://www.julien-dubois.com"><fmt:message key="footer.copyright"/></a> |
Language: <a href="${currentPage}?lang=en">English</a> | <a href="${currentPage}?lang=fr">Fran&ccedil;ais</a></div>
<script type="text/javascript">
    var context = "${context}";
</script>
<script type="text/javascript" src="${staticContent}/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${staticContent}/jquery/jquery-ui/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${staticContent}/tudu.js"></script>
<c:if test="${errors ne null}">

</c:if>
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


