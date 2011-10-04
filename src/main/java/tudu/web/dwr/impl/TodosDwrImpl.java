package tudu.web.dwr.impl;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import tudu.domain.Todo;
import tudu.domain.TodoList;
import tudu.domain.User;
import tudu.domain.comparator.*;
import tudu.service.TodoListsService;
import tudu.service.TodosService;
import tudu.service.UserService;
import tudu.web.dwr.TodosDwr;
import tudu.web.dwr.bean.RemoteTodo;
import tudu.web.dwr.bean.RemoteTodoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Implementation of the tudu.service.TodosService interface.
 * 
 * @author Julien Dubois
 */
public class TodosDwrImpl implements TodosDwr {

    private static final String TODO_LIST_SORT_BY = "TODO_LIST_SORT_BY";

    private static final String SORT_BY_PRIORITY = "priority";

    private static final String SORT_BY_PRIORITY_ASC = "priority_asc";

    private static final String SORT_BY_DESCRIPTION = "description";

    private static final String SORT_BY_DESCRIPTION_ASC = "description_asc";

    private static final String SORT_BY_DUE_DATE = "due_date";

    private static final String SORT_BY_DUE_DATE_ASC = "due_date_asc";

    private static final String SORT_BY_ASSIGNED_USER = "assigned_user";

    private static final String SORT_BY_ASSIGNED_USER_ASC = "assigned_user_asc";

