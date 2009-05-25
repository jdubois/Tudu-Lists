<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>New user registration</head>
<body>

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
 <form:form commandName="user">
 <table class="list" style="width:400px">
  <tr>
   <th colspan="3">
    <fmt:message key="register.subtitle"/>
   </th>
  </tr>
  <tbody>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.login"/> *
    </td>
    <td>
     <form:input path="login" size="20" maxlength="50"/>
    </td>
    <td>
     <form:errors path="login"/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.first.name"/> *
    </td>
    <td>
     <form:input path="firstName" size="15" maxlength="60"/>
    </td>
    <td>
     <form:errors path="firstName"/>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.last.name"/> *
    </td>
    <td>
     <form:input path="lastName" size="15" maxlength="60"/>
    </td>
    <td>
     <form:errors path="lastName"/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.email"/>
    </td>
    <td>
     <form:input path="email" size="30" maxlength="100"/>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.password"/> *
    </td>
    <td>
     <form:password path="password" size="15" maxlength="32"/>
    </td>
    <td>
     <form:errors path="password"/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.verifypassword"/> *
    </td>
    <td>
     <form:password path="verifyPassword" size="15" maxlength="32"/>
    </td>
    <td>
     <form:errors path="verifyPassword"/>
    </td>
   </tr>
  </tbody>
 </table>
  <br/>
  <br/>
  <input type="submit" value="<fmt:message key="form.submit"/>"/>
  <input type="reset" value="<fmt:message key="form.cancel"/>"/>
 </form:form>
</div>

</body>
</html>
