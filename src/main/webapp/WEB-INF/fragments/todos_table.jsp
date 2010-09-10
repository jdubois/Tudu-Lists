<%@ page import="tudu.service.impl.ConfigurationServiceImpl" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
 request.setAttribute("ctx", request.getContextPath()); 
 request.setAttribute("now", java.util.Calendar.getInstance().getTime());
 String staticCtx = ConfigurationServiceImpl.staticContent;
 if (staticCtx.equals("")) {
     request.setAttribute("staticCtx", request.getContextPath());
 } else {
     request.setAttribute("staticCtx", staticCtx);
 }
 %>
<c:set var="row" value="0"/>
 
<c:if test="${not empty todoList}">
 <div style="font: 18px/1.3em Arial,Helvetica,sans-serif;">${todoList.name}</div>
 <br/>
</c:if>
 <table align="center">
  <tr>
   <td style="padding: 0px;width: ${completion * 10}px;background-color: #8080FF;"></td>
   <td style="padding: 0px;width: ${1000 - completion * 10}px;background-color: #FF8080;"></td>
   <td>&nbsp;(${completion}%)</td>
  </tr>
 </table>
 <br/>
 <div style="text-align:left">
 <form name="quickAddNewTodoForm" onSubmit="return false;">
  <input type="text" name="description" size="30" maxlength="255" onkeydown="if(event.keyCode==13) {quickAddTodo();}"/>
  <a href="javascript:quickAddTodo()"><fmt:message key="todos.actions.add.quick"/></a> | 
  <a href="javascript:showAddTodo()"><fmt:message key="todos.actions.add"/></a> | 
  <a href="javascript:deleteAllCompletedTodos()"><fmt:message key="todos.actions.delete.all"/></a> | 
  <c:if test="${hideOlderTodos eq 'true'}">
   <a href="javascript:showOlderTodos()"><fmt:message key="todos.actions.show.older"/></a>
  </c:if>
  <c:if test="${hideOlderTodos eq 'false'}">
   <a href="javascript:hideOlderTodos()"><fmt:message key="todos.actions.hide.older"/></a>
  </c:if>
 </form>
 </div>
<c:if test="${empty todos}">
 <div class="message"><fmt:message key="todos.nothing.to.display"/></div>
</c:if>
<c:if test="${not empty todos}">
 <c:set var="showAssignedUser" value="false"/>
 <c:if test="${fn:length(todoList.users) > 1}">
  <c:set var="showAssignedUser" value="true"/>
 </c:if>
 <div style="max-height: 700px; overflow:auto">
 <table class="list">
  <tr>
   <th class="${descriptionClass}" onclick="sortTable('description')">
    <fmt:message key="todos.description"/> <span class="completed">(<fmt:message key="common.click.to.edit"/>)</span>
   </th>
   <th class="${priorityClass}" style="width: 80px" onclick="sortTable('priority')">
    <fmt:message key="todos.priority"/>
   </th>
   <th class="${dueDateClass}" style="width: 80px" onclick="sortTable('due_date')">
    <fmt:message key="todos.due.date"/>
   </th>
   <c:if test="${showAssignedUser eq 'true'}">
    <th class="${assignedUserClass}" style="width: 98px" onclick="sortTable('assigned_user')">
     <fmt:message key="todos.assigned.user"/>
    </th>
   </c:if>
   <th style="width: 90px"><fmt:message key="todos.completed"/></th>
   <th style="width: 60px"><fmt:message key="todos.actions"/></th>
  </tr>
  <c:set var="row" value="0"/>
  <c:forEach var="todo" items="${todos}">
   <c:set var="row" value="${row + 1}"/>
   <c:set var="trStyle" value="${row % 2 eq 0 ? 'even' : 'odd'}"/>
   <c:set var="method" value="completeTodo" scope="page"/>
   <c:set var="checked" value="" scope="page"/>
   <c:set var="style" value="" scope="page"/>
   <c:if test="${todo.completed}">
    <c:set var="method" value="reopenTodo" scope="page"/>
    <c:set var="checked" value="checked" scope="page"/>
    <c:set var="tdStyle" value="completed" scope="page"/>
   </c:if>
   <tr class="${trStyle}" id="${todo.todoId}">
    <td class="${tdStyle}" onclick="showQuickEditTodo('${todo.todoId}')">
     <div id="show-${todo.todoId}" >
	  <c:if test="${not (todo.completed) and (todo.dueDate.time le now.time)}">
       <img src="${staticCtx}/images/date_warning.png" width="16" height="16" alt="!"/>
      </c:if>
	  ${todo.description}
      <c:if test="${todo.hasNotes}">&nbsp;<img src="${staticCtx}/images/note.png" alt=""/></c:if>
     </div>
     <div id="edit-${todo.todoId}" style="display: none"><input id="edit-in-${todo.todoId}" type="text" size="50" value="${todo.description}" onblur="quickEditTodo('${todo.todoId}',this.value);"/></div>
    </td>
    <td class="${tdStyle}" style="text-align: center">
     ${todo.priority}
    </td>
    <td class="${tdStyle}" style="text-align: center; font-size: 80%">
     <fmt:formatDate value="${todo.dueDate}" type="date" pattern="${dateFormat}"/>
    </td>
    <c:if test="${showAssignedUser eq 'true'}">
     <td class="${tdStyle}" style="text-align: center">
      ${todo.assignedUser.login}
     </td>
    </c:if>
    <td class="${tdStyle}" style="text-align: center">
     <input type="checkbox" 
            onClick="${method}('${todo.todoId}')" ${checked}>
    </td>
    <td class="${tdStyle}" style="text-align: center">
     <a href="javascript:showEditTodo('${todo.todoId}')"><img src="${staticCtx}/images/pencil.png" border="0"/></a> 
     <a href="javascript:deleteTodo('${todo.todoId}')"><img src="${staticCtx}/images/bin_closed.png" border="0"/></a>
    </td>
   </tr>
  </c:forEach>
  <c:if test="${hideOlderTodos eq 'true'}">
   <tr class="hidden">
    <c:set var="hiddenColspan" value="5"/>
    <c:if test="${showAssignedUser eq 'true'}">
     <c:set var="hiddenColspan" value="6"/>
    </c:if>
    <td colspan="${hiddenColspan}">
     ${hiddenTodos} <fmt:message key="todos.hidden.description" />
    </td>
   </tr>
  </c:if>
 </table>
 </div>
 <br/><br/>
</c:if>
<c:if test="${not empty todoList}">
 <c:if test="${todoList.rssAllowed eq true}">
  <a href="${ctx}/rss/showRssFeed.action?listId=${todoList.listId}">
   <img width="30" height="14" alt="RSS" src="${staticCtx}/images/rss.gif" border="0"/>
  </a>
  <link rel="alternate" title="RSS feed" href="${ctx}/rss/showRssFeed.action?listId=${todoList.listId}" TYPE="application/rss+xml">
  | 
 </c:if>
 <a href="${ctx}/secure/backupTodoList.action?listId=${todoList.listId}">Backup <img width="9" height="10" alt="Backup" src="${ctx}/images/asc.gif" border="0"/></a> | 
 <a href="${ctx}/secure/restoreTodoList.action?listId=${todoList.listId}">Restore <img width="9" height="10" alt="Restore" src="${ctx}/images/desc.gif" border="0"/></a>
</c:if>
 