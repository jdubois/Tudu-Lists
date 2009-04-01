package tudu.domain.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import tudu.domain.dao.TodoDAO;
import tudu.domain.model.Todo;

/**
 * Hibernate implementation of the tudu.domain.dao.TodoDAO interface.
 * 
 * @author Julien Dubois
 */
@Repository
public class TodoDAOJpa implements TodoDAO {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Find a Todo by ID.
     * 
     * @see tudu.domain.dao.TodoDAO#getTodo(String)
     */
    public Todo getTodo(String todoId) {
        Todo todo = this.em.find(Todo.class, todoId);
        if (todo == null) {
            throw new ObjectRetrievalFailureException(Todo.class, todoId);
        }
        return todo;
    }

    /**
     * Update a Todo.
     * 
     * @see tudu.domain.dao.TodoDAO#updateTodo(tudu.domain.model.Todo)
     */
    public void updateTodo(Todo todo) {
        this.em.merge(todo);
    }

    /**
     * Save a Todo.
     * 
     * @see tudu.domain.dao.TodoDAO#saveTodo(tudu.domain.model.Todo)
     */
    public void saveTodo(Todo todo) {
        this.em.persist(todo);
    }

    /**
     * Delete a Todo.
     * 
     * @see tudu.domain.dao.TodoDAO#removeTodo(String)
     */
    public void removeTodo(String todoId) {
        this.em.remove(getTodo(todoId));
    }
}
