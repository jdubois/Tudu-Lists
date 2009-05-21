package tudu.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tudu.service.UserManager;
import tudu.domain.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Manage the user information.
 * 
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/secure/myInfo.action")
public class MyInfoAction {

    @Autowired
    private UserManager userManager;

    /**
     * Display the "my user info" page.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView display() {
        User user = userManager.getCurrentUser();
        ModelAndView mv = new ModelAndView("my_info");
        mv.addObject("user", user);
        return mv;
    }

    /**
     * Update user information.
     */
    public ModelAndView update() {

        /*log.debug("Execute update action");
        ActionMessages errors = form.validate(mapping, request);
        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.getInputForward();
        }

        DynaActionForm userForm = (DynaActionForm) form;

        String password = (String) userForm.get("password");
        String firstName = (String) userForm.get("firstName");
        String lastName = (String) userForm.get("lastName");
        String email = (String) userForm.get("email");

        String dateFormat = (String) userForm.get("dateFormat");
        // If the user hacked the drop-down list, defaults to US date format
        if (dateFormat == null
                || (!dateFormat.equals(Constants.DATEFORMAT_US)
                        && !dateFormat.equals(Constants.DATEFORMAT_US_SHORT)
                        && !dateFormat.equals(Constants.DATEFORMAT_FRENCH) && !dateFormat
                        .equals(Constants.DATEFORMAT_FRENCH_SHORT))) {

            dateFormat = Constants.DATEFORMAT_US;
        }

        String login = request.getRemoteUser();
        User user = userManager.findUser(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setDateFormat(dateFormat);
        userManager.updateUser(user);
        request.setAttribute("success", "true");
        return display(mapping, form, request, response);*/
        return new ModelAndView();
    }
}