    private final Log log = LogFactory.getLog(TodosDwrImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TodosService todosService;

    @Autowired
    private TodoListsService todoListsService;

    /**
     * @see tudu.web.dwr.TodosDwr#getCurrentTodoLists(java.util.Date)
     */
    public RemoteTodoList[] getCurrentTodoLists(Date menuDate) {
        User user = userService.getCurrentUser();
        Collection<TodoList> todoLists = user.getTodoLists();

        if (menuDate != null) {
            boolean aListHasBeenUpdated = false;
            for (TodoList todoList : todoLists) {
                if (todoList.getLastUpdate().after(menuDate)) {
                    aListHasBeenUpdated = true;
                }
            }
            if (!aListHasBeenUpdated) {
                return null;
            }
        }

        Collection<RemoteTodoList> remoteTodoLists = new TreeSet<RemoteTodoList>();

        for (TodoList todoList : todoLists) {
            RemoteTodoList remoteTodoList = new RemoteTodoList();
            remoteTodoList.setListId(todoList.getListId());
            remoteTodoList.setName(todoList.getName());
            int completed = 0;
            for (Todo todo : todoList.getTodos()) {
                if (todo.isCompleted()) {
                    completed++;
                }
            }
            remoteTodoList.setDescription(todoList.getName() + " (" + completed
                    + "/" + todoList.getTodos().size() + ")");

            remoteTodoLists.add(remoteTodoList);
        }
        return remoteTodoLists.toArray(new RemoteTodoList[remoteTodoLists
                .size()]);
    }

    /**
     * @see tudu.web.dwr.TodosDwr#forceGetCurrentTodoLists()
     */
    public RemoteTodoList[] forceGetCurrentTodoLists() {
        return this.getCurrentTodoLists(null);
    }

    /**
     * @see tudu.web.dwr.TodosDwr#getTodoById(java.lang.String)
     */
    public RemoteTodo getTodoById(String todoId) {
        Todo todo = todosService.findTodo(todoId);
        RemoteTodo remoteTodo = new RemoteTodo();
        String unescapedDescription = StringEscapeUtils.unescapeHtml(todo
                .getDescription());
        remoteTodo.setDescription(unescapedDescription);
        remoteTodo.setPriority(todo.getPriority());
        if (todo.getDueDate() != null) {
            String formattedDate = getDateFormatter().format(todo.getDueDate());
            remoteTodo.setDueDate(formattedDate);
        } else {
            remoteTodo.setDueDate("");
        }
        remoteTodo.setHasNotes(todo.isHasNotes());
        if (remoteTodo.isHasNotes()) {
            remoteTodo.setNotes(todo.getNotes());
        }
        if (todo.getAssignedUser() != null) {
            remoteTodo.setAssignedUserLogin(todo.getAssignedUser().getLogin());
        }
        return remoteTodo;
    }

    /**
     * @see tudu.web.dwr.TodosDwr#renderTodos(java.lang.String, java.util.Date)
     */
    public String renderTodos(String listId, Date tableDate) {
        HttpServletRequest request = WebContextFactory.get()
                .getHttpServletRequest();

        if (listId != null && !listId.equals("")) {
            TodoList todoList = todoListsService.findTodoList(listId);
            if (tableDate != null && todoList.getLastUpdate().before(tableDate)) {
                return "";
            }

            request.setAttribute("todoList", todoList);
            Set<Todo> todos = todoList.getTodos();
            String sorter = (String) request.getSession().getAttribute(
                    TODO_LIST_SORT_BY);

            Set<Todo> sortedTodos;
            String descriptionClass = "sortable";
            String priorityClass = "sortable";
            String dueDateClass = "sortable";
            String assignedUserClass = "sortable";
            if (sorter != null) {
                if (sorter.equals(SORT_BY_DESCRIPTION)) {
                    descriptionClass = "sorted";
                    sortedTodos = new TreeSet<Todo>(
                            new TodoByDescriptionComparator());
                } else if (sorter.equals(SORT_BY_DESCRIPTION_ASC)) {
                    descriptionClass = "sorted_asc";
                    sortedTodos = new TreeSet<Todo>(
                            new TodoByDescriptionAscComparator());
                } else if (sorter.equals(SORT_BY_DUE_DATE)) {
                    dueDateClass = "sorted";
                    sortedTodos = new TreeSet<Todo>(
                            new TodoByDueDateComparator());
                } else if (sorter.equals(SORT_BY_DUE_DATE_ASC)) {
                    dueDateClass = "sorted_asc";
                    sortedTodos = new TreeSet<Todo>(
                            new TodoByDueDateAscComparator());
                } else if (sorter.equals(SORT_BY_ASSIGNED_USER)) {
                    assignedUserClass = "sorted";
                    sortedTodos = new TreeSet<Todo>(
                            new TodoByAssignedUserComparator());
                } else if (sorter.equals(SORT_BY_ASSIGNED_USER_ASC)) {
                    assignedUserClass = "sorted_asc";
                    sortedTodos = new TreeSet<Todo>(
                            new TodoByAssignedUserAscComparator());
                } else if (sorter.equals(SORT_BY_PRIORITY_ASC)) {
                    priorityClass = "sorted_asc";
                    sortedTodos = new TreeSet<Todo>(
                            new TodoByPriorityAscComparator());
                } else {
                    priorityClass = "sorted";
                    sortedTodos = new TreeSet<Todo>();
                }
            } else {
                priorityClass = "sorted";
                sortedTodos = new TreeSet<Todo>();
            }

            String hideOlderTodos = (String) request.getSession().getAttribute(
                    "hideOlderTodos");

            if (hideOlderTodos == null) {
                hideOlderTodos = "true";
                request.getSession().setAttribute("hideOlderTodos",
                        hideOlderTodos);
            }
            if (hideOlderTodos.equals("true")) {
                Calendar oneDayAgo = Calendar.getInstance();
                oneDayAgo.set(Calendar.DATE, oneDayAgo.get(Calendar.DATE) - 1);
                oneDayAgo.set(Calendar.HOUR, 0);
                oneDayAgo.set(Calendar.MINUTE, 0);
                oneDayAgo.set(Calendar.SECOND, 0);
                int hiddenTodos = 0;
                for (Todo todo : todos) {
                    if (todo.getCompletionDate() == null
                            || todo.getCompletionDate().after(
                                    oneDayAgo.getTime())) {

                        sortedTodos.add(todo);
                    } else {
                        hiddenTodos++;
                    }
                }
                request.setAttribute("hiddenTodos", hiddenTodos);
            } else {
                sortedTodos.addAll(todos);
            }
            request.setAttribute("todos", sortedTodos);
            request.setAttribute("descriptionClass", descriptionClass);
            request.setAttribute("priorityClass", priorityClass);
            request.setAttribute("dueDateClass", dueDateClass);
            request.setAttribute("assignedUserClass", assignedUserClass);

            int nbCompleted = 0;
            for (Todo todo : todos) {
                if (todo.isCompleted()) {
                    nbCompleted++;
                }
            }
            if (todos.size() != 0) {
                request.setAttribute("completion", nbCompleted * 100
                        / todos.size());
            } else {
                request.setAttribute("completion", 100);
            }
        } else {
            return "";
        }
        try {
            return WebContextFactory.get().forwardToString(
                    "/WEB-INF/fragments/todos_table.jsp");
        } catch (ServletException e) {
            log.error("ServletException : " + e);
            return "";
        } catch (IOException ioe) {
            log.error("IOException : " + ioe);
            return "";
        }
    }

    /**
     * @see tudu.web.dwr.TodosDwr#renderNextDays()
     */
    public String renderNextDays() {
        HttpServletRequest request = WebContextFactory.get()
                .getHttpServletRequest();

        request.setAttribute("filter", "nextDays");
        request.setAttribute("todos", todosService.findUrgentTodos());
        return renderFilter();
    }

    public String renderAssignedToMe() {
        HttpServletRequest request = WebContextFactory.get()
                .getHttpServletRequest();

        request.setAttribute("filter", "assignedToMe");
        request.setAttribute("todos", todosService.findAssignedTodos());
        return renderFilter();
    }

    /**
     * Render the filtered data.
     */
    private String renderFilter() {
        try {
            return WebContextFactory.get().forwardToString(
                    "/WEB-INF/fragments/todos_table_filter.jsp");
        } catch (ServletException e) {
            log.error("ServletException : " + e);
            return "";
        } catch (IOException ioe) {
            log.error("IOException : " + ioe);
            return "";
        }
    }

    /**
     * @see tudu.web.dwr.TodosDwr#forceRenderTodos(java.lang.String)
     */
    public String forceRenderTodos(String listId) {
        return this.renderTodos(listId, null);
    }

    /**
     * Sort the List according to the "sorter" passed as parameter.
     * <p>
     * If the provided "sorter" is equals to the current list "sorter", then the
     * user must have clicked again on the sort button : in that case he wants
     * to sort the list the other way around (ascending).
     * </p>
     * 
     * @see tudu.web.dwr.TodosDwr#sortAndRenderTodos(java.lang.String,
     *      java.lang.String)
     */
    public String sortAndRenderTodos(String listId, String sorter) {
        HttpSession session = WebContextFactory.get()
                .getHttpServletRequest().getSession();

        String currentSorter = (String) session.getAttribute(TODO_LIST_SORT_BY);
        if (currentSorter != null && currentSorter.equals(sorter)
                && !currentSorter.endsWith("_asc")) {

            sorter += "_asc";
        } else if (currentSorter == null && sorter.equals(SORT_BY_PRIORITY)) {
            sorter += "_asc";
        }
        session.setAttribute(TODO_LIST_SORT_BY, sorter);
        return this.forceRenderTodos(listId);
    }

    /**
     * @see tudu.web.dwr.TodosDwr#addTodo(java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public String addTodo(String listId, String description, String priority,
            String dueDate, String notes, String assignedUserLogin) {

        Todo todo = new Todo();
        String escapedDescription = StringEscapeUtils.escapeHtml(description);
        todo.setDescription(escapedDescription);

        int priorityInt = 0;
        try {
            priorityInt = Integer.valueOf(priority);
        } catch (NumberFormatException e) {
            // The priority is not a number.
        }
        todo.setPriority(priorityInt);

        try {
            Date due = getDateFormatter().parse(dueDate);
            todo.setDueDate(due);
        } catch (ParseException e) {
            // The date is not correct
        }

        inputNotes(todo, notes);
        inputAssignedUser(todo, assignedUserLogin);

        todosService.createTodo(listId, todo);
        return forceRenderTodos(listId);
    }

    /**
     * @see tudu.web.dwr.TodosDwr#reopenTodo(java.lang.String)
     */
    public String reopenTodo(String todoId) {
        Todo todo = todosService.reopenTodo(todoId);
        return forceRenderTodos(todo.getTodoList().getListId());
    }

    /**
     * @see tudu.web.dwr.TodosDwr#completeTodo(java.lang.String)
     */
    public String completeTodo(String todoId) {
        Todo todo = todosService.completeTodo(todoId);
        return forceRenderTodos(todo.getTodoList().getListId());
    }

    /**
     * @see tudu.web.dwr.TodosDwr#editTodo(java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public String editTodo(String todoId, String description, String priority,
            String dueDate, String notes, String assignedUserLogin) {

        Todo todo = todosService.findTodo(todoId);
        String escapedDescription = StringEscapeUtils.escapeHtml(description);
        todo.setDescription(escapedDescription);

        int priorityInt = 0;
        try {
            priorityInt = Integer.parseInt(priority);
        } catch (NumberFormatException e) {
            // The priority is not a number.
        }
        todo.setPriority(priorityInt);

        if (dueDate == null || dueDate.equals("")) {
            todo.setDueDate(null);
        } else {
            try {
                Date due = getDateFormatter().parse(dueDate);
                todo.setDueDate(due);
            } catch (ParseException e) {
                // The date is not correct
            }
        }

        inputNotes(todo, notes);
        inputAssignedUser(todo, assignedUserLogin);

        todosService.updateTodo(todo);
        return forceRenderTodos(todo.getTodoList().getListId());
    }
    
    /**
     * @see tudu.web.dwr.TodosDwr#quickEditTodo(java.lang.String, java.lang.String)
     */
    public String quickEditTodo(String todoId, String description) {
        Todo todo = todosService.findTodo(todoId);
        String escapedDescription = StringEscapeUtils.escapeHtml(description);
        todo.setDescription(escapedDescription);
        todosService.updateTodo(todo);
        return forceRenderTodos(todo.getTodoList().getListId());
    }

    /**
     * @see tudu.web.dwr.TodosDwr#deleteTodo(java.lang.String)
     */
    public String deleteTodo(String todoId) {
        Todo todo = todosService.findTodo(todoId);
        String listId = todo.getTodoList().getListId();
        todosService.deleteTodo(todo);
        return forceRenderTodos(listId);
    }

    /**
     * @see tudu.web.dwr.TodosDwr#deleteAllCompletedTodos(java.lang.String)
     */
    public String deleteAllCompletedTodos(String listId) {
        todosService.deleteAllCompletedTodos(listId);
        return forceRenderTodos(listId);
    }

    /**
     * @see tudu.web.dwr.TodosDwr#showOlderTodos(java.lang.String)
     */
    public String showOlderTodos(String listId) {
        HttpSession session = WebContextFactory.get()
                .getHttpServletRequest().getSession();

        session.setAttribute("hideOlderTodos", "false");
        return forceRenderTodos(listId);
    }

    /**
     * @see tudu.web.dwr.TodosDwr#hideOlderTodos(java.lang.String)
     */
    public String hideOlderTodos(String listId) {
        HttpSession session = WebContextFactory.get()
                .getHttpServletRequest().getSession();

        session.setAttribute("hideOlderTodos", "true");
        return forceRenderTodos(listId);
    }

    /**
     * Find a Date Formatter.
     * 
     * @return the Date formatter
     */
    protected SimpleDateFormat getDateFormatter() {
        String dateFormat = null; //(String) WebContextFactory.get().getSession().getAttribute("dateFormat");

        if (dateFormat == null) {
            dateFormat = userService.getCurrentUser().getDateFormat();
            //WebContextFactory.get().getSession().setAttribute("dateFormat", dateFormat);
        }
        return new SimpleDateFormat(dateFormat);
    }

    /**
     * Insert notes into the todo.
     * 
     * @param todo
     *            The Todo
     * @param notes
     *            The notes
     */
    protected void inputNotes(Todo todo, String notes) {
        if (notes != null && !notes.equals("")) {
            todo.setHasNotes(true);
            if (notes.length() > 10000) {
                todo.setNotes(notes.substring(0, 10000));
            } else {
                todo.setNotes(notes);
            }
        } else {
            todo.setHasNotes(false);
            todo.setNotes(null);
        }
    }

    /**
     * Insert the assigned user into the todo.
     * 
     * @param todo
     *            The Todo
     * @param assignedUserLogin
     *            The login of the assigned user
     */
    private void inputAssignedUser(Todo todo, String assignedUserLogin) {
        if (assignedUserLogin != null && !assignedUserLogin.equals("")) {
            try {
                User assignedUser = userService.findUser(assignedUserLogin);
                todo.setAssignedUser(assignedUser);
            } catch (ObjectRetrievalFailureException orfe) {
                todo.setAssignedUser(null);
            }
        } else {
            todo.setAssignedUser(null);
        }
    }
}
