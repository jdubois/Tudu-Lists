<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<html>
<head></head>
<body>

<h2><fmt:message key="login.welcome"/></h2>

<table style="width:700px;">
 <tr>
  <td  style="width:400px; vertical-align: top;">
   <p><fmt:message key="login.description.1"/></p>
   <p><fmt:message key="login.description.2"/></p>
   <div>
    <ul>
     <li><fmt:message key="login.advantages.1"/></li>
     <li><fmt:message key="login.advantages.2"/></li>
     <li><fmt:message key="login.advantages.3"/></li>
     <li>
      <fmt:message key="login.advantages.4.1"/> 
      <a href="register.action"><fmt:message key="login.advantages.4.link"/></a>
      <fmt:message key="login.advantages.4.2"/>
     </li>
     <li><fmt:message key="login.advantages.5"/></li>
    </ul>
   </div>
  </td>
  <td style="width:300px; text-align:center; vertical-align: top;">
   <h3><fmt:message key="login.title"/></h3>
   <c:if test="${not empty param.login_error}">
    <div class="error"> 
     <fmt:message key="login.error.title"/>
    </div>
    <br/>
   </c:if>
   <form action="j_spring_security_check" method="post">
    <table border="0" cellspacing="2" cellpadding="3">
     <tr>
      <th style="text-align: left"><fmt:message key="login.login"/></th>
      <td style="text-align: left">
       <input 	type="text" 
           		name="j_username" 
           		size="20" 
           		maxlength="50" 
           		<c:if test="${not empty param.login_error}">
            			value='<%= session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %>'
           		</c:if>
       />
      </td>
     </tr>
     <tr>
      <th style="text-align: left"><fmt:message key="login.password"/></th>
      <td style="text-align: left"><input type="password" name="j_password" size="20" maxlength="50"/></td>
     </tr>
     <tr>
      <td colspan="2">
       <p>
        <fmt:message key="login.auto.login"/>
        <input type="checkbox" name="_spring_security_remember_me">
       </p>
      </td>
     </tr>
     <tr>
      <th colspan="2">
       <input type="submit" value="<fmt:message key="login.submit"/>"/>
       <input type="reset" value="<fmt:message key="login.reset"/>"/>
      </th>
     </tr>
    </table>
   </form>
   <p>
    <fmt:message key="login.register.1"/>
    <a href="register.action"><fmt:message key="login.register.link"/></a><fmt:message key="login.register.2"/>
   </p>
   <p>
    <fmt:message key="login.forgotten.password.1"/>
    <a href="recoverPassword.action"><fmt:message key="login.forgotten.password.link"/></a> <fmt:message key="login.forgotten.password.2"/>
   </p>
  </td>
 </tr>
</table>

</body>
</html>