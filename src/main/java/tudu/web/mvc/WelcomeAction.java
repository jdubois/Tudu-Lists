package tudu.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Welcome action.
 * 
 * @author Julien Dubois
 */
@Controller
public class WelcomeAction {

    /**
     * Welcome action.
     */
    @RequestMapping("/welcome.action")
    public String welcome() {

/*        if (request.isUserInRole(RolesEnum.ROLE_USER.toString())) {
            return "showTodosAction";
        }*/
        return "login";
    }

}
