package tudu.web.dwr.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import tudu.domain.TodoList;
import tudu.domain.User;
import tudu.service.TodoListsService;
import tudu.service.UserService;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class TodoListsDwrImplTest {

    TodoList todoList = new TodoList();

    TodoListsService todoListsService = null;
    UserService userService = null;

    TodoListsDwrImpl todoListsDwr = new TodoListsDwrImpl();

    @Before
    public void before() {
        todoList.setListId("001");
        todoList.setName("Description");
        todoList.setRssAllowed(false);

        todoListsService = createMock(TodoListsService.class);

        userService = createMock(UserService.class);

        ReflectionTestUtils.setField(todoListsDwr, "todoListsService", todoListsService);
        ReflectionTestUtils.setField(todoListsDwr, "userService", userService);
    }

    @After
    public void after() {
        verify(todoListsService);
        verify(userService);
    }

    private void replay_() {
        replay(todoListsService);
        replay(userService);
    }

    @Test
    public void testGetTodoListName() {
        expect(todoListsService.findTodoList("001")).andReturn(todoList);

        replay_();

        String name = todoListsDwr.getTodoList("001").getName();
        assertEquals("Description", name);
    }

    @Test
    public void testGetTodoListRss() {
        todoList.setRssAllowed(true);
        expect(todoListsService.findTodoList("001")).andReturn(todoList);

        replay_();

        boolean rss = todoListsDwr.getTodoList("001").isRssAllowed();
        assertEquals(true, rss);
    }

    @Test
    public void testGetTodoListRss2() {
        todoList.setRssAllowed(false);
        expect(todoListsService.findTodoList("001")).andReturn(todoList);

        replay_();

        boolean rss = todoListsDwr.getTodoList("001").isRssAllowed();
        assertEquals(false, rss);
    }

    @Test
    public void testGetTodoListUsers() {
        User user = new User();
        user.setLogin("test_user");
        expect(userService.getCurrentUser()).andReturn(user);

        todoList.getUsers().add(user);

        User user1 = new User();
        user1.setLogin("BBB");
        todoList.getUsers().add(user1);

        User user2 = new User();
        user2.setLogin("AAA");
        todoList.getUsers().add(user2);

        User user3 = new User();
        user3.setLogin("CCC");
        todoList.getUsers().add(user3);

        expect(todoListsService.findTodoList("001")).andReturn(todoList);

        replay_();

        String[] logins = todoListsDwr.getTodoListUsers("001");

        assertEquals(3, logins.length);
        assertEquals("AAA", logins[0]);
        assertEquals("BBB", logins[1]);
        assertEquals("CCC", logins[2]);
    }

    @Test
    public void testAddTodoListUser() {
        todoListsService.addTodoListUser("001", "test_user");

        replay_();

        todoListsDwr.addTodoListUser("001", "test_user");
    }

    @Test
    public void testDeleteTodoListUser() {
        todoListsService.deleteTodoListUser("001", "test_user");

        replay_();

        todoListsDwr.deleteTodoListUser("001", "test_user");
    }

    @Test
    public void testEditTodoList() {
        expect(todoListsService.findTodoList("001")).andReturn(todoList);
        todoListsService.updateTodoList(todoList);

        replay_();

        todoListsDwr.editTodoList("001", "edit name", "1");

        assertEquals("edit name", todoList.getName());
    }
}
