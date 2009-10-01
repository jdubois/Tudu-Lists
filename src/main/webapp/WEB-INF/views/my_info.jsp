<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>My info</head>
<body>

<div align="center">
 <h3><fmt:message key="user.info.title"/></h3>
 <form:form commandName="user" >
 <table class="list" style="width:400px">
  <tr>
   <th colspan="3">
    <fmt:message key="user.info.subtitle"/>
   </th>
  </tr>
  <tbody>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.first.name"/>
    </td>
    <td>
     <form:input path="firstName" size="15" maxlength="60"/>
    </td>
    <td>
     <form:errors path="firstName"/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.last.name"/>
    </td>
    <td>
     <form:input path="lastName" size="15" maxlength="60"/>
    </td>
    <td>
     <form:errors path="lastName"/>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.email"/>
    </td>
    <td>
     <form:input path="email" size="30" maxlength="100"/>
    </td>
    <td></td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="user.info.date.format"/>
    </td>
    <td>
     <form:select path="dateFormat">
      <form:option value="MM/dd/yyyy">mm/dd/yyyy</form:option>
      <form:option value="MM/dd/yy">mm/dd/yy</form:option>
      <form:option value="dd/MM/yyyy">dd/mm/yyyy</form:option>
      <form:option value="dd/MM/yy">dd/mm/yy</form:option>
     </form:select>
    </td>
    <td></td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="user.info.password"/>
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
     <fmt:message key="user.info.verifypassword"/>
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
  <input type="submit" value="<fmt:message key="form.submit"/>" />
 </form:form>
</div>

</body>
</html>