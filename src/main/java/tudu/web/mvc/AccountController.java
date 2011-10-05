package tudu.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tudu.Constants;
import tudu.domain.User;
import tudu.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Manage the user information.
 * 
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User formBackingObject() {
        User user = userService.getCurrentUser();
        user.setVerifyPassword(user.getPassword());
        return user;
    }

    /**
     * Display the "my user info" page.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String display() {
        return "account";
    }

    /**
     * Update user information.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String update(@Valid User user, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "account";
        }
        if (!user.getPassword().equals(user.getVerifyPassword())) {
            result.rejectValue("verifyPassword", "user.info.password.not.matching");
            return "account";
        }

        // If the user hacked the drop-down list, defaults to US date format
        if (user.getDateFormat() == null
                || (!user.getDateFormat().equals(Constants.DATEFORMAT_US)
                        && !user.getDateFormat().equals(Constants.DATEFORMAT_US_SHORT)
                        && !user.getDateFormat().equals(Constants.DATEFORMAT_FRENCH) && !user.getDateFormat()
                        .equals(Constants.DATEFORMAT_FRENCH_SHORT))) {

            user.setDateFormat(Constants.DATEFORMAT_US);
        }
        userService.updateUser(user);
        request.setAttribute("updated", "ok");
        return "account";
    }
}
