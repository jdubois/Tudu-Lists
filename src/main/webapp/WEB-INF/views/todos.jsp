<%@ page import="tudu.service.impl.ConfigurationServiceImpl" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>New user registration</head>
<body>

<%
    request.setAttribute("ctx", request.getContextPath());
    String staticCtx = ConfigurationServiceImpl.staticContent;
    if (staticCtx.equals("")) {
        request.setAttribute("staticCtx", request.getContextPath());
    } else {
        request.setAttribute("staticCtx", staticCtx);
    }
%>

<script type='text/javascript' src='${staticCtx}/scripts/calendar.js'></script>
<script type='text/javascript' src='${staticCtx}/scripts/calendar-en.js'></script>
<script type='text/javascript' src='${staticCtx}/scripts/calendar-setup.js'></script>

<script type='text/javascript' src='${ctx}/secure/dwr/interface/todos.js'></script>
<script type='text/javascript' src='${ctx}/secure/dwr/interface/todo_lists.js'></script>

<script type='text/javascript'>

dwr.util.setEscapeHtml(false);

// Error handling
function errorHandler(msg) {
  //alert(msg);
}
dwr.engine.setErrorHandler(errorHandler);

// Keyboard listener
var keyboardListener = "on";
document.onkeydown = function(e) {
 if(!e) e = window.event;
 if(e.keyCode==13 && keyboardListener=="on") {
  if ($("addNewTodoDiv").style.display=="inline") {
   addTodo();
  } else if ($("editTodoDiv").style.display=="inline") {
   editTodo();
  }
 }
}

// Hide the add, edit, share, ... layers.
function hideTodosLayers() {
 $("addNewTodoDiv").style.display='none';
 document.forms.addNewTodoForm.description.value = '';
 document.forms.addNewTodoForm.priority.value = '';
 document.forms.addNewTodoForm.dueDate.value = '';
 document.forms.addNewTodoForm.notes.value = '';
 $("editTodoDiv").style.display='none';
 document.forms.editTodoForm.description.value = '';
 document.forms.editTodoForm.priority.value = '';
 document.forms.editTodoForm.dueDate.value = '';
 document.forms.editTodoForm.notes.value = '';
 $("addNewListDiv").style.display='none';
 document.forms.addNewListForm.name.value = '';
 document.forms.addNewListForm.rssAllowed.checked = false;
 $("editListDiv").style.display='none';
 document.forms.editListForm.name.value = '';
 document.forms.editListForm.rssAllowed.checked = false;
 $("shareListDiv").style.display='none';
 document.forms.shareListForm.login.value = '';
}

// Show the "add a new todo" layer.
function showAddTodo() {
 hideTodosLayers();
 var listId = document.forms.todoForm.listId.value;
 createAssignedUserList('addNewTodoAssignedUser', listId);
 $("addNewTodoDiv").style.display="inline";
 document.forms.addNewTodoForm.description.focus();
 tracker('/ajax/showAddTodo');
}

// Show the "edit a todo" layer.
function showEditTodo(todoId) {
 hideTodosLayers();
 document.forms.editTodoForm.todoId.value = todoId;
 var listId = document.forms.todoForm.listId.value;
 dwr.engine.beginBatch();
 createAssignedUserList('editTodoAssignedUser', listId);
 todos.getTodoById(todoId, replyGetTodoById);
 dwr.engine.endBatch();
 $("editTodoDiv").style.display="inline";
 document.forms.editTodoForm.description.focus();
 tracker('/ajax/showEditTodo');
}

var replyGetTodoById = function(todo) {
 document.forms.editTodoForm.description.value = todo.description;
 document.forms.editTodoForm.priority.value = todo.priority;
 document.forms.editTodoForm.dueDate.value = todo.dueDate;
 if (todo.notes == null) {
  document.forms.editTodoForm.notes.value = '';
 } else {
  document.forms.editTodoForm.notes.value = todo.notes;
 }
 dwr.util.setValue('editTodoAssignedUser', todo.assignedUserLogin);
}  

