package tudu.web;

import java.util.List;

import tudu.domain.model.User;
import tudu.service.ConfigurationManager;
import tudu.service.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Application administration actions.
 * 
 * @author Julien Dubois
 */
@Controller
public class AdministrationAction {

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private UserManager userManager;

    /**
     * Show the administration page action.
     */
    @RequestMapping("/secure/admin/administration/display.html")
    public ModelAndView display(@RequestParam(required = false) String page) {

        ModelAndView mv = new ModelAndView();

        if (page == null || page.equals("")) {
            page = "configuration";
        }

        if (page.equals("configuration")) {
            mv.addObject("page", "configuration");

            String propertyStaticPath = this.configurationManager.getProperty(
                    "application.static.path").getValue();

            mv.addObject("propertyStaticPath", propertyStaticPath);

            String googleAnalyticsKey = configurationManager.getProperty(
                    "google.analytics.key").getValue();

            mv.addObject("googleAnalyticsKey", googleAnalyticsKey);

            String smtpHost = configurationManager.getProperty("smtp.host")
                    .getValue();
            mv.addObject("smtpHost", smtpHost);

            String smtpPort = configurationManager.getProperty("smtp.port")
                    .getValue();
            mv.addObject("smtpPort", smtpPort);

            String smtpUser = configurationManager.getProperty("smtp.user")
                    .getValue();
            mv.addObject("smtpUser", smtpUser);

            String smtpPassword = configurationManager.getProperty(
                    "smtp.password").getValue();
            mv.addObject("smtpPassword", smtpPassword);

            String smtpFrom = configurationManager.getProperty("smtp.from")
                    .getValue();
            mv.addObject("smtpFrom", smtpFrom);

        } else if (page.equals("users")) {
            mv.addObject("page", "users");
            mv.addObject("numberOfUsers", this.userManager
                    .getNumberOfUsers());

        } else if (page.equals("database")) {
            mv.addObject("page", "database");
        }

        mv.setViewName("administration");
        return mv;
    }

    /**
     * Update the application configuration.
     */
    @RequestMapping("/secure/admin/administration/update_configuration.html")
    public ModelAndView updateConfiguration() {

        /*DynaActionForm administrationForm = (DynaActionForm) form;
        String staticPath = (String) administrationForm
                .get("propertyStaticPath");
        String googleAnalyticsKey = (String) administrationForm
                .get("googleAnalyticsKey");

        this.configurationManager.updateApplicationProperties(staticPath,
                googleAnalyticsKey);

        String smtpHost = (String) administrationForm.get("smtpHost");
        String smtpPort = (String) administrationForm.get("smtpPort");
        String smtpUser = (String) administrationForm.get("smtpUser");
        String smtpPassword = (String) administrationForm.get("smtpPassword");
        String smtpFrom = (String) administrationForm.get("smtpFrom");
        configurationManager.updateEmailProperties(smtpHost, smtpPort,
                smtpUser, smtpPassword, smtpFrom);

        request.setAttribute("success", "true");*/
        return new ModelAndView();
    }

    /**
     * Search for users.
     */
    public ModelAndView searchUser() {

        /*DynaActionForm administrationForm = (DynaActionForm) form;
        String loginStart = (String) administrationForm.get("loginStart");
        if (loginStart == null) {
            loginStart = "";
        }
        List<User> users = this.userManager.findUsersByLogin(loginStart);
        request.setAttribute("users", users);*/
        return new ModelAndView();
    }

    /**
     * Disable a user.
     */
    public ModelAndView disableUser() {

        /*DynaActionForm administrationForm = (DynaActionForm) form;
        String login = (String) administrationForm.get("login");
        if (login == null || login.equals("")) {
            return searchUser(mapping, form, request, response);
        }
        this.userManager.disableUser(login);
        request.setAttribute("success", "true");*/
        return new ModelAndView();
    }

    /**
     * Enable a user.
     */
    public ModelAndView enableUser() {

        /*DynaActionForm administrationForm = (DynaActionForm) form;
        String login = (String) administrationForm.get("login");
        if (login == null || login.equals("")) {
            return searchUser(mapping, form, request, response);
        }
        this.userManager.enableUser(login);
        request.setAttribute("success", "true");*/
        return new ModelAndView();
    }
}
