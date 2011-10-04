package tudu.service.impl;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import tudu.domain.*;
import tudu.service.UserAlreadyExistsException;

import javax.persistence.EntityManager;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

public class UserServiceImplTest {

    User user = new User();

    UserServiceImpl userService = new UserServiceImpl();

    EntityManager em = null;

    @Before
    public void before() {
        user.setLogin("test_user");
        user.setFirstName("First name");
        user.setLastName("Last name");

        em = createMock(EntityManager.class);

        ReflectionTestUtils.setField(userService, "em", em);
    }

    @After
    public void after() {
        verify(em);
    }

    private void replay() {
        EasyMock.replay(em);
    }

    @Test
    public void testFindUser() {
        expect(em.find(User.class, "test_user")).andReturn(user);

        replay();

        User testUser = userService.findUser("test_user");
        assertEquals(testUser, user);
    }

    @Test
    public void testUpdateUser() {
        expect(em.merge(user)).andReturn(null);
        replay();
        userService.updateUser(user);
    }

    @Test
    public void testCreateUser() {
        expect(em.find(User.class, "test_user")).andReturn(null);
        Role role = new Role();
        role.setRole(RolesEnum.ROLE_USER.name());
        expect(em.find(Role.class, RolesEnum.ROLE_USER.name())).andReturn(role);
        em.persist(user);
        TodoList todoList = new TodoList();
        Todo todo = new Todo();
        em.persist(todo);
        em.persist(todoList);

        replay();

        try {
            userService.createUser(user);
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
        expect(em.find(User.class, "test_user")).andReturn(user);

        replay();

        try {
            userService.createUser(user);
            fail("A UserAlreadyExistsException should have been thrown.");
        } catch (UserAlreadyExistsException e) {
            assertNotNull(user);
        }
    }
}
