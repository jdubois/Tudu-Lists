package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RegisterAction {

    private final Log log = LogFactory.getLog(RegisterAction.class);

    @Autowired
    private UserManager userManager;

    /**
     * Show the "register a new user" page.
     */
    public ModelAndView display() {

        return new ModelAndView("register");
    }

    /**
     * Register a new user.
     */
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
    public ModelAndView cancel() {
        return new ModelAndView("cancel");
    }
}
