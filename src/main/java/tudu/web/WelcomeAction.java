package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import tudu.domain.RolesEnum;

/**
 * The Welcome action.
 * 
 * @author Julien Dubois
 */
@Controller
public class WelcomeAction {

    private final Log log = LogFactory.getLog(WelcomeAction.class);

    /**
     * Welcome action.
     */
    @RequestMapping("/welcome.action")
    public ModelAndView execute() {

/*        if (request.isUserInRole(RolesEnum.ROLE_USER.toString())) {
            return mapping.findForward("showTodosAction");
        }*/
        return new ModelAndView("login");
    }

}
