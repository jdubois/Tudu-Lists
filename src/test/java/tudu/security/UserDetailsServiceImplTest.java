package tudu.security;

import org.easymock.EasyMock;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import tudu.domain.RolesEnum;
import tudu.domain.model.Role;
import tudu.domain.model.User;
import tudu.service.UserManager;

public class UserDetailsServiceImplTest {

    @Test
    public void testLoadUserByUsername() {
        UserDetailsServiceImpl authenticationDAO = new UserDetailsServiceImpl();
        UserManager userManager = EasyMock.createMock(UserManager.class);
        ReflectionTestUtils.setField(authenticationDAO, "userManager", userManager);

        User user = new User();
        user.setLogin("test_user");
        user.setPassword("password");
        user.setEnabled(true);
        Role userRole = new Role();
        userRole.setRole(RolesEnum.ROLE_USER.toString());
        user.getRoles().add(userRole);
        expect(userManager.findUser("test_user")).andReturn(user);

        userManager.updateUser(user);

        replay(userManager);

        UserDetails springSecurityUser = authenticationDAO
                .loadUserByUsername("test_user");

        assertEquals(user.getLogin(), springSecurityUser.getUsername());
        assertEquals(user.getPassword(), springSecurityUser.getPassword());
        assertNotNull(user.getLastAccessDate());
        assertEquals(1, springSecurityUser.getAuthorities().length);
        assertEquals(RolesEnum.ROLE_USER.toString(),
                springSecurityUser.getAuthorities()[0].getAuthority());

        verify(userManager);
    }
}
