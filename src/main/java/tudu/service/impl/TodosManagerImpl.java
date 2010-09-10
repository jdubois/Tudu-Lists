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
import tudu.service.TodoListsManager;
import tudu.service.TodosManager;
import tudu.service.UserManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Implementation of the tudu.service.TodosManager interface.
 *
 * @author Julien Dubois
 */
@Service
@Transactional
public class TodosManagerImpl implements TodosManager {

    private final Log log = LogFactory.getLog(TodosManagerImpl.class);


    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TodoListsManager todoListsManager;

    @Autowired
    private UserManager userManager;

    /**
     * Find a Todo by ID.
     *
     * @see tudu.service.TodosManager#findTodo(java.lang.String)
     */
    @Transactional(readOnly = true)
    public Todo findTodo(final String todoId) {
        if (log.isDebugEnabled()) {
            log.debug("Finding Todo with ID " + todoId);
        }
        Todo todo = em.find(Todo.class, todoId);
        TodoList todoList = todo.getTodoList();
        User user = userManager.getCurrentUser();
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
     * @see tudu.service.TodosManager#findUrgentTodos()
     */
    @Transactional(readOnly = true)
    public Collection<Todo> findUrgentTodos() {
        User user = userManager.getCurrentUser();
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
     * @see tudu.service.TodosManager#findAssignedTodos()
     */
    @Transactional(readOnly = true)
    public Collection<Todo> findAssignedTodos() {
        User user = userManager.getCurrentUser();
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
     * @see tudu.service.TodosManager#createTodo(java.lang.String listId,
     *      tudu.domain.Todo)
     */
    public void createTodo(final String listId, final Todo todo) {

        Date now = Calendar.getInstance().getTime();
        todo.setCreationDate(now);
        TodoList todoList = todoListsManager.findTodoList(listId);
        todo.setTodoList(todoList);
        todoList.getTodos().add(todo);
        todoListsManager.updateTodoList(todoList);
    }

    /**
     * Delete a Todo.
     *
     * @see tudu.service.TodosManager#deleteTodo(java.lang.String)
     */
    public void deleteTodo(final String todoId) {
        Todo todo = this.findTodo(todoId);
        TodoList todoList = todo.getTodoList();
        todoList.getTodos().remove(todo);
        em.remove(todo);
        todoListsManager.updateTodoList(todoList);
    }

    /**
     * @see tudu.service.TodosManager#deleteAllCompletedTodos(java.lang.String)
     */
    public void deleteAllCompletedTodos(String listId) {
        TodoList todoList = todoListsManager.findTodoList(listId);
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
        todoListsManager.updateTodoList(todoList);
    }

    /**
     * @see tudu.service.TodosManager#completeTodo(java.lang.String)
     */
    public Todo completeTodo(String todoId) {
        Todo todo = this.findTodo(todoId);
        todo.setCompleted(true);
        todo.setCompletionDate(Calendar.getInstance().getTime());
        todoListsManager.updateTodoList(todo.getTodoList());
        return todo;
    }

    /**
     * @see tudu.service.TodosManager#reopenTodo(java.lang.String)
     */
    public Todo reopenTodo(String todoId) {
        Todo todo = this.findTodo(todoId);
        todo.setCompleted(false);
        todo.setCompletionDate(null);
        todoListsManager.updateTodoList(todo.getTodoList());
        return todo;
    }
}
