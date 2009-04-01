package tudu.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.rememberme.TokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

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
public class LogoutAction {

    private final Log log = LogFactory.getLog(LogoutAction.class);

    /**
     * Log out action.
     */
    @SuppressWarnings("unchecked")
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {

        log.debug("Execute action");
        // Remove the security information
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
        return new ModelAndView("logout");
    }
}
