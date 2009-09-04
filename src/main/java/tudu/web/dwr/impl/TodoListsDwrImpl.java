package tudu.web.dwr.impl;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Component;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.TodoListsManager;
import tudu.service.UserManager;
import tudu.web.dwr.TodoListsDwr;
import tudu.web.dwr.bean.RemoteTodoList;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Implementation of the tudu.service.TodoListsManager interface.
 * 
 * @author Julien Dubois
 */
@Component("todoListsDwr")
public class TodoListsDwrImpl implements TodoListsDwr {

    private final Log log = LogFactory.getLog(TodoListsDwrImpl.class);

    @Autowired
    private TodoListsManager todoListsManager;

    @Autowired
    private UserManager userManager;

    /**
     * @see tudu.web.dwr.TodoListsDwr#getTodoList(java.lang.String)
     */
    public RemoteTodoList getTodoList(String listId) {
        RemoteTodoList remoteTodoList = new RemoteTodoList();
        try {
            TodoList todoList = todoListsManager.findTodoList(listId);
            remoteTodoList.setListId(todoList.getListId());
            String unescapedName = StringEscapeUtils.unescapeHtml(todoList
                    .getName());
            remoteTodoList.setName(unescapedName);
            remoteTodoList.setRssAllowed(todoList.isRssAllowed());
        } catch (Throwable t) {
            remoteTodoList.setListId(null);
        }
        return remoteTodoList;
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#getTodoListUsers(java.lang.String)
     */
    public String[] getTodoListUsers(String listId) {
        try {
            TodoList todoList = todoListsManager.findTodoList(listId);
            String currentLogin = userManager.getCurrentUser().getLogin();
            Collection<User> users = todoList.getUsers();
            Collection<String> logins = new TreeSet<String>();
            for (User user : users) {
                if (!currentLogin.equals(user.getLogin())) {
                    logins.add(user.getLogin());
                }
            }
            return logins.toArray(new String[logins.size()]);
        } catch (Throwable t) {
            return new String[0];
        }
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#addTodoListUser(java.lang.String,
     *      java.lang.String)
     */
    public String addTodoListUser(String listId, String login) {
        login = login.toLowerCase();
        try {
            todoListsManager.addTodoListUser(listId, login);
        } catch (ObjectRetrievalFailureException orfe) {
            return "ObjectRetrievalFailureException";
        }
        return "";
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#deleteTodoListUser(java.lang.String,
     *      java.lang.String)
     */
    public void deleteTodoListUser(String listId, String login) {
        login = login.toLowerCase();
        todoListsManager.deleteTodoListUser(listId, login);
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#addTodoList(java.lang.String,
     *      java.lang.String)
     */
    public void addTodoList(String name, String rssAllowed) {
        boolean rssAllowedBool = false;
        if (rssAllowed != null && rssAllowed.equals("1")) {
            rssAllowedBool = true;
        }
        TodoList todoList = new TodoList();
        String escapedName = StringEscapeUtils.escapeHtml(name);
        todoList.setName(escapedName);
        todoList.setRssAllowed(rssAllowedBool);
        todoListsManager.createTodoList(todoList);
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#editTodoList(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void editTodoList(String listId, String name, String rssAllowed) {
        TodoList todoList = todoListsManager.findTodoList(listId);
        if (name != null && !name.equals("")) {
            String escapedName = StringEscapeUtils.escapeHtml(name);
            todoList.setName(escapedName);
        }
        if (rssAllowed != null && !rssAllowed.equals("")) {
            boolean rssAllowedBool = false;
            if (rssAllowed.equals("1")) {
                rssAllowedBool = true;
            }
            todoList.setRssAllowed(rssAllowedBool);
        }
        todoListsManager.updateTodoList(todoList);
    }

    /**
     * @see tudu.web.dwr.TodoListsDwr#deleteTodoList(java.lang.String)
     */
    public void deleteTodoList(String listId) {
        todoListsManager.deleteTodoList(listId);
    }
}
