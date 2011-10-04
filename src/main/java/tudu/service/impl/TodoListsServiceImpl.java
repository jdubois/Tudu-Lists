package tudu.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tudu.domain.Todo;
import tudu.domain.TodoList;
import tudu.domain.User;
import tudu.security.PermissionDeniedException;
import tudu.service.TodoListsService;
import tudu.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the tudu.service.TodoListsService interface.
 *
 * @author Julien Dubois
 */
@Service
@Transactional
public class TodoListsServiceImpl implements TodoListsService {

    private final Log log = LogFactory.getLog(TodoListsServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserService userService;

    /**
     * Create a new Todo List.
     *
     * @see tudu.service.TodoListsService#createTodoList(tudu.domain.TodoList)
     */
    public void createTodoList(final TodoList todoList) {
        if (log.isDebugEnabled()) {
            log.debug("Creating a new Todo List with name "
                    + todoList.getName());
        }
        todoList.setLastUpdate(Calendar.getInstance().getTime());
        User user = userService.getCurrentUser();
        todoList.getUsers().add(user);
        em.persist(todoList);
        user.getTodoLists().add(todoList);
    }

    /**
     * @see tudu.service.TodoListsService#findTodoList(java.lang.String)
     */
    @Transactional(readOnly = true)
    public TodoList findTodoList(String listId) {
        TodoList todoList = em.find(TodoList.class, listId);
        if (todoList == null) {
            throw new ObjectRetrievalFailureException(TodoList.class, listId);
        }
        User user = userService.getCurrentUser();
        if (!user.getTodoLists().contains(todoList)) {
            if (log.isInfoEnabled()) {
                log.info("Permission denied when finding Todo List ID '"
                        + listId + "' for User '" + user.getLogin() + "'");
            }
            throw new PermissionDeniedException(
                    "Permission denied to access this Todo List.");
        }
        return todoList;
    }

    /**
     * @see tudu.service.TodoListsService#unsecuredFindTodoList(java.lang.String)
     */
    @Transactional(readOnly = true)
    public TodoList unsecuredFindTodoList(String listId) {
        TodoList todoList = em.find(TodoList.class, listId);
        Hibernate.initialize(todoList.getTodos());
        return todoList;
    }

    /**
     * Updates the Todo List last update date.
     *
     * @see tudu.service.TodoListsService#updateTodoList(tudu.domain.TodoList)
     */
    public void updateTodoList(final TodoList todoList) {
        todoList.setLastUpdate(Calendar.getInstance().getTime());
    }

    /**
     * Delete a Todo List.
     *
     * @see tudu.service.TodoListsService#deleteTodoList(java.lang.String)
     */
    public void deleteTodoList(final String listId) {
        TodoList todoList = this.findTodoList(listId);
        for (User user : todoList.getUsers()) {
            user.getTodoLists().remove(todoList);
        }
        for (Todo todo : todoList.getTodos()) {
            em.remove(todo);
        }
        em.remove(todoList);
    }

    /**
     * @see tudu.service.TodoListsService#addTodoListUser(java.lang.String,
     *      java.lang.String)
     */
    public void addTodoListUser(String listId, String login) {
        TodoList todoList = this.findTodoList(listId);
        User targetUser = userService.findUser(login);
        todoList.getUsers().add(targetUser);
        targetUser.getTodoLists().add(todoList);
        this.updateTodoList(todoList);
    }

    /**
     * @see tudu.service.TodoListsService#deleteTodoListUser(java.lang.String,
     *      java.lang.String)
     */
    public void deleteTodoListUser(String listId, String login) {
        TodoList todoList = this.findTodoList(listId);
        User targetUser = userService.findUser(login);
        for (Todo todo : todoList.getTodos()) {
            if (todo.getAssignedUser() != null
                    && todo.getAssignedUser().equals(targetUser)) {

                todo.setAssignedUser(null);
            }
        }
        todoList.getUsers().remove(targetUser);
        targetUser.getTodoLists().remove(todoList);
        this.updateTodoList(todoList);
    }

    public Document backupTodoList(String listId) {
        TodoList todoList = findTodoList(listId);
        Document doc = new Document();

        Element todoListElement = new Element("todolist");
        todoListElement.addContent(new Element("title").addContent(todoList
                .getName()));

        todoListElement.addContent(new Element("rss").addContent(String
                .valueOf(todoList.isRssAllowed())));

        Element todosElement = new Element("todos");
        for (Todo todo : todoList.getTodos()) {
            Element todoElement = new Element("todo");
            todoElement.setAttribute("id", todo.getTodoId());
            todoElement.addContent(new Element("creationDate").addContent(Long
                    .toString(todo.getCreationDate().getTime())));

            todoElement.addContent(new Element("description").addContent(todo
                    .getDescription()));

            todoElement.addContent(new Element("priority").addContent(Integer
                    .toString(todo.getPriority())));

            if (todo.getDueDate() != null) {
                todoElement.addContent(new Element("dueDate").addContent(Long
                        .toString(todo.getDueDate().getTime())));
            }
            todoElement.addContent(new Element("completed").addContent(Boolean
                    .toString(todo.isCompleted())));

            if (todo.isCompleted() && todo.getCompletionDate() != null) {
                todoElement.addContent(new Element("completionDate")
                        .addContent(Long.toString(todo.getCompletionDate()
                        .getTime())));
            }

            todoElement.addContent(new Element("notes").addContent(todo
                    .getNotes()));

            todosElement.addContent(todoElement);
        }
        todoListElement.addContent(todosElement);

        doc.addContent(todoListElement);

        return doc;
    }

    /**
     * @see tudu.service.TodoListsService#restoreTodoList(java.lang.String,
     *      java.lang.String, java.io.InputStream)
     */
    public void restoreTodoList(String restoreChoice, String listId,
                                InputStream todoListContent) throws JDOMException, IOException {

        SAXBuilder saxBuilder = new SAXBuilder();
        Document doc = saxBuilder.build(todoListContent);
        Element rootElement = doc.getRootElement();
        String title = rootElement.getChildText("title");
        String rss = rootElement.getChildText("rss");
        if (restoreChoice.equals("create")) {
            TodoList todoList = new TodoList();
            todoList.setName(title);
            todoList.setRssAllowed(Boolean.parseBoolean(rss));
            this.createTodoList(todoList);
            importTodosFromXml(todoList, rootElement);
        } else if (restoreChoice.equals("replace")) {
            TodoList todoList = this.findTodoList(listId);
            for (Todo todo : todoList.getTodos()) {
                em.remove(todo);
            }
            todoList.getTodos().clear();
            todoList.setName(title);
            todoList.setRssAllowed(Boolean.parseBoolean(rss));
            importTodosFromXml(todoList, rootElement);
            this.updateTodoList(todoList);
        } else if (restoreChoice.equals("merge")) {
            TodoList todoList = this.findTodoList(listId);
            importTodosFromXml(todoList, rootElement);
            this.updateTodoList(todoList);
        } else {
            log.error("Wrong choice of RestoreController option");
        }
    }

    /**
     * Import Todos from a JDOM document.
     *
     * @param todoList    The current Todo List
     * @param rootElement The root element of the JDOM document.
     */
    @SuppressWarnings("unchecked")
    private void importTodosFromXml(TodoList todoList, Element rootElement) {
        Element todosElement = rootElement.getChild("todos");
        List todos = todosElement.getChildren();
        for (Object todoObject : todos) {
            Element todoElement = (Element) todoObject;
            Todo todo = new Todo();
            Date creationDate = new Date(Long.parseLong(todoElement
                    .getChildText("creationDate")));
            todo.setCreationDate(creationDate);
            todo.setDescription(todoElement.getChildText("description"));
            todo.setPriority(Integer.valueOf(todoElement
                    .getChildText("priority")));
            todo.setCompleted(Boolean.parseBoolean(todoElement
                    .getChildText("completed")));
            String completionDate = todoElement.getChildText("completionDate");
            if (completionDate != null) {
                todo
                        .setCompletionDate(new Date(Long
                                .parseLong(completionDate)));
            }
            String dueDate = todoElement.getChildText("dueDate");
            if (dueDate != null) {
                todo.setDueDate(new Date(Long.parseLong(dueDate)));
            }
            String notes = todoElement.getChildText("notes");
            if (notes == null || notes.length() == 0) {
                todo.setHasNotes(false);
            } else {
                todo.setNotes(notes);
                todo.setHasNotes(true);
            }
            todo.setTodoList(todoList);
            todoList.getTodos().add(todo);
            em.persist(todo);
        }
    }
}
