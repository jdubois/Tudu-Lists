package tudu.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tudu.domain.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.UserService;

import javax.validation.Valid;

/**
 * Register a new Tudu Lists user.
 *
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

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
    public String register(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        if (!user.getPassword().equals(user.getVerifyPassword())) {
            result.rejectValue("verifyPassword", "user.info.password.not.matching");
            return "register";
        }
        try {
            userService.createUser(user);
        } catch (UserAlreadyExistsException e) {
            result.rejectValue("login", "register.user.already.exists");
            return "register";
        }
        return "register_ok";
    }

    /**
     * Cancel the action.
     */
    public String cancel() {
        return "cancel";
    }
}
