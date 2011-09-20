package tudu.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tudu.domain.RolesEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * The license controller.
 * 
 * @author Julien Dubois
 */
@Controller
public class LicenseController {

    /**
     * Shows the Tudu Lists' license.
     */
    @RequestMapping(value="/license", method = RequestMethod.GET)
    public String license() {
        return "license";
    }

}
