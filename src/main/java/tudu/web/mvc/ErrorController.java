package tudu.web.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julien Dubois
 */
@Controller
public class ErrorController {

    private final Log log = LogFactory.getLog(ErrorController.class);

    @RequestMapping("/404")
    public String pageNotFound(HttpServletRequest request) {
        if (log.isInfoEnabled()) {
            log.info("404 error");
        }
        return "404";
    }

    @RequestMapping("/500")
    public String internatServerError(HttpServletRequest request) {
        if (log.isInfoEnabled()) {
            log.info("500 error");
        }
        return "500";
    }
}
