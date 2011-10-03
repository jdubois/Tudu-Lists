package tudu.web.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tudu.domain.RolesEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * The Welcome controller.
 * 
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    private final Log log = LogFactory.getLog(WelcomeController.class);

    /**
     * Welcome action.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome(HttpServletRequest request,
                          @RequestParam(required = false) String authentication) {

        ModelAndView mv = new ModelAndView();
        if (request.isUserInRole(RolesEnum.ROLE_USER.name())) {
            mv.setViewName("redirect:/tudu/lists");
        } else {
            mv.addObject("authentication", authentication);
            mv.setViewName("welcome");
        }
        return mv;
    }
}
