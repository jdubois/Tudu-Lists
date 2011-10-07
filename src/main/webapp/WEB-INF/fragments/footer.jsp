<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="tudu.service.impl.ConfigurationServiceImpl" %>
<%
    String staticContent = ConfigurationServiceImpl.staticContent;
    request.setAttribute("currentPage", request.getPathInfo());
%>
    </div>
   </td>
  </tr>
  <tr>
   <td id="about">
    Tudu Lists v. <%=tudu.Constants.VERSION%> | <a href="http://github.com/jdubois/Tudu-Lists"><fmt:message key="footer.dev"/></a> | <a href="${context}/tudu/license"><fmt:message key="footer.license"/></a> | <a href="http://www.julien-dubois.com"><fmt:message key="footer.copyright"/></a>
   </td>
   <td id="bug">
    <img src="${staticContent}/images/bug.png" alt="bug"/> <fmt:message key="footer.bug.text"/> <a href="http://github.com/jdubois/Tudu-Lists/issues"><fmt:message key="footer.bug.link"/></a>
   </td>
  </tr>
 </table>
 </div>
<script type="text/javascript">
    initMenu();
</script>
<c:if test="${googleAnalytics ne ''}">
  <script type="text/javascript">  (function() {
    var ga = document.createElement('script');     ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:'   == document.location.protocol ? 'https://ssl'   : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
    })();
   </script>
</c:if>




