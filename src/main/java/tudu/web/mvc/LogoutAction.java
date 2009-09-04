package tudu.web.mvc;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * The Log out action.
 * 
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/logout.action")
public class LogoutAction {

    @RequestMapping(method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        SecurityContextHolder.clearContext();

        // Remove all session data
        HttpSession session = request.getSession();
        for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {
            session.removeAttribute((String) e.nextElement());
        }

        // Remove the cookie
        Cookie terminate = new Cookie(
                TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY,
                null);
        terminate.setMaxAge(-1);
        terminate.setPath(request.getContextPath() + "/");
        response.addCookie(terminate);
        return "logout";
    }
}
