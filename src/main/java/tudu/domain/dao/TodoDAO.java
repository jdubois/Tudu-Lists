package tudu.domain.dao;

import tudu.domain.model.Todo;

/**
 * DAO for the Todo table.
 * 
 * @author Julien Dubois
 */
public interface TodoDAO {

    /**
     * Find a Todo by ID.
     * 
     * @param todoId
     *            The Todo Id
     * @return The Todo
     */
    Todo getTodo(String todoId);

    /**
     * Update a Todo.
     * 
     * @param todo
     *            The Todo
     */
    void updateTodo(Todo todo);

    /**
     * Save a Todo.
     * 
     * @param todo
     *            The Todo
     */
    void saveTodo(Todo todo);

    /**
     * Delete a Todo.
     * 
     * @param todoId
     *            The Todo ID
     */
    void removeTodo(String todoId);
}
