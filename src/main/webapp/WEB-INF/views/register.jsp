<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div align="center">
 <h3><fmt:message key="register.title"/></h3>
 <div style="width:350px">
  <ul>
   <li>
    <fmt:message key="register.info.1"/>
   </li>
   <li>
    <fmt:message key="register.info.2"/>
   </li>
  </ul>
 </div>
 <html:form action="/register" focus="login">
 <html:errors/>
 <html:hidden property="method" value="cancel"/>
 <table class="list" style="width:400px">
  <tr>
   <th colspan="2">
    <fmt:message key="register.subtitle"/>
   </th>
  </tr>
  <tbody>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.login"/> *
    </td>
    <td>
     <html:text property="login" size="20" maxlength="50"/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.first.name"/> *
    </td>
    <td>
     <html:text property="firstName" size="15" maxlength="60"/>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.last.name"/> *
    </td>
    <td>
     <html:text property="lastName" size="15" maxlength="60"/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.email"/>
    </td>
    <td>
     <html:text property="email" size="30" maxlength="100"/>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.password"/> *
    </td>
    <td>
     <html:password property="password" size="15" maxlength="32"/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.verifypassword"/> *
    </td>
    <td>
     <html:password property="verifyPassword" size="15" maxlength="32"/>
    </td>
   </tr>
  </tbody>
 </table>
  <br/>
  <br/>
  <html:submit onclick="document.forms[0].elements['method'].value='register';">
   <fmt:message key="form.submit"/>
  </html:submit>
  <html:submit><fmt:message key="form.cancel"/></html:submit>
 </html:form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
