package tudu.domain.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import tudu.domain.dao.TodoListDAO;
import tudu.domain.model.TodoList;

/**
 * Hibernate implementation of the tudu.domain.dao.TodoListDAO interface.
 * 
 * @author Julien Dubois
 */
@Repository
public class TodoListDAOJpa implements TodoListDAO {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Find a Todo List by ID.
     * 
     * @see tudu.domain.dao.TodoListDAO#getTodoList(String)
     */
    public final TodoList getTodoList(final String listId) {
        TodoList todoList = this.em.find(TodoList.class, listId);
        if (todoList == null) {
            throw new ObjectRetrievalFailureException(TodoList.class, listId);
        }
        return todoList;
    }

    /**
     * Save a Todo List.
     * 
     * @see tudu.domain.dao.TodoListDAO#saveTodoList(tudu.domain.model.TodoList)
     */
    public final void saveTodoList(final TodoList todoList) {
        this.em.persist(todoList);
    }

    /**
     * Update a Todo List.
     * 
     * @see tudu.domain.dao.TodoListDAO#updateTodoList(tudu.domain.model.TodoList)
     */
    public final void updateTodoList(final TodoList todoList) {
        this.em.merge(todoList);
    }

    /**
     * Delete a Todo List by ID.
     * 
     * @see tudu.domain.dao.TodoListDAO#removeTodoList(String)
     */
    public final void removeTodoList(final String listId) {
        TodoList todoList = getTodoList(listId);
        this.em.remove(todoList);
    }
}
