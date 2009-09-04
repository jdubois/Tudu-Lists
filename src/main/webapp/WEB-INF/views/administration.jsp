<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>Administration</head>
<body>

<div align="center">
 <h3><fmt:message key="administration.title"/></h3>
 <c:if test="${success eq 'true'}">
  <span class="success"><fmt:message key="form.success"/></span>
 </c:if>
 <table>
  <tr>
   <td style="vertical-align: top; border: 1px solid #C0C0C0">
    <div id="menuDiv" style="min-height: 400px">
     <table id="menuTable" style="min-width:160px">
      <thead>
       <tr>
        <td style="font-weight: bold;">Actions</td>
       </tr>
       <tr>
        <td>
         [ <a href="administration.action">
          <fmt:message key="administration.menu.configuration"/>
         </a> ]
        </td>
       </tr>
       <tr>
        <td>
         [ <a href="administration.action?page=users">
          <fmt:message key="administration.menu.users"/>
         </a> ]
        </td>
       </tr>
      </thead>
     </table>
    </div>
   </td>
   <td style="width:10px"></td>
   <td style="width: 100%; vertical-align: top; text-align: center; border: 1px solid #C0C0C0">
    <div style="min-height: 400px" align="center">

    <c:if test="${page eq 'configuration' or page == null}">
     <form:form commandName="administrationModel">
     <form:errors/>
     <input name="action" type="hidden" value="configuration"/>
     <table class="list" style="width:450px">
      <tr>
       <th colspan="2">
        <fmt:message key="administration.configuration"/>
       </th>
      </tr>
      <tbody>
       <tr class="odd">
        <td>
         <fmt:message key="administration.configuration.static.path"/>
        </td>
        <td>
         <form:input path="propertyStaticPath" size="30" maxlength="200"/>
        </td>
       </tr>
	   <tr class="even">
        <td>
         <fmt:message key="administration.configuration.google.analytics.key"/>
        </td>
        <td>
         <form:input path="googleAnalyticsKey" size="30" maxlength="200"/>
        </td>
       </tr>
       <tr class="odd">
        <td>
         <fmt:message key="administration.configuration.email.host"/>
        </td>
        <td>
         <form:input path="smtpHost" size="30" maxlength="200"/>
        </td>
       </tr>
       <tr class="even">
        <td>
         <fmt:message key="administration.configuration.email.port"/>
        </td>
        <td>
	     <form:input path="smtpPort" size="30" maxlength="200"/>
        </td>
       </tr>
       <tr class="odd">
        <td>
         <fmt:message key="administration.configuration.email.user"/>
        </td>
        <td>
         <form:input path="smtpUser" size="30" maxlength="200"/>
        </td>
       </tr>
       <tr class="even">
        <td>
         <fmt:message key="administration.configuration.email.password"/>
        </td>
        <td>
         <form:input path="smtpPassword" size="30" maxlength="200"/>
        </td>
       </tr>
       <tr class="odd">
        <td>
         <fmt:message key="administration.configuration.email.from"/>
        </td>
        <td>
         <form:input path="smtpFrom" size="30" maxlength="200"/>
        </td>
       </tr>
      </tbody>
     </table>
      <br/>
      <input type="submit" value="<fmt:message key="form.submit"/>"/>
     </form:form>
    </c:if>

    <c:if test="${page eq 'users'}">
      <form:form commandName="administrationModel">
      <p>
       <input name="action" type="hidden" value="userSearch"/>
       <input name="login" type="hidden" value=""/>
       <fmt:message key="administration.number.of.users"/> : <b>${numberOfUsers}</b>
       <br/><br/>
       <fmt:message key="administration.user.login"/> :
       <form:input path="searchLogin" size="30" maxlength="200"/>
       <input type="submit" value="<fmt:message key="form.submit"/>"/>
      </p>
      <c:if test="${not empty users}">
       <p>
        <c:set var="row" value="0"/>
        <table class="list">
         <tr>
          <th>
           <fmt:message key="user.info.login"/>
          </th>
          <th>
           <fmt:message key="user.info.first.name"/>
          </th>
          <th>
           <fmt:message key="user.info.last.name"/>
          </th>
          <th>
           <fmt:message key="administration.user.enabled"/>
          </th>
          <th>
           <fmt:message key="todos.actions"/>
          </th>
         </tr>
        <c:forEach var="user" items="${users}">
         <c:set var="row" value="${row + 1}"/>
         <c:set var="trStyle" value="${row % 2 eq 0 ? 'even' : 'odd'}"/>
         <tr class="${trStyle}">
          <td>
           ${user.login}
          </td>
          <td>
           ${user.firstName}
          </td>
          <td>
           ${user.lastName}
          </td>
          <td style="text-align: center">
           ${user.enabled}
          </td>
          <td style="text-align: center">
           <c:if test="${user.enabled}">
            <input type="submit" value="Disable" onclick="document.forms[0].elements['action'].value='disableUser';document.forms[0].elements['login'].value='${user.login}';"/>
           </c:if>
           <c:if test="${not user.enabled}">
            <input type="submit" value="Enable" onclick="document.forms[0].elements['action'].value='enableUser';document.forms[0].elements['login'].value='${user.login}';"/>
           </c:if>
          </td>
         </tr>
        </c:forEach>
        </table>
       </p>
      </c:if>
     </form:form>
    </c:if>

   </div>
   </td>
  </tr>
 </table>
</div>

</body>
</html>
