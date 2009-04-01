package tudu.web.dwr.impl;

import static org.easymock.EasyMock.*;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.TodoListsManager;
import tudu.service.UserManager;

public class TodoListsDwrImplTest {

    TodoList todoList = new TodoList();

    TodoListsManager todoListsManager = null;
    UserManager userManager = null;

    TodoListsDwrImpl todoListsDwr = new TodoListsDwrImpl();

    @Before
    public void before() {
        todoList.setListId("001");
        todoList.setName("Description");
        todoList.setRssAllowed(false);

        todoListsManager = createMock(TodoListsManager.class);

        userManager = createMock(UserManager.class);

        ReflectionTestUtils.setField(todoListsDwr, "todoListsManager", todoListsManager);
        ReflectionTestUtils.setField(todoListsDwr, "userManager", userManager);
    }

    @After
    public void after() {
        verify(todoListsManager);
        verify(userManager);
    }

    private void replay_() {
        replay(todoListsManager);
        replay(userManager);
    }

    @Test
    public void testGetTodoListName() {
        expect(todoListsManager.findTodoList("001")).andReturn(todoList);

        replay_();

        String name = todoListsDwr.getTodoList("001").getName();
        assertEquals("Description", name);
    }

    @Test
    public void testGetTodoListRss() {
        todoList.setRssAllowed(true);
        expect(todoListsManager.findTodoList("001")).andReturn(todoList);

        replay_();

        boolean rss = todoListsDwr.getTodoList("001").isRssAllowed();
        assertEquals(true, rss);
    }

    @Test
    public void testGetTodoListRss2() {
        todoList.setRssAllowed(false);
        expect(todoListsManager.findTodoList("001")).andReturn(todoList);

        replay_();

        boolean rss = todoListsDwr.getTodoList("001").isRssAllowed();
        assertEquals(false, rss);
    }

    @Test
    public void testGetTodoListUsers() {
        User user = new User();
        user.setLogin("test_user");
        expect(userManager.getCurrentUser()).andReturn(user);

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

        expect(todoListsManager.findTodoList("001")).andReturn(todoList);

        replay_();

        String[] logins = todoListsDwr.getTodoListUsers("001");

        assertEquals(3, logins.length);
        assertEquals("AAA", logins[0]);
        assertEquals("BBB", logins[1]);
        assertEquals("CCC", logins[2]);
    }

    @Test
    public void testAddTodoListUser() {
        todoListsManager.addTodoListUser("001", "test_user");

        replay_();

        todoListsDwr.addTodoListUser("001", "test_user");
    }

    @Test
    public void testDeleteTodoListUser() {
        todoListsManager.deleteTodoListUser("001", "test_user");

        replay_();

        todoListsDwr.deleteTodoListUser("001", "test_user");
    }

    @Test
    public void testEditTodoList() {
        expect(todoListsManager.findTodoList("001")).andReturn(todoList);
        todoListsManager.updateTodoList(todoList);

        replay_();

        todoListsDwr.editTodoList("001", "edit name", "1");

        assertEquals("edit name", todoList.getName());
    }
}
