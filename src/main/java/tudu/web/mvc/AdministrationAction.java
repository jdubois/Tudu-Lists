package tudu.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tudu.domain.model.User;
import tudu.service.ConfigurationManager;
import tudu.service.UserManager;

import java.util.List;

/**
 * Application administration actions.
 * 
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/secure/admin/administration.action")
public class AdministrationAction {

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private UserManager userManager;

    /**
     * Show the administration page action.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView display(@RequestParam(required = false) String page) {

        ModelAndView mv = new ModelAndView();
        AdministrationModel model = new AdministrationModel();

        if (page == null || page.equals("")) {
            page = "configuration";
        }

        if (page.equals("configuration")) {
            mv.addObject("page", "configuration");
            String propertyStaticPath = this.configurationManager.getProperty(
                    "application.static.path").getValue();

            model.setPropertyStaticPath(propertyStaticPath);

            String googleAnalyticsKey = configurationManager.getProperty(
                    "google.analytics.key").getValue();

            model.setGoogleAnalyticsKey(googleAnalyticsKey);

            String smtpHost = configurationManager.getProperty("smtp.host")
                    .getValue();
            model.setSmtpHost(smtpHost);

            String smtpPort = configurationManager.getProperty("smtp.port")
                    .getValue();
            model.setSmtpPort(smtpPort);

            String smtpUser = configurationManager.getProperty("smtp.user")
                    .getValue();
            model.setSmtpUser(smtpUser);

            String smtpPassword = configurationManager.getProperty(
                    "smtp.password").getValue();
            model.setSmtpPassword(smtpPassword);

            String smtpFrom = configurationManager.getProperty("smtp.from")
                    .getValue();
            model.setSmtpFrom(smtpFrom);


        } else if (page.equals("users")) {
            mv.addObject("page", "users");
            model.setSearchLogin("");
            mv.addObject("numberOfUsers", this.userManager
                    .getNumberOfUsers());

        }
        mv.addObject("administrationModel", model);
        mv.setViewName("administration");
        return mv;
    }

    /**
     * Update the application configuration.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute AdministrationModel model) {
        ModelAndView mv = new ModelAndView();
        if ("configuration".equals(model.getAction())) {
            this.configurationManager.updateApplicationProperties(model.getPropertyStaticPath(),
                    model.getGoogleAnalyticsKey());

            this.configurationManager.updateEmailProperties(model.getSmtpHost(), model.getSmtpPort(),
                    model.getSmtpUser(), model.getSmtpPassword(), model.getSmtpFrom());

            mv = this.display("configuration");
            mv.addObject("success", "true");
        } else {
            if ("disableUser".equals(model.getAction())) {
                String login = model.getLogin();
                this.userManager.disableUser(login);
            }
            if ("enableUser".equals(model.getAction())) {
                String login = model.getLogin();
                this.userManager.enableUser(login);
            }
            if (model.getSearchLogin() == null) {
                model.setSearchLogin("");
            }
            List<User> users = this.userManager.findUsersByLogin(model.getSearchLogin());
            mv.addObject("users", users);
            mv.addObject("page", "users");
            mv.addObject("administrationModel", model);
            mv.setViewName("administration");
        }

        return mv;
    }
}
