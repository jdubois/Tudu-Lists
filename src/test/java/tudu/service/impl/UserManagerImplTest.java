package tudu.service.impl;

import static org.easymock.EasyMock.*;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import tudu.domain.RolesEnum;
import tudu.domain.dao.RoleDAO;
import tudu.domain.dao.TodoDAO;
import tudu.domain.dao.TodoListDAO;
import tudu.domain.dao.UserDAO;
import tudu.domain.model.Role;
import tudu.domain.model.Todo;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;

public class UserManagerImplTest {

    User user = new User();

    UserDAO userDAO = null;
    RoleDAO roleDAO = null;
    TodoListDAO todoListDAO = null;
    TodoDAO todoDAO = null;

    UserManagerImpl userManager = new UserManagerImpl();

    @Before
    public void before() {
        user.setLogin("test_user");
        user.setFirstName("First name");
        user.setLastName("Last name");

        userDAO = createMock(UserDAO.class);
        roleDAO = createMock(RoleDAO.class);
        todoListDAO = createMock(TodoListDAO.class);
        todoDAO = createMock(TodoDAO.class);

        ReflectionTestUtils.setField(userManager, "userDAO", userDAO);
        ReflectionTestUtils.setField(userManager, "roleDAO", roleDAO);
        ReflectionTestUtils.setField(userManager, "todoListDAO", todoListDAO);
        ReflectionTestUtils.setField(userManager, "todoDAO", todoDAO);

    }

    @After
    public void after() {
        verify(userDAO);
        verify(roleDAO);
        verify(todoListDAO);
        verify(todoDAO);
    }

    private void replay_() {
        replay(userDAO);
        replay(roleDAO);
        replay(todoListDAO);
        replay(todoDAO);
    }

    @Test
    public void testFindUser() {
        expect(userDAO.getUser("test_user")).andReturn(user);

        replay_();

        User testUser = userManager.findUser("test_user");
        assertEquals(testUser, user);
    }

    @Test
    public void testUpdateUser() {
        userDAO.updateUser(user);

        replay_();

        userManager.updateUser(user);
    }

    @Test
    public void testCreateUser() {
        expect(userDAO.getUser("test_user")).andReturn(null);

        Role role = new Role();
        role.setRole(RolesEnum.ROLE_USER.toString());
        expect(roleDAO.getRole(RolesEnum.ROLE_USER.toString())).andReturn(role);

        userDAO.saveUser(user);

        TodoList todoList = new TodoList();
        todoListDAO.saveTodoList(todoList);

        Todo todo = new Todo();
        todoDAO.saveTodo(todo);
        todoListDAO.updateTodoList(todoList);

        replay_();

        try {
            userManager.createUser(user);
            assertTrue(user.isEnabled());
            assertNotNull(user.getCreationDate());
            assertNotNull(user.getLastAccessDate());
            assertEquals(1, user.getRoles().size());
            Role testRole = user.getRoles().iterator().next();
            assertEquals(RolesEnum.ROLE_USER.toString(), testRole.getRole());
            assertEquals(1, user.getTodoLists().size());
            TodoList testTodoList = user.getTodoLists().iterator().next();
            assertNotNull(testTodoList.getLastUpdate());
            assertEquals(1, testTodoList.getTodos().size());
        } catch (UserAlreadyExistsException e) {
            fail("A UserAlreadyExistsException should not have been thrown.");
        }
    }

    @Test
    public void testFailedCreateUser() {
        expect(userDAO.getUser("test_user")).andReturn(user);

        replay_();

        try {
            userManager.createUser(user);
            fail("A UserAlreadyExistsException should have been thrown.");
        } catch (UserAlreadyExistsException e) {

        }
    }
}
