package tudu.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tudu.Constants;
import tudu.domain.model.TodoList;
import tudu.domain.model.User;
import tudu.service.UserManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Show the Todos belonging to the current List.
 * 
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/secure/showTodos.action")
public class ShowTodosAction {

    @Autowired
    private UserManager userManager;

    /**
     * Show the Todos main page.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showTodos(HttpServletRequest request) {

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

        return new ModelAndView("todos");
    }
}
