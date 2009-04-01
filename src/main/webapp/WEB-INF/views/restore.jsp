<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div align="center">
 <h3><fmt:message key="restore.title"/></h3>
 <html:form action="/secure/restoreTodoList" method="POST" enctype="multipart/form-data">
 <html:errors/>
 <html:hidden property="listId"/>
 <html:hidden property="method" value="cancel"/>
 <table class="list" style="width:600px">
  <tr>
   <th colspan="2">
    <fmt:message key="restore.subtitle"/>
   </th>
  </tr>
  <tbody>
   <tr class="odd">
    <td>
     <fmt:message key="restore.file"/>
    </td>
    <td>
     <br/>
     <html:file property="backupFile" />
     <br/><br/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="restore.choice"/>
    </td>
    <td>
     <br/>
     <html:radio property="restoreChoice" value="create"/>
     <fmt:message key="restore.choice.create"/>
     <br/><br/>
     <html:radio property="restoreChoice" value="replace"/>
     <fmt:message key="restore.choice.replace">
      <fmt:param value="${todoList.name}"/>
     </fmt:message>
     <br/><br/>
     <html:radio property="restoreChoice" value="merge"/>
     <fmt:message key="restore.choice.merge">
      <fmt:param value="${todoList.name}"/>
     </fmt:message>
     <br/><br/>
    </td>
   </tr>
  </tbody>
 </table>
  <br/>
  <html:submit onclick="document.forms[0].elements['method'].value='restore';">
   <fmt:message key="form.submit"/>
  </html:submit>
  <html:submit><fmt:message key="form.cancel"/></html:submit>
 </html:form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
