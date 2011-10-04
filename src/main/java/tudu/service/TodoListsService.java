package tudu.service;

import org.jdom.Document;
import org.jdom.JDOMException;
import tudu.domain.TodoList;

import java.io.IOException;
import java.io.InputStream;

/**
 * Manage Todo Lists.
 * 
 * @author Julien Dubois
 */
public interface TodoListsService {

    /**
     * Create a new Todo List.
     * 
     * @param todoList
     *            The Todo List to create
     */
    void createTodoList(TodoList todoList);

    /**
     * Find a Todo List by ID.
     * 
     * @param listId
     *            The Todo List ID
     * @return The Todo List
     */
    TodoList findTodoList(String listId);

    /**
     * Find a Todo List by ID, without any security check.
     * <p>
     * This method is used for the RSS feed, which is not secured.
     * </p>
     * 
     * @param listId
     *            The Todo List ID
     * @return The Todo List
     */
    TodoList unsecuredFindTodoList(String listId);

    /**
     * Update a Todo List.
     * 
     * @param todoList
     *            The Todo List to update
     */
    void updateTodoList(TodoList todoList);

    /**
     * Delete a Todo List.
     * 
     * @param listId
     *            The ID of the Todo List to delete
     */
    void deleteTodoList(String listId);

    /**
     * Add a user to a Todo List.
     * 
     * @param listId
     *            The ID of the Todo List
     * @param login
     *            The user login
     */
    void addTodoListUser(String listId, String login);

    /**
     * Delete a user from Todo List.
     * 
     * @param listId
     *            The ID of the Todo List
     * @param login
     *            The user login
     */
    void deleteTodoListUser(String listId, String login);

    /**
     * Backup a Todo List.
     * 
     * @param todoList
     *            The Todo List
     * @return A JDOM document ready for backup
     */
    Document backupTodoList(String listId);

    /**
     * Restore a Todo List.
     * 
     * @param restoreChoice
     *            The type of RestoreController (create, replace or merge)
     * @param listId
     *            The Todo List ID
     * @param todoListContent
     *            The content to RestoreController
     */
    void restoreTodoList(String restoreChoice, String listId,
            InputStream todoListContent) throws JDOMException, IOException;

}
