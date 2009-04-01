package tudu.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import tudu.service.UserManager;

/**
 * Recover a user's lost password.
 * 
 * @author Julien Dubois
 */
@Controller
public class RecoverPasswordAction {

    private final Log log = LogFactory.getLog(RecoverPasswordAction.class);

    @Autowired
    private UserManager userManager;

    /**
     * Show the recover password page action.
     */
    public ModelAndView display() {

/*        log.debug("Execute display action");
        DynaActionForm recoverPasswordForm = (DynaActionForm) form;
        recoverPasswordForm.set("login", "");*/
        return new ModelAndView();
    }

    /**
     * Send an email with the new password to the user.
     */
    public ModelAndView sendMail() {

        /*log.debug("Execute sendMail action");
        ActionMessages errors = form.validate(mapping, request);
        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        DynaActionForm recoverPasswordForm = (DynaActionForm) form;
        String login = (String) recoverPasswordForm.get("login");
        login = login.toLowerCase();
        User user;
        try {
            user = userManager.findUser(login);
        } catch (ObjectRetrievalFailureException orfe) {
            ActionMessage message = new ActionMessage(
                    "recover.password.no.user", login);

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            ActionMessage message = new ActionMessage(
                    "recover.password.no.email", login);

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        try {
            userManager.sendPassword(user);
            request.setAttribute("success", "true");
        } catch (RuntimeException e) {
            log.warn("Mail sending error : " + e.getMessage());
            ActionMessage message = new ActionMessage(
                    "recover.password.smtp.error", login);

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        return mapping.findForward("recover");*/
        return new ModelAndView();
    }

    /**
     * Cancel the action.
     */
    public ModelAndView cancel() {
        return new ModelAndView("cancel");
    }
}