function createAssignedUserList(id, listId) {
 dwr.util.removeAllOptions(id);
 dwr.util.addOptions(id, ['<%=request.getRemoteUser()%>']);
 dwr.util.addOptions(id, [{desc:'-- <fmt:message key="todos.assigned.nobody"/> --',id:''}],'id','desc');
 var reply = function(data) {
  dwr.util.addOptions(id, data);
 }
 todo_lists.getTodoListUsers(listId, reply);
}

//Render the main todo table.
function renderTableListId(listId) {
 hideTodosLayers();
 document.forms.todoForm.listId.value = listId;
 todos.forceRenderTodos(listId, replyRenderTable);
 tracker('/ajax/renderTableListId');
}

var tableDate;
//Render the main todo table.
function renderTable() {
 var listId = document.forms.todoForm.listId.value;
 if (tableDate == null) {
  todos.forceRenderTodos(listId, replyRenderTable);
 } else {
  todos.renderTodos(listId, tableDate, replyRenderTable);
 }
 tracker('/ajax/renderTable');
}

//Render the main todo table with the todos due in the next 4 days
function renderNextDays() {
 document.forms.todoForm.listId.value = null;
 todos.renderNextDays(replyRenderTable);
 tracker('/ajax/renderNextDays');
}

//Render the main todo table with the todos assigned to the current user
function renderAssignedToMe() {
 document.forms.todoForm.listId.value = null;
 todos.renderAssignedToMe(replyRenderTable);
 tracker('/ajax/renderAssignedToMe');
}

var replyRenderTable = function(data) {
 if (data.length > 0) {
  dwr.util.setValue('todosTable', data);
 }
 tableDate = new Date();
 document.forms.quickAddNewTodoForm.description.focus();
}

//Sort the main todo table
function sortTable(sorter) {
 hideTodosLayers();
 var listId = document.forms.todoForm.listId.value;
 todos.sortAndRenderTodos(listId, sorter, replyRenderTable);
 tracker('/ajax/sortTable');
}

//Add a todo
function addTodo() {
 var listId = document.forms.todoForm.listId.value;
 var description = document.forms.addNewTodoForm.description.value;
 var priority = document.forms.addNewTodoForm.priority.value;
 var dueDate = document.forms.addNewTodoForm.dueDate.value;
 var notes = document.forms.addNewTodoForm.notes.value;
 var assignedUser = document.forms.addNewTodoForm.assignedUser.value;
 if (validateForm(priority, dueDate, notes) != "ok") {
   return;
 }
 $("addNewTodoDiv").style.display='none';
 commonAddTodo(listId, description, priority, dueDate, notes, assignedUser);
 tracker('/ajax/addTodo');
}

//Quick add a todo
function quickAddTodo() {
 var listId = document.forms.todoForm.listId.value;
 var description = document.forms.quickAddNewTodoForm.description.value;
 if (description != "") {
  commonAddTodo(listId, description, 0, "", null, "<%=request.getRemoteUser()%>");
  tracker('/ajax/quickAddTodo');
 }
}

//Common function for adding a todo
function commonAddTodo(listId, description, priority, dueDate, notes, assignedUser) {
 dwr.engine.beginBatch();
 todos.addTodo(listId, description, priority, dueDate, notes, assignedUser, replyRenderTable);
 todos.forceGetCurrentTodoLists(replyCurrentTodoLists);
 dwr.engine.endBatch();
}

//Cancel the Add Todo action
function cancelAddTodo() {
 $("addNewTodoDiv").style.display='none';
}

//Reopen a todo
function reopenTodo(todoId) {
 dwr.engine.beginBatch();
 todos.reopenTodo(todoId, replyRenderTable);
 todos.forceGetCurrentTodoLists(replyCurrentTodoLists);
 dwr.engine.endBatch();
 tracker('/ajax/reopenTodo');
}

//Complete a todo
function completeTodo(todoId) {
 dwr.engine.beginBatch();
 todos.completeTodo(todoId, replyRenderTable);
 todos.forceGetCurrentTodoLists(replyCurrentTodoLists);
 dwr.engine.endBatch();
 tracker('/ajax/completeTodo');
}

