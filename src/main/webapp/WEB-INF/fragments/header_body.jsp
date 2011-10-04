<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
<div id="container">
    <table id="headerTable">
        <tr>
            <td style="width: 152px"><a href="${context}/tudu/welcome" style="background: transparent;"><img
                    src="${staticContent}/images/tudu_logo.png" alt="Tudu Lists" width="150" height="30"/></a></td>
            <% if (request.getRemoteUser() != null) { %>
            <td>
                <menu:useMenuDisplayer name="TabbedMenu"
                                       bundle="org.apache.struts.action.MESSAGE">

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
            </td>
            <td>
                <div id="username"><img src="${staticContent}/images/user_suit.png"/> <%=request.getRemoteUser()%></div>
            </td>
            <% } %>
        </tr>
    </table>
 <table id="page">
  <tr>
   <td align="center" colspan="2">
    <div id="content">
