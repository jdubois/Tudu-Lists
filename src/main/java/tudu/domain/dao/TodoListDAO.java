package tudu.domain.dao;

import tudu.domain.model.TodoList;

/**
 * DAO for the TodoList table.
 * 
 * @author Julien Dubois
 */
public interface TodoListDAO {

    /**
     * Find a Todo List by ID.
     * 
     * @param listId
     *            The Todo List ID
     * @return The Todo List
     */
    TodoList getTodoList(String listId);

    /**
     * Save a Todo List.
     * 
     * @param todoList
     *            The Todo List to save
     */
    void saveTodoList(TodoList todoList);

    /**
     * Update a Todo List.
     * 
     * @param todoList
     *            The Todo List to update
     */
    void updateTodoList(TodoList todoList);

    /**
     * Delete a Todo List by ID.
     * 
     * @param listId
     *            The Todo List ID
     */
    void removeTodoList(String listId);
}
