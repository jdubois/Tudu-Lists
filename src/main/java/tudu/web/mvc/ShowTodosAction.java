package tudu.web.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import tudu.service.UserManager;

/**
 * Show the Todos belonging to the current List.
 * 
 * @author Julien Dubois
 */
@Controller
public class ShowTodosAction {

    private final Log log = LogFactory.getLog(ShowTodosAction.class);

    @Autowired
    private UserManager userManager;

    /**
     * Show the Todos main page.
     */
    public ModelAndView execute() {

        /*log.debug("Execute show action");
        User user = userManager.getCurrentUser();
        Collection<TodoList> todoLists = new TreeSet<TodoList>(user
                .getTodoLists());

        if (!todoLists.isEmpty()) {
            String listId = request.getParameter("listId");
            if (listId != null) {
                request.setAttribute("defaultList", listId);
            } else {
                request.setAttribute("defaultList", todoLists.iterator().next()
                        .getListId());
            }
        }

        String dateFormat = user.getDateFormat();
        request.getSession().setAttribute("dateFormat", dateFormat);
        String calendarDateFormat = "%m/%d/%Y";
        if (Constants.DATEFORMAT_US_SHORT.equals(dateFormat)) {
            calendarDateFormat = "%m/%d/%y";
        } else if (Constants.DATEFORMAT_FRENCH.equals(dateFormat)) {
            calendarDateFormat = "%d/%m/%Y";
        } else if (Constants.DATEFORMAT_FRENCH_SHORT.equals(dateFormat)) {
            calendarDateFormat = "%d/%m/%y";
        }
        request.getSession().setAttribute("calendarDateFormat",
                calendarDateFormat);

        return mapping.findForward("show");*/
        return new ModelAndView();
    }
}
