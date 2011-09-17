package tudu.security;

import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import tudu.domain.Role;
import tudu.domain.RolesEnum;
import tudu.domain.User;
import tudu.service.UserService;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDetailsServiceImplTest {

    @Test
    public void testLoadUserByUsername() {
        UserDetailsServiceImpl userDetailService = new UserDetailsServiceImpl();
        UserService userService = EasyMock.createMock(UserService.class);
        ReflectionTestUtils.setField(userDetailService, "userService", userService);

        User user = new User();
        user.setLogin("test_user");
        user.setPassword("password");
        user.setEnabled(true);
        Role userRole = new Role();
        userRole.setRole(RolesEnum.ROLE_USER.toString());
        user.getRoles().add(userRole);
        expect(userService.findUser("test_user")).andReturn(user);

        replay(userService);

        UserDetails springSecurityUser = userDetailService
                .loadUserByUsername("test_user");

        assertEquals(user.getLogin(), springSecurityUser.getUsername());
        assertEquals(user.getPassword(), springSecurityUser.getPassword());
        assertNotNull(user.getLastAccessDate());
        assertEquals(1, springSecurityUser.getAuthorities().size());
        assertEquals(RolesEnum.ROLE_USER.toString(),
                springSecurityUser.getAuthorities().iterator().next().getAuthority());

        verify(userService);
    }
}
