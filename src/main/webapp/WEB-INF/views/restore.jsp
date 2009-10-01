<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>Restore Todo List</head>
<body>


<div align="center">
 <h3><fmt:message key="restore.title"/></h3>
 <form:form commandName="restoreTodoListForm" enctype="multipart/form-data">
 <form:errors/>
 <form:hidden path="listId"/>
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
     <input type="file" name="backupFile" />
     <br/><br/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="restore.choice"/>
    </td>
    <td>
     <br/>
     <form:radiobutton path="restoreChoice" value="create"/>
     <fmt:message key="restore.choice.create"/>
     <br/><br/>
     <form:radiobutton path="restoreChoice" value="replace"/>
     <fmt:message key="restore.choice.replace">
      <fmt:param value="${todoList.name}"/>
     </fmt:message>
     <br/><br/>
     <form:radiobutton path="restoreChoice" value="merge"/>
     <fmt:message key="restore.choice.merge">
      <fmt:param value="${todoList.name}"/>
     </fmt:message>
     <br/><br/>
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
