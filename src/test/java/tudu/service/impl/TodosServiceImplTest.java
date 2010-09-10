package tudu.service.impl;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import tudu.domain.Todo;
import tudu.domain.TodoList;
import tudu.domain.User;
import tudu.security.PermissionDeniedException;
import tudu.service.TodoListsService;
import tudu.service.UserService;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class TodosServiceImplTest {

    Todo todo = new Todo();
    TodoList todoList = new TodoList();
    User user = new User();

    //TodoDAO todoDAO = null;
    TodoListsService todoListsService = null;
    UserService userService = null;

    TodosServiceImpl todosService = new TodosServiceImpl();

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

        //todoDAO = createMock(TodoDAO.class);
        todoListsService = createMock(TodoListsService.class);
        userService = createMock(UserService.class);

        //ReflectionTestUtils.setField(todosService, "todoDAO", todoDAO);
        ReflectionTestUtils.setField(todosService, "todoListsService", todoListsService);
        ReflectionTestUtils.setField(todosService, "userService", userService);
    }

    @After
    public void tearDown() {
        //verify(todoDAO);
        verify(todoListsService);
        verify(userService);
    }

    private void replay() {
        //EasyMock.replay(todoDAO);
        EasyMock.replay(todoListsService);
        EasyMock.replay(userService);
    }

    @Test
    public void testFindTodo() {
        todo.setTodoList(todoList);
        //expect(todoDAO.getTodo("0001")).andReturn(todo);

        user.getTodoLists().add(todoList);
        expect(userService.getCurrentUser()).andReturn(user);

        replay();

        try {
            Todo testTodo = todosService.findTodo("0001");
            assertEquals(todo, testTodo);
        } catch (PermissionDeniedException pde) {
            fail("Permission denied when looking for Todo.");
        }
    }

    @Test
    public void testFailedFindTodo() {
        //expect(todoDAO.getTodo("0001")).andReturn(todo);

        expect(userService.getCurrentUser()).andReturn(user);

        replay();

        try {
            todosService.findTodo("0001");
            fail("A PermissionDeniedException should have been thrown");
        } catch (PermissionDeniedException pde) {

        }
    }

    @Test
    public void testCreateTodo() {
        expect(todoListsService.findTodoList("001")).andReturn(todoList);

        //todoDAO.saveTodo(todo);

        todoListsService.updateTodoList(todoList);

        replay();

        todosService.createTodo("001", todo);

        assertNotNull(todo.getCreationDate());
        assertEquals(todoList, todo.getTodoList());
        assertTrue(todoList.getTodos().contains(todo));
    }

    @Test
    public void testUpdateTodo() {
        //todoDAO.updateTodo(todo);
        todoListsService.updateTodoList(todo.getTodoList());

        replay();

        todo.setCompleted(true);
        //todosService.updateTodo(todo);
        assertTrue(todo.isCompleted());
    }

    @Test
    public void testDeleteTodo() {
        todo.setTodoList(todoList);
        todoList.getTodos().add(todo);
        //expect(todoDAO.getTodo("0001")).andReturn(todo);

        user.getTodoLists().add(todoList);
        expect(userService.getCurrentUser()).andReturn(user);

        //todoDAO.removeTodo("0001");
        todoListsService.updateTodoList(todo.getTodoList());

        replay();

        todosService.deleteTodo("0001");

        assertFalse(todoList.getTodos().contains(todo));
    }

    @Test
    public void testCompleteTodo() {
        todo.setTodoList(todoList);
        todoList.getTodos().add(todo);
        //expect(todoDAO.getTodo("0001")).andReturn(todo);

        user.getTodoLists().add(todoList);
        expect(userService.getCurrentUser()).andReturn(user);

        todoListsService.updateTodoList(todo.getTodoList());

        replay();

        Todo todo = todosService.completeTodo("0001");

        assertTrue(todo.isCompleted());
        assertNotNull(todo.getCompletionDate());
    }

    @Test
    public void testReopenTodo() {
        todo.setTodoList(todoList);
        todoList.getTodos().add(todo);
        //expect(todoDAO.getTodo("0001")).andReturn(todo);

        user.getTodoLists().add(todoList);
        expect(userService.getCurrentUser()).andReturn(user);

        todoListsService.updateTodoList(todo.getTodoList());

        replay();

        Todo todo = todosService.reopenTodo("0001");

        assertFalse(todo.isCompleted());
        assertNull(todo.getCompletionDate());
    }
}
