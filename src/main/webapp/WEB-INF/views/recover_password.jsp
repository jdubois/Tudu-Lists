<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div align="center">
 <h3><fmt:message key="recover.password.title"/></h3>
 <html:form action="/recoverPassword" focus="login">
 <c:if test="${success eq 'true'}">
  <span class="success"><fmt:message key="recover.password.success"/></span>
 </c:if>
 <html:errors/>
 <html:hidden property="method" value="cancel"/>
 
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
     <html:text property="login" size="20" maxlength="50"/>
    </td>
   </tr>
  </tbody>
 </table>
  <br/>
  <html:submit onclick="document.forms[0].elements['method'].value='sendMail';">
   <fmt:message key="form.submit"/>
  </html:submit>
  <html:submit><fmt:message key="form.cancel"/></html:submit>
 </html:form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
