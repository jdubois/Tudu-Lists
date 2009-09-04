<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>My info</head>
<body>

<div align="center">
 <h3><fmt:message key="recover.password.title"/></h3>
 <form:form commandName="user">
 <c:if test="${success eq 'true'}">
  <span class="success"><fmt:message key="recover.password.success"/></span>
 </c:if>
 <c:if test="${message ne null}">
  <span class="error"><fmt:message key="${message}"/></span>
 </c:if>
 
 <table class="list" style="width:250px">
  <tr>
   <th colspan="2">
    <fmt:message key="recover.password.subtitle"/>
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
   </tr>
  </tbody>
 </table>
  <br/>
  <input type="submit" value="<fmt:message key="form.submit"/>" />
 </form:form>
</div>

</body>
</html>
