package tudu.service.impl;

import org.junit.Before;
import org.junit.Test;
import tudu.domain.*;
import tudu.service.UserAlreadyExistsException;

import static org.junit.Assert.*;

public class UserManagerImplTest {

    User user = new User();

    UserManagerImpl userManager = new UserManagerImpl();

    @Before
    public void before() {
        user.setLogin("test_user");
        user.setFirstName("First name");
        user.setLastName("Last name");
    }

    @Test
    public void testFindUser() {
        //expect(userDAO.getUser("test_user")).andReturn(user);

        User testUser = userManager.findUser("test_user");
        assertEquals(testUser, user);
    }

    @Test
    public void testUpdateUser() {
        //userDAO.updateUser(user);


        userManager.updateUser(user);
    }

    @Test
    public void testCreateUser() {
        //expect(userDAO.getUser("test_user")).andReturn(null);

        Role role = new Role();
        role.setRole(RolesEnum.ROLE_USER.toString());
        //expect(roleDAO.getRole(RolesEnum.ROLE_USER.toString())).andReturn(role);

        //userDAO.saveUser(user);

        TodoList todoList = new TodoList();
        //todoListDAO.saveTodoList(todoList);

        Todo todo = new Todo();
        //todoDAO.saveTodo(todo);
        //todoListDAO.updateTodoList(todoList);


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
        //expect(userDAO.getUser("test_user")).andReturn(user);


        try {
            userManager.createUser(user);
            fail("A UserAlreadyExistsException should have been thrown.");
        } catch (UserAlreadyExistsException e) {

        }
    }
}
