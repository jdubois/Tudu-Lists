package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import tudu.domain.model.User;
import tudu.service.UserManager;
import tudu.Constants;

/**
 * Manage the user information.
 * 
 * @author Julien Dubois
 */
@Controller
public class MyInfoAction {

    private final Log log = LogFactory.getLog(MyInfoAction.class);

    @Autowired
    private UserManager userManager;

    /**
     * Display the "my user info" page.
     */
    public ModelAndView display() {

        /*log.debug("Execute display action");
        String login = request.getRemoteUser();
        User user = userManager.findUser(login);
        DynaActionForm userForm = (DynaActionForm) form;
        userForm.set("password", user.getPassword());
        userForm.set("verifyPassword", user.getPassword());
        userForm.set("firstName", user.getFirstName());
        userForm.set("lastName", user.getLastName());
        userForm.set("email", user.getEmail());
        userForm.set("dateFormat", user.getDateFormat());
        return mapping.findForward("user.info");*/
        return new ModelAndView();
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