//Edit a todo
function editTodo() {
 var todoId = document.forms.editTodoForm.todoId.value;
 var description = document.forms.editTodoForm.description.value;
 var priority = document.forms.editTodoForm.priority.value;
 var dueDate = document.forms.editTodoForm.dueDate.value;
 var notes = document.forms.editTodoForm.notes.value;
 var assignedUser = document.forms.editTodoForm.assignedUser.value;
 if (validateForm(priority, dueDate, notes) != "ok") {
   return;
 }
 $("editTodoDiv").style.display='none';
 todos.editTodo(todoId, description, priority, dueDate, notes, assignedUser, replyRenderTable);
 tracker('/ajax/editTodo');
}

//Show the "quick edit" function for editing a todo
function showQuickEditTodo(todoId) {
 $("show-" + todoId).style.display='none';
 $("edit-" + todoId).style.display='inline';
 $("edit-in-" + todoId).focus();
}

//Quick edit a todo
function quickEditTodo(todoId, description) {
 todos.quickEditTodo(todoId, description, replyRenderTable);
 tracker('/ajax/quickEditTodo');
}

//Cancel the Edit Todo action
function cancelEditTodo() {
 $("editTodoDiv").style.display='none';
}

// Delete a todo.
function deleteTodo(todoId) {
 var sure = confirm("<fmt:message key="todos.delete.confirm"/>");
 if (sure) {
  hideTodosLayers();
  dwr.engine.beginBatch();
  todos.deleteTodo(todoId, replyRenderTable);
  todos.forceGetCurrentTodoLists(replyCurrentTodoLists);
  dwr.engine.endBatch();
  tracker('/ajax/deleteTodo');
 }
}

// Delete all completed todos.
function deleteAllCompletedTodos(todoId) {
 var sure = confirm("<fmt:message key="todos.delete.all.confirm"/>");
 if (sure) {
  var listId = document.forms.todoForm.listId.value;
  hideTodosLayers();
  dwr.engine.beginBatch();
  todos.deleteAllCompletedTodos(listId, replyRenderTable);
  todos.forceGetCurrentTodoLists(replyCurrentTodoLists);
  dwr.engine.endBatch();
  tracker('/ajax/deleteAllCompletedTodos');
 }
}

// Show the older todos.
function showOlderTodos() {
 var listId = document.forms.todoForm.listId.value;
 todos.showOlderTodos(listId, replyRenderTable);
 tracker('/ajax/showOlderTodos');
}

// Hide the older todos.
function hideOlderTodos() {
 var listId = document.forms.todoForm.listId.value;
 todos.hideOlderTodos(listId, replyRenderTable);
 tracker('/ajax/hideOlderTodos');
}

//Validate the priority and the date
function validateForm(priority, dueDate, notes) {
 if (priority != "" && !priority.match(/^\d+$/)) {
   alert("<fmt:message key="todos.error.priority"/>");
   return "nok";
 }
 if (dueDate != "" && !dueDate.match(/^\d{1,2}\/\d{1,2}\/\d{1,4}$/)) {
   alert("<fmt:message key="todos.error.date"/>");
   return "nok";
 }
 if (notes.length > 10000) {
   alert("<fmt:message key="todos.error.notes.length"/>");
   return "nok";
 }
 return "ok";
}
</script>

