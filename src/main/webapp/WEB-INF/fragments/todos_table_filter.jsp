<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%
 request.setAttribute("ctx", request.getContextPath()); 
 request.setAttribute("now", java.util.Calendar.getInstance().getTime());
 %>

<div style="font: 18px/1.3em Arial,Helvetica,sans-serif;">
 <c:if test="${filter eq 'nextDays'}">
  <fmt:message key="todos.next.days.detailed"/>
 </c:if>
 <c:if test="${filter eq 'assignedToMe'}">
  <fmt:message key="todos.assigned.to.me.detailed"/>
 </c:if>
</div>
<c:if test="${empty todos}">
 <div class="message"><fmt:message key="todos.nothing.to.display"/></div>
</c:if>
<c:if test="${not empty todos}">
 <table class="list">
  <tr>
   <th class="${descriptionClass}">
    <fmt:message key="todos.filter.list.description"/>
   </th>
   <th class="${descriptionClass}">
    <fmt:message key="todos.description"/>
   </th>
   <th class="${priorityClass}" style="width: 80px">
    <fmt:message key="todos.priority"/>
   </th>
   <th class="${dueDateClass}" style="width: 80px">
    <fmt:message key="todos.due.date"/>
   </th>
  </tr>
  <c:set var="row" value="0"/>
  <c:forEach var="todo" items="${todos}">
   <c:set var="row" value="${row + 1}"/>
   <c:set var="trStyle" value="${row % 2 eq 0 ? 'even' : 'odd'}"/>
   <tr class="${trStyle}" id="${todo.todoId}">
    <td class="${tdStyle}">
     <a href="javascript:renderTableListId('${todo.todoList.listId}')">${todo.todoList.name}</a>
    </td>
    <td class="${tdStyle}">
     <c:if test="${todo.dueDate.time le now.time}">
      <img src="${ctx}/images/warning.gif" width="14" height="13" alt="!"/>
     </c:if>
     ${todo.description}
    </td>
    <td class="${tdStyle}" style="text-align: center">
     ${todo.priority}
    </td>
    <td class="${tdStyle}" style="text-align: center; font-size: 80%">
     <fmt:formatDate value="${todo.dueDate}" type="date" pattern="MM/dd/yyyy"/>
    </td>
   </tr>
  </c:forEach>
 </table>
 <br/><br/>
</c:if>
 