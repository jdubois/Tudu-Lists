package tudu.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tudu.domain.model.User;
import tudu.service.UserAlreadyExistsException;
import tudu.service.UserManager;

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

    @ModelAttribute("user")
    public User formBackingObject() {
        return new User();
    }

    @InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setRequiredFields(new String[]{"login", "firstName", "lastName", "password", "verifyPassword"});
	}

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
        if (result.hasErrors()) {
            return "register";
        }
        try {
            userManager.createUser(user);
        } catch (UserAlreadyExistsException e) {
            result.reject("register.user.already.exists");
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