<table>
 <tr>
  <td style="width: 150px; vertical-align: top; border: 1px solid #C0C0C0">
   <div id="menuDiv">
    <jsp:include page="/WEB-INF/fragments/todos_menu.jsp"/>
   </div>
  </td>
  <td style="width:10px"></td>
  <td style="width: 100%; vertical-align: top; text-align: center; border: 1px solid #C0C0C0">

  <!-- The "Add a new Todo" layer -->
   <div id="addNewTodoDiv" class="box" style="display: none" align="center">
    <div class="box_head"><h2><fmt:message key="todos.actions.add"/></h2></div>
    <div class="box_body">
     <form name="addNewTodoForm" action="">
      <table>
       <tr>
        <td style="text-align: left">
         <fmt:message key="todos.description"/>
        </td>
        <td colspan="3" style="text-align: left">
         <input type="text" name="description" size="50" maxlength="255"/>
        </td>
       </tr>
       <tr>
        <td style="text-align: left">
         <fmt:message key="todos.priority"/>
        </td>
        <td style="text-align: left">
         <input type="text" name="priority" size="5" maxlength="4"/>
        </td>
        <td style="text-align: left">
         <fmt:message key="todos.due.date"/>
        </td>
        <td style="text-align: left">
         <input type="text" name="dueDate" id="addDueDateId" size="10" maxlength="10"/>
         <img src="${staticCtx}/images/date.png" alt="Calendar" width="16" height="16" id="add_trigger_calendar" style="cursor: pointer;"/>
         <span style="font-size: 80%">(${fn:toLowerCase(dateFormat)})</span>
        </td>
       </tr>
       <tr>
        <td style="text-align: left">
         <fmt:message key="todos.assigned.user"/>
        </td>
        <td colspan="3" style="text-align: left">
         <select id="addNewTodoAssignedUser" name="assignedUser"><option></option></select>
        </td>
       </tr>
       <tr>
        <td style="text-align: left">
         <fmt:message key="todos.notes"/>
         <img src="${staticCtx}/images/note.png" width="16" height="16" alt=""/>
        </td>
        <td colspan="3" style="text-align: left">
         <textarea name="notes" rows="10" cols="37" onfocus="keyboardListener='off'" onblur="keyboardListener='on'"></textarea>
        </td>
       </tr>
       <tr>
        <td colspan="4" style="text-align: center;">
         [ <a href="javascript:addTodo();" onfocus="keyboardListener='off'" onblur="keyboardListener='on'"><fmt:message key="form.submit"/></a> ]
         &nbsp;&nbsp;
         [ <a href="javascript:cancelAddTodo();" onfocus="keyboardListener='off'" onblur="keyboardListener='on'"><fmt:message key="form.cancel"/></a> ]
        </td>
       </tr>
      </table>
      <br/>
     </form>
     <script type='text/javascript'>
      Calendar.setup({
       weekNumbers: false,
       inputField : "addDueDateId",
       ifFormat : "${calendarDateFormat}",
       button : "add_trigger_calendar"
      });
     </script>
    </div>
   </div>

  <!-- The "Edit a Todo" layer -->
   <div id="editTodoDiv" class="box" style="display: none;" align="center">
    <div class="box_head"><h2><fmt:message key="todos.edit.title"/></h2></div>
    <div class="box_body">
     <form name="editTodoForm" action="">
      <input type="hidden" name="todoId"/>
      <table>
       <tr>
        <td style="text-align: left">
         <fmt:message key="todos.description"/>
        </td>
        <td colspan="3" style="text-align: left">
         <input type="text" name="description" size="50" maxlength="255"/>
        </td>
       </tr>
       <tr>
        <td style="text-align: left">
         <fmt:message key="todos.priority"/>
        </td>
        <td style="text-align: left">
         <input type="text" name="priority" size="5" maxlength="4"/>
        </td>
        <td style="text-align: left">
         <fmt:message key="todos.due.date"/>
        </td>
        <td>
         <input type="text" name="dueDate" id="editDueDateId" size="10" maxlength="10"/>
         <img src="${staticCtx}/images/date.png" alt="Calendar" width="16" height="16" id="edit_trigger_calendar" style="cursor: pointer;" />
         <span style="font-size: 80%">(${fn:toLowerCase(dateFormat)})</span>
        </td>
       </tr>
       <tr>
        <td style="text-align: left">
         <fmt:message key="todos.assigned.user"/>
        </td>
        <td colspan="3" style="text-align: left">
         <select id="editTodoAssignedUser" name="assignedUser"><option></option></select>
        </td>
       </tr>
       <tr>
        <td style="text-align: left">
         <fmt:message key="todos.notes"/>
         <img src="${staticCtx}/images/note.png" width="16" height="16" alt=""/>
        </td>
        <td colspan="3" style="text-align: left">
         <textarea name="notes" rows="10" cols="37" onfocus="keyboardListener='off'" onblur="keyboardListener='on'"></textarea>
        </td>
       </tr>
       <tr>
        <td colspan="4" style="text-align: center;">
         [ <a href="javascript:editTodo();" onfocus="keyboardListener='off'" onblur="keyboardListener='on'"><fmt:message key="form.submit"/></a> ]
         &nbsp;&nbsp;
         [ <a href="javascript:cancelEditTodo();" onfocus="keyboardListener='off'" onblur="keyboardListener='on'"><fmt:message key="form.cancel"/></a> ]
        </td>
       </tr>
      </table>
      <br/>
     </form>
     <script type='text/javascript'>
      Calendar.setup({
       weekNumbers: false,
       inputField : "editDueDateId",
       ifFormat : "${calendarDateFormat}",
       button : "edit_trigger_calendar"
      });
     </script>
    </div>
   </div>

  <!-- The "Add a new list" layer -->
   <div id="addNewListDiv" class="box" style="display: none" align="center">
    <div class="box_head"><h2><fmt:message key="todo.lists.actions.add"/></h2></div>
    <div class="box_body">
     <form name="addNewListForm" onsubmit="addTodoList();return false;" action="">
      <table>
       <tr>
        <td>
         <fmt:message key="todo.lists.name"/>
        </td>
        <td>
         <input type="text" name="name" size="25" maxlength="50"/>
        </td>
       </tr>
       <tr>
        <td>
         <fmt:message key="todo.lists.edit.rss"/>
        </td>
        <td>
         <input type="checkbox" name="rssAllowed"/>
        </td>
       </tr>
       <tr>
        <td colspan="2" style="text-align: center;">
         [ <a href="javascript:addTodoList();"><fmt:message key="form.submit"/></a> ]
         &nbsp;&nbsp;
         [ <a href="javascript:cancelAddTodoList();"><fmt:message key="form.cancel"/></a> ]
        </td>
       </tr>
      </table>
      <br/>
     </form>
    </div>
   </div>

   <!-- The "Edit current list" layer -->
   <div id="editListDiv" class="box" style="display: none" align="center">
    <div class="box_head"><h2><fmt:message key="todo.lists.edit.title"/></h2></div>
    <div class="box_body">
     <form name="editListForm" onsubmit="editTodoList();return false;" action="">
     <table>
      <tr>
       <td>
        <fmt:message key="todo.lists.name"/>
       </td>
       <td>
        <input type="text" name="name" size="25" maxlength="50"/>
       </td>
      </tr>
      <tr>
       <td>
        <fmt:message key="todo.lists.edit.rss"/>
       </td>
       <td>
        <input type="checkbox" name="rssAllowed"/>
       </td>
      </tr>
      <tr>
       <td colspan="2" style="text-align: center;">
        [ <a href="javascript:editTodoList();"><fmt:message key="form.submit"/></a> ]
        &nbsp;&nbsp;
        [ <a href="javascript:cancelEditTodoList();"><fmt:message key="form.cancel"/></a> ]
       </td>
      </tr>
     </table>
     <br/>
    </form>
   </div>
  </div>

   <!-- The "Share current list" layer -->
   <div id="shareListDiv" class="box" style="display: none" align="center">
    <div class="box_head"><h2><fmt:message key="todo.lists.share.title"/></h2></div>
    <div class="box_body">
     <form name="shareListForm" action="">
     <table>
      <tr>
       <td>
        <fmt:message key="todo.lists.share.add"/>
       </td>
       <td>
        <input type="text" name="login" id="login" size="20" maxlength="50"/>
        &nbsp;&nbsp;
        <a href="javascript:addTodoListUser();"><fmt:message key="todo.lists.share.submit"/></a>
       </td>
      </tr>
      <tr>
       <td style="vertical-align: top">
        <fmt:message key="todo.lists.share.currentusers"/>
       </td>
       <td>
        <table id="usersTable">
         <tbody style="text-align: left;" id="usersTableBody"><tr><td></td></tr></tbody>
        </table>
       </td>
      </tr>
      <tr>
       <td colspan="2" style="text-align: right;">
        [ <a href="javascript:renderTable();hideTodosLayers();"><fmt:message key="common.hide"/></a> ]
       </td>
      </tr>
     </table>
     <br/>
    </form>
   </div>
  </div>

   <!-- The main table, displaying the current Todos -->
   <div id="todosTable" style="min-height: 500px; width:100%"></div>

  </td>
 </tr>
</table>

<form name="todoForm" action="">
 <input type="hidden" name="listId" value="${defaultList}"/>
</form>

<script type="text/javascript">
 // The todos table should be refreshed automatically every 2 minutes.
 function reloadingTable() {
  renderTable();
  setTimeout('reloadingTable();', 2 * 60 * 1000);
 }
 reloadingTable();
</script>

</body>
</html>
