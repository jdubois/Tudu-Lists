<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
    request.setAttribute("ctx", request.getContextPath());
    String staticCtx = tudu.service.impl.ConfigurationManagerImpl.staticFilesPath;
    if (staticCtx.equals("")) {
        request.setAttribute("staticCtx", request.getContextPath());
    } else {
        request.setAttribute("staticCtx", staticCtx);
    }
%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title><decorator:title default="Tudu Lists" /></title>
    <meta http-equiv="content-type" content="text/javascript; charset=utf-8"/>
    <meta http-equiv="content-script-type" content="text/javascript"/>
    <meta name="Keywords"
          content="Tudu Lists, tudu, free, Open Source, todo, Todo List, Todo Lists, Sourceforge, Spring, Spring Framework, Java, J2EE, AJAX, DWR"/>
    <meta name="Description"
          content="Tudu Lists is a free (non-commercial) on-line application for managing todo lists."/>
    <link rel="shortcut icon" href="${staticCtx}/images/favicon.ico"/>
    <link rel="icon" type="image/gif" href="${staticCtx}/images/animated_favicon.gif"/>
    <link rel="stylesheet" type="text/css" href="${staticCtx}/css/global.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="${staticCtx}/css/tabs.css"/>
    <link rel="stylesheet" type="text/css" media="all" href="${staticCtx}/css/calendar-blue.css" title="calendar-blue"/>
    <% if (!tudu.service.impl.ConfigurationManagerImpl.googleAnalyticsKey.equals("")) { %>
    <script type="text/javascript">
        var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
        document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
        var pageTracker = _gat._getTracker("<%=tudu.service.impl.ConfigurationManagerImpl.googleAnalyticsKey%>");
        pageTracker._initData();
        pageTracker._trackPageview();
        function tracker(ajaxCall) {
            pageTracker._trackPageview(ajaxCall);
        }
    </script>
    <% } else { %>
    <script type="text/javascript">function tracker(ajaxCall) {
    }</script>
    <% } %>
    <script type="text/javascript" src="${staticCtx}/scripts/tabs.js"></script>
    <script type="text/javascript" src="${staticCtx}/scripts/scriptaculous/prototype.js"></script>
    <script type="text/javascript" src="${staticCtx}/scripts/scriptaculous/scriptaculous.js"></script>
<%
    if (request.isUserInRole("ROLE_USER")) {
%>
    <script type="text/javascript" src="${ctx}/secure/dwr/engine.js"></script>
    <script type="text/javascript" src="${ctx}/secure/dwr/util.js"></script>

</head>
<body onload="initMenu();DWRUtil.useLoadingMessage();">
<table width="100%">
    <tr>
        <td width="152"><a href="http://www.julien-dubois.com/tudu-lists"><img src="${staticCtx}/images/tudu_logo.png"
                                                                   alt="Tudu Lists" width="150" height="30" border="0"/></a>
        </td>
        <td>
            <menu:useMenuDisplayer name="TabbedMenu" bundle="messages">

                <menu:displayMenu name="Info"/>
                <menu:displayMenu name="Todos"/>
                    <%
   if (request.isUserInRole("ROLE_ADMIN")) {
  %>
                <menu:displayMenu name="Administration"/>
                    <%
   }
  %>
                <menu:displayMenu name="Logout"/>
            </menu:useMenuDisplayer>
<%
 } else {
%>
            </head>
            <body onload="initMenu();">
            <table width="100%">
                <tr>
                    <td width="152"><a href="http://www.julien-dubois.com/tudu-lists"><img src="${staticCtx}/images/tudu_logo.png"
                                                                               alt="Tudu Lists" width="150" height="30"
                                                                               border="0"/></a></td>
                    <td>
                        <menu:useMenuDisplayer name="TabbedMenu"
                                               bundle="messages">

                            <menu:displayMenu name="Welcome"/>
                            <menu:displayMenu name="Register"/>
                        </menu:useMenuDisplayer>
<%
 }
%>
                    </td>
                </tr>
            </table>
            <table align="center" id="page">
                <tr>
                    <td>
                        <div id="title">Tudu Lists v. <%=tudu.Constants.VERSION%> - <%=request.getSession().getServletContext().getContextPath()%>
                        </div>
                    </td>
                    <td>
                        <% if (request.getRemoteUser() != null) { %>
                        <div id="username"><img src="${staticCtx}/images/user_suit.png"/> <%=request.getRemoteUser()%>
                        </div>
                        <% } %>
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <div id="content">

                            <decorator:body />

                        </div>
                    </td>
                </tr>
                <tr>
                    <td id="sourceforge">
                        <a href="http://www.julien-dubois.com/tudu-lists">
                            <img src="http://sourceforge.net/sflogo.php?group_id=131842&amp;type=1" width="88"
                                 height="31" border="0" alt="SourceForge.net"/>
                        </a>
                    </td>
                    <td id="bug">
                        <img src="${staticCtx}/images/bug.png"/> <fmt:message key="footer.bug.text"/> <a
                            href="http://sourceforge.net/tracker/?group_id=131842"><fmt:message
                            key="footer.bug.link"/></a>
                    </td>
                </tr>
            </table>
   </body>
</html>
