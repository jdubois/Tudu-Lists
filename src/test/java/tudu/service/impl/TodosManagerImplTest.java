package tudu.service.impl;

import static org.easymock.EasyMock.*;
import org.easymock.EasyMock;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import tudu.domain.dao.TodoDAO;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.security.PermissionDeniedException;
import tudu.service.TodoListsManager;
import tudu.service.UserManager;

public class TodosManagerImplTest {

    Todo todo = new Todo();
    TodoList todoList = new TodoList();
    User user = new User();

    TodoDAO todoDAO = null;
    TodoListsManager todoListsManager = null;
    UserManager userManager = null;

    TodosManagerImpl todosManager = new TodosManagerImpl();

    @Before
    public void setUp() {
        todo.setTodoId("0001");
        todo.setDescription("Test description");
        todo.setPriority(0);
        todo.setCompleted(false);

        todoList.setListId("001");
        todoList.setName("Test Todo List");
        todoList.setRssAllowed(false);

        user.setLogin("test_user");
        user.setFirstName("First name");
        user.setLastName("Last name");

        todoDAO = createMock(TodoDAO.class);
        todoListsManager = createMock(TodoListsManager.class);
        userManager = createMock(UserManager.class);

        ReflectionTestUtils.setField(todosManager, "todoDAO", todoDAO);
        ReflectionTestUtils.setField(todosManager, "todoListsManager", todoListsManager);
        ReflectionTestUtils.setField(todosManager, "userManager", userManager);
    }

    @After
    public void tearDown() {
        verify(todoDAO);
        verify(todoListsManager);
        verify(userManager);
    }

    private void replay() {
        EasyMock.replay(todoDAO);
        EasyMock.replay(todoListsManager);
        EasyMock.replay(userManager);
    }

    @Test
    public void testFindTodo() {
        todo.setTodoList(todoList);
        expect(todoDAO.getTodo("0001")).andReturn(todo);

        user.getTodoLists().add(todoList);
        expect(userManager.getCurrentUser()).andReturn(user);

        replay();

        try {
            Todo testTodo = todosManager.findTodo("0001");
            assertEquals(todo, testTodo);
        } catch (PermissionDeniedException pde) {
            fail("Permission denied when looking for Todo.");
        }
    }

    @Test
    public void testFailedFindTodo() {
        expect(todoDAO.getTodo("0001")).andReturn(todo);

        expect(userManager.getCurrentUser()).andReturn(user);

        replay();

        try {
            todosManager.findTodo("0001");
            fail("A PermissionDeniedException should have been thrown");
        } catch (PermissionDeniedException pde) {

        }
    }

    @Test
    public void testCreateTodo() {
        expect(todoListsManager.findTodoList("001")).andReturn(todoList);

        todoDAO.saveTodo(todo);

        todoListsManager.updateTodoList(todoList);

        replay();

        todosManager.createTodo("001", todo);

        assertNotNull(todo.getCreationDate());
        assertEquals(todoList, todo.getTodoList());
        assertTrue(todoList.getTodos().contains(todo));
    }

    @Test
    public void testUpdateTodo() {
        todoDAO.updateTodo(todo);
        todoListsManager.updateTodoList(todo.getTodoList());

        replay();

        todo.setCompleted(true);
        todosManager.updateTodo(todo);
        assertTrue(todo.isCompleted());
    }

    @Test
    public void testDeleteTodo() {
        todo.setTodoList(todoList);
        todoList.getTodos().add(todo);
        expect(todoDAO.getTodo("0001")).andReturn(todo);

        user.getTodoLists().add(todoList);
        expect(userManager.getCurrentUser()).andReturn(user);

        todoDAO.removeTodo("0001");
        todoListsManager.updateTodoList(todo.getTodoList());

        replay();

        todosManager.deleteTodo("0001");

        assertFalse(todoList.getTodos().contains(todo));
    }

    @Test
    public void testCompleteTodo() {
        todo.setTodoList(todoList);
        todoList.getTodos().add(todo);
        expect(todoDAO.getTodo("0001")).andReturn(todo);

        user.getTodoLists().add(todoList);
        expect(userManager.getCurrentUser()).andReturn(user);

        todoListsManager.updateTodoList(todo.getTodoList());

        replay();

        Todo todo = todosManager.completeTodo("0001");

        assertTrue(todo.isCompleted());
        assertNotNull(todo.getCompletionDate());
    }

    @Test
    public void testReopenTodo() {
        todo.setTodoList(todoList);
        todoList.getTodos().add(todo);
        expect(todoDAO.getTodo("0001")).andReturn(todo);

        user.getTodoLists().add(todoList);
        expect(userManager.getCurrentUser()).andReturn(user);

        todoListsManager.updateTodoList(todo.getTodoList());

        replay();

        Todo todo = todosManager.reopenTodo("0001");

        assertFalse(todo.isCompleted());
        assertNull(todo.getCompletionDate());
    }
}
