<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="tudu.service.impl.ConfigurationServiceImpl" %>
<%
    String staticContent = ConfigurationServiceImpl.staticContent;
    String googleAnalytics = ConfigurationServiceImpl.googleAnalyticsKey;
    request.setAttribute("staticContent", staticContent);
    request.setAttribute("googleAnalytics", googleAnalytics);
%>
<div id="about"><a href="${context}/">Accueil</a> | <a href="${context}/about/mentions_legales">Mentions l&eacute;gales</a> | <a href="${context}/about/conditions_generales_d_utilisation">Conditions g&eacute;n&eacute;rales d'utilisation</a> | <a href="http://www.responcia.fr/blog">Blog de Responcia</a> | <a href="http://www.responcia.fr">&copy; Responcia SARL</a></div>
<script type="text/javascript">
    var context = "${context}";
</script>
<script type="text/javascript" src="${staticContent}/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${staticContent}/jquery/jquery-ui/jquery-ui.1.8.custom.min.js"></script>
<script type="text/javascript" src="${staticContent}/tudu.js"></script>

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


