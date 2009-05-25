package tudu.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.BindingResult;
import tudu.service.UserManager;
import tudu.domain.model.User;
import tudu.Constants;

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

    @ModelAttribute("user")
    public User formBackingObject() {
        return userManager.getCurrentUser();
    }

    @InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setRequiredFields(new String[]{"login", "firstName", "lastName", "password", "verifyPassword"});
	}

    /**
     * Display the "my user info" page.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String display() {
        return "my_info";
    }

    /**
     * Update user information.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String update(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "my_info";
        }

        // If the user hacked the drop-down list, defaults to US date format
        if (user.getDateFormat() == null
                || (!user.getDateFormat().equals(Constants.DATEFORMAT_US)
                        && !user.getDateFormat().equals(Constants.DATEFORMAT_US_SHORT)
                        && !user.getDateFormat().equals(Constants.DATEFORMAT_FRENCH) && !user.getDateFormat()
                        .equals(Constants.DATEFORMAT_FRENCH_SHORT))) {

            user.setDateFormat(Constants.DATEFORMAT_US);
        }
        userManager.updateUser(user);
        return "my_info";
    }
}
