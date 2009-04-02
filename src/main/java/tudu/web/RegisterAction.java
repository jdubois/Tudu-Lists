package tudu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tudu.service.UserManager;
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
    public ModelAndView register() {

        /*log.debug("Execute register action");
        DynaActionForm registerForm = (DynaActionForm) form;
        ActionMessages errors = form.validate(mapping, request);
        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        String login = (String) registerForm.get("login");
        login = login.toLowerCase();
        String password = (String) registerForm.get("password");
        String firstName = (String) registerForm.get("firstName");
        String lastName = (String) registerForm.get("lastName");
        String email = (String) registerForm.get("email");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        try {
            userManager.createUser(user);
        } catch (UserAlreadyExistsException e) {
            ActionMessage message = new ActionMessage(
                    "register.user.already.exists", login);

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        request.setAttribute("login", login);
        return mapping.findForward("success");*/
        return new ModelAndView();
    }

    /**
     * Cancel the action.
     */
    public String cancel() {
        return "cancel";
    }
}
