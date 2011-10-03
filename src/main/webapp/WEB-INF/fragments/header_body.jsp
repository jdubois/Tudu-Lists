<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>
<div id="container">
<table width="100%"><tr><td style="width: 152px"><a href="https://github.com/jdubois/Tudu-Lists"><img src="${staticContent}/images/tudu_logo.png" alt="Tudu Lists" width="150" height="30"/></a></td><td>
<menu:useMenuDisplayer 	name="TabbedMenu"
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
 </td></tr></table>
 <table id="page">
  <tr>
   <td>
    <div id="title">Tudu Lists v. <%=tudu.Constants.VERSION%></div>
   </td>
   <td>
    <% if (request.getRemoteUser() != null) { %>
     <div id="username"><img src="${staticContent}/images/user_suit.png"/> <%=request.getRemoteUser()%></div>
    <% } %>
   </td>
  </tr>
  <tr>
   <td align="center" colspan="2">
    <div id="content">
