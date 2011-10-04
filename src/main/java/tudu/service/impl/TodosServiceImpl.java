package tudu.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tudu.domain.Todo;
import tudu.domain.TodoList;
import tudu.domain.User;
import tudu.domain.comparator.TodoByDueDateComparator;
import tudu.security.PermissionDeniedException;
import tudu.service.TodoListsService;
import tudu.service.TodosService;
import tudu.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Implementation of the tudu.service.TodosService interface.
 *
 * @author Julien Dubois
 */
@Service
@Transactional
public class TodosServiceImpl implements TodosService {

    private final Log log = LogFactory.getLog(TodosServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TodoListsService todoListsService;

    @Autowired
    private UserService userService;

    /**
     * Find a Todo by ID.
     *
     * @see tudu.service.TodosService#findTodo(java.lang.String)
     */
    @Transactional(readOnly = true)
    public Todo findTodo(final String todoId) {
        if (log.isDebugEnabled()) {
            log.debug("Finding Todo with ID " + todoId);
        }
        Todo todo = em.find(Todo.class, todoId);
        if (todo == null) {
            if (log.isInfoEnabled()) {
                log.info("Todo ID '" + todoId
                        + "' does not exist!");
            }
            return null;
        }
        TodoList todoList = todo.getTodoList();
        User user = userService.getCurrentUser();
        if (!user.getTodoLists().contains(todoList)) {
            if (log.isInfoEnabled()) {
                log.info("Permission denied when finding Todo ID '" + todoId
                        + "' for User '" + user.getLogin() + "'");
            }

            throw new PermissionDeniedException(
                    "Permission denied to access this Todo.");

        }
        return todo;
    }

    /**
     * @see tudu.service.TodosService#findUrgentTodos()
     */
    @Transactional(readOnly = true)
    public Collection<Todo> findUrgentTodos() {
        User user = userService.getCurrentUser();
        Calendar urgentCal = Calendar.getInstance();
        urgentCal.add(Calendar.DATE, 4);
        Date urgentDate = urgentCal.getTime();
        Set<Todo> urgentTodos = new TreeSet<Todo>(new TodoByDueDateComparator());
        for (TodoList todoList : user.getTodoLists()) {
            for (Todo todo : todoList.getTodos()) {
                if (todo.getDueDate() != null
                        && todo.getDueDate().before(urgentDate)
                        && !todo.isCompleted()) {

                    urgentTodos.add(todo);
                }
            }
        }
        return urgentTodos;
    }

    /**
     * @see tudu.service.TodosService#findAssignedTodos()
     */
    @Transactional(readOnly = true)
    public Collection<Todo> findAssignedTodos() {
        User user = userService.getCurrentUser();
        Set<Todo> assignedTodos = new TreeSet<Todo>();
        for (TodoList todoList : user.getTodoLists()) {
            for (Todo todo : todoList.getTodos()) {
                if (todo.getAssignedUser() != null
                        && todo.getAssignedUser().equals(user)
                        && !todo.isCompleted()) {

                    assignedTodos.add(todo);
                }
            }
        }
        return assignedTodos;
    }

    /**
     * Create a new Todo.
     *
     * @see tudu.service.TodosService#createTodo(java.lang.String listId,
     *      tudu.domain.Todo)
     */
    public void createTodo(final String listId, final Todo todo) {
        Date now = Calendar.getInstance().getTime();
        todo.setCreationDate(now);
        TodoList todoList = todoListsService.findTodoList(listId);
        todo.setTodoList(todoList);
        em.persist(todo);
        todoList.getTodos().add(todo);
        todoListsService.updateTodoList(todoList);
        if (log.isDebugEnabled()) {
            log.debug("Created Todo ID=" + todo.getTodoId());
        }
    }

    public void deleteTodo(final Todo todo) {
        TodoList todoList = todo.getTodoList();
        Set<Todo> todos = todoList.getTodos();
        if (todos.contains(todo)) {
            todos.remove(todo);
            todoListsService.updateTodoList(todoList);
            if (log.isDebugEnabled()) {
                log.debug("Removed Todo ID=" + todo.getTodoId() + " - list size="
                        + todoList.getTodos().size());
            }
        } else {
            log.warn("Todo " + todo.getTodoId() + " should have been in List "
                    + todoList.getListId());

        }
        em.remove(todo);
    }

    /**
     * @see tudu.service.TodosService#deleteAllCompletedTodos(java.lang.String)
     */
    public void deleteAllCompletedTodos(String listId) {
        TodoList todoList = todoListsService.findTodoList(listId);
        List<Todo> todosToRemove = new ArrayList<Todo>();
        for (Todo todo : todoList.getTodos()) {
            if (todo.isCompleted()) {
                todosToRemove.add(todo);
            }
        }
        todoList.getTodos().removeAll(todosToRemove);
        for (Todo todo : todosToRemove) {
            em.remove(todo);
        }
        todoListsService.updateTodoList(todoList);
    }

    /**
     * @see tudu.service.TodosService#completeTodo(java.lang.String)
     */
    public Todo completeTodo(String todoId) {
        Todo todo = this.findTodo(todoId);
        todo.setCompleted(true);
        todo.setCompletionDate(Calendar.getInstance().getTime());
        todoListsService.updateTodoList(todo.getTodoList());
        return todo;
    }

    /**
     * @see tudu.service.TodosService#reopenTodo(java.lang.String)
     */
    public Todo reopenTodo(String todoId) {
        Todo todo = this.findTodo(todoId);
        todo.setCompleted(false);
        todo.setCompletionDate(null);
        todoListsService.updateTodoList(todo.getTodoList());
        return todo;
    }

    public void updateTodo(Todo todo) {
        todoListsService.updateTodoList(todo.getTodoList());
    }
}
