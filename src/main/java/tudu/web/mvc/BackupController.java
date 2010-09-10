package tudu.web.mvc;

import org.jdom.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import tudu.domain.TodoList;
import tudu.service.TodoListsService;

import javax.servlet.http.HttpSession;

/**
 * Backup a Todo List.
 * 
 * @author Julien Dubois
 */
@Controller
public class BackupController {

    @Autowired
    private TodoListsService todoListsService;

    /**
     * Backup a Todo List.
     */
    @RequestMapping("/backup")
    public ModelAndView backup(@RequestParam String listId, HttpSession session)
            throws Exception {

        TodoList todoList = todoListsService.findTodoList(listId);
        Document doc = todoListsService.backupTodoList(todoList);
        session.setAttribute("todoListDocument", doc);
        ModelAndView mv = new ModelAndView();
        mv.setView(new InternalResourceView("/secure/servlet/tudu_lists_backup.xml"));
        return mv;
    }
}
