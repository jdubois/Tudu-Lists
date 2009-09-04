package tudu.web.mvc;

import org.jdom.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import tudu.domain.model.TodoList;
import tudu.service.TodoListsManager;

import javax.servlet.http.HttpSession;

/**
 * Backup a Todo List.
 * 
 * @author Julien Dubois
 */
@Controller
public class BackupTodoListAction {

    @Autowired
    private TodoListsManager todoListsManager;

    /**
     * Backup a Todo List.
     */
    @RequestMapping("/secure/backupTodoList.action")
    public ModelAndView backup(@RequestParam String listId, HttpSession session)
            throws Exception {

        TodoList todoList = todoListsManager.findTodoList(listId);
        Document doc = todoListsManager.backupTodoList(todoList);
        session.setAttribute("todoListDocument", doc);
        ModelAndView mv = new ModelAndView();
        mv.setView(new InternalResourceView("/secure/servlet/tudu_lists_backup.xml"));
        return mv;
    }
}
