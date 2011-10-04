package tudu.web.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import tudu.domain.TodoList;
import tudu.service.TodoListsService;

import javax.servlet.http.HttpServletRequest;

/**
 * Generate the RSS feed.
 * 
 * @author Julien Dubois
 */
@Controller
public class ShowRssFeedController {

    private final Log log = LogFactory.getLog(ShowRssFeedController.class);

    @Autowired
    private TodoListsService todoListsService;

    @RequestMapping("/rss")
    public ModelAndView showRss(@RequestParam String listId, HttpServletRequest request)
            throws Exception {

        ModelAndView mv = new ModelAndView();
        TodoList todoList = todoListsService.unsecuredFindTodoList(listId);

        if (todoList.isRssAllowed()) {
            mv.addObject("todoList", todoList);
            mv.addObject("link", request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/tudu/lists");

            mv.setView(new InternalResourceView("/servlet/rss"));
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Rendering RSS feed for Todo List ID '"
                        + todoList.getListId() + "' is not allowed");
            }
            mv.setViewName("rss_not_allowed");
        }
        return mv;
    }
}
