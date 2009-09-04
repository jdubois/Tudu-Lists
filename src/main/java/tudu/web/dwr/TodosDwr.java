package tudu.web.dwr;

import tudu.web.dwr.bean.RemoteTodo;
import tudu.web.dwr.bean.RemoteTodoList;

import java.util.Date;

/**
 * Todos actions presented with DWR.
 * 
 * @author Julien Dubois
 */
public interface TodosDwr {

    /**
     * Get an array containing the user's Todo Lists.
     * <p>
     * If no data has changed after the last rendering date (the menuDate
     * parameter), this method will return null.
     * </p>
     * 
     * @param menuDate
     *            The date when the actual lists where rendered
     */
    RemoteTodoList[] getCurrentTodoLists(Date menuDate);
    
    /**
     * Get an array containing the user's Todo Lists..
     */
    RemoteTodoList[] forceGetCurrentTodoLists();

    /**
     * Get a Todo by ID.
     * 
     * @param todoId
     *            The Todo ID
     * @return The Todo
     */
    RemoteTodo getTodoById(String todoId);

    /**
     * Render the Todo List.
     * <p>
     * If no data has changed after the last rendering date (the tableDate
     * parameter), this method will return an empty String.
     * </p>
     * 
     * @param listId
     *            The List ID
     * @param tableDate
     *            The date when the actual todos where rendered
     * @return The HTML list, or an empty String
     */
    String renderTodos(String listId, Date tableDate);

    /**
     * Render a list of all Todos due in the next 4 days.
     * 
     * @return The HTML list, or an empty String
     */
    String renderNextDays();

    /**
     * Render a list of all Todos assigned to the current user.
     * 
     * @return The HTML list, or an empty String
     */
    String renderAssignedToMe();

    /**
     * Force the rendering of the Todo List.
     * 
     * @param listId
     *            The List ID
     * @return The HTML list
     */
    String forceRenderTodos(String listId);

    /**
     * Sort and render the Todo List.
     * 
     * @param listId
     *            The List ID
     * @param sorter
     *            The element used to sort the list
     * @return The HTML list
     */
    String sortAndRenderTodos(String listId, String sorter);

    /**
     * Add a new Todo.
     * 
     * @param listId
     *            The list ID
     * @param description
     *            The description
     * @param priority
     *            The priority
     * @param dueDate
     *            The due date
     * @param notes
     *            The notes
     * @param assignedUserLogin
     *            The login of the user the todo is assigned to
     * @return The HTML list
     */
    String addTodo(String listId, String description, String priority,
            String dueDate, String notes, String assignedUserLogin);

    /**
     * Re open a Todo.
     * 
     * @param todoId
     *            The todo ID
     * @return The HTML list
     */
    String reopenTodo(String todoId);

    /**
     * Complete a Todo.
     * 
     * @param todoId
     *            The todo ID
     * @return The HTML list
     */
    String completeTodo(String todoId);

    /**
     * Edit a Todo.
     * 
     * @param todoId
     *            The Todo ID
     * @param description
     *            The description
     * @param priority
     *            The priority
     * @param dueDate
     *            The due date
     * @param notes
     *            The notes
     * @param assignedUserLogin
     *            The login of the user the todo is assigned to
     * @return The HTML list
     */
    String editTodo(String todoId, String description, String priority,
            String dueDate, String notes, String assignedUserLogin);
    
    /**
     * Edit a Todo.
     * 
     * @param todoId
     *            The Todo ID
     * @param description
     *            The description
     * @return The HTML list
     */
    String quickEditTodo(String todoId, String description);

    /**
     * Delete a Todo.
     * 
     * @param todoId
     *            The todo ID
     * @return The HTML list
     */
    String deleteTodo(String todoId);

    /**
     * Delete all the completed Todos of a given list.
     * 
     * @param listId
     *            The list ID
     * @return The HTML list
     */
    String deleteAllCompletedTodos(String listId);

    /**
     * Show the older Todos of a given list.
     * 
     * @param listId
     *            The list ID
     * @return The HTML list
     */
    String showOlderTodos(String listId);

    /**
     * Hide the older Todos of a given list.
     * 
     * @param listId
     *            The list ID
     * @return The HTML list
     */
    String hideOlderTodos(String listId);
}
