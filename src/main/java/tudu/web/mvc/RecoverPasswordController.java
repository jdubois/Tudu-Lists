package tudu.web.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tudu.domain.User;
import tudu.service.UserService;

/**
 * Recover a user's lost password.
 * 
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/recoverPassword")
public class RecoverPasswordController {

    private final Log log = LogFactory.getLog(RecoverPasswordController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User formBackingObject() {
        return new User();
    }

    /**
     * Show the recover password page action.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String display() {
        return "recover_password";
    }

    /**
     * Send an email with the new password to the user.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView sendMail(@ModelAttribute User user, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        mv.addObject(user);
        mv.setViewName("recover_password");
        String login = user.getLogin().toLowerCase();
        try {
            user = userService.findUser(login);
        } catch (ObjectRetrievalFailureException orfe) {
            result.rejectValue("login", "recover.password.no.user");
            return mv;
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            result.rejectValue("login", "recover.password.no.email");
            return mv;
        }
        try {
            userService.sendPassword(user);
            mv.addObject("success", "true");
        } catch (RuntimeException e) {
            result.rejectValue("login", "recover.password.smtp.error");
            log.warn("Mail sending error : " + e.getMessage());
        }
        return mv;
    }
}
