package tudu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import tudu.service.UserManager;
import tudu.service.UserAlreadyExistsException;
import tudu.domain.model.User;

/**
 * Register a new Tudu Lists user.
 * 
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/register.action")
public class RegisterAction {

    @Autowired
    private UserManager userManager;

    /**
     * Show the "register a new user" page.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView display() {
        ModelAndView mv = new ModelAndView("register");
        mv.addObject("user", new User());
        return mv;
    }

    /**
     * Register a new user.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String register(@ModelAttribute User user, BindingResult result) {
        try {
            userManager.createUser(user);
        } catch (UserAlreadyExistsException e) {
            result.reject("register.user.already.exists");
            return "register";
        }
        return "success";
    }

    /**
     * Cancel the action.
     */
    public String cancel() {
        return "cancel";
    }
}
