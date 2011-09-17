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

import javax.persistence.EntityManager;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class TodosServiceImplTest {

    Todo todo = new Todo();
    TodoList todoList = new TodoList();
    User user = new User();

    TodoListsService todoListsService = null;
    UserService userService = null;
    EntityManager em = null;

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

        em = createMock(EntityManager.class);
        todoListsService = createMock(TodoListsService.class);
        userService = createMock(UserService.class);

        ReflectionTestUtils.setField(todosService, "em", em);
        ReflectionTestUtils.setField(todosService, "todoListsService", todoListsService);
        ReflectionTestUtils.setField(todosService, "userService", userService);
    }

    @After
    public void tearDown() {
        verify(em);
        verify(todoListsService);
        verify(userService);
    }

    private void replay() {
        EasyMock.replay(em);
        EasyMock.replay(todoListsService);
        EasyMock.replay(userService);
    }

    @Test
    public void testFindTodo() {
        todo.setTodoList(todoList);
        expect(em.find(Todo.class, "0001")).andReturn(todo);
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
        expect(em.find(Todo.class, "0001")).andReturn(todo);
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
        todoListsService.updateTodoList(todoList);
        em.persist(todo);

        replay();

        todosService.createTodo("001", todo);

        assertNotNull(todo.getCreationDate());
        assertEquals(todoList, todo.getTodoList());
        assertTrue(todoList.getTodos().contains(todo));
    }

    @Test
    public void testUpdateTodo() {
        todoListsService.updateTodoList(todo.getTodoList());

        replay();

        todo.setCompleted(true);
        todosService.updateTodo(todo);
        assertTrue(todo.isCompleted());
    }

    @Test
    public void testDeleteTodo() {
        todo.setTodoList(todoList);
        todoList.getTodos().add(todo);
        user.getTodoLists().add(todoList);
        em.remove(todo);
        todoListsService.updateTodoList(todo.getTodoList());

        replay();

        todosService.deleteTodo(todo);

        assertFalse(todoList.getTodos().contains(todo));
    }

    @Test
    public void testCompleteTodo() {
        todo.setTodoList(todoList);
        todoList.getTodos().add(todo);
        expect(em.find(Todo.class, "0001")).andReturn(todo);

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
        expect(em.find(Todo.class, "0001")).andReturn(todo);

        user.getTodoLists().add(todoList);
        expect(userService.getCurrentUser()).andReturn(user);

        todoListsService.updateTodoList(todo.getTodoList());

        replay();

        Todo todo = todosService.reopenTodo("0001");

        assertFalse(todo.isCompleted());
        assertNull(todo.getCompletionDate());
    }
}
