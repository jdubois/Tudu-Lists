package tudu.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import tudu.domain.model.TodoList;
import tudu.service.TodoListsManager;

/**
 * Backup a Todo List.
 * 
 * @author Julien Dubois
 */
@Controller
public class BackupTodoListAction {

    private final Log log = LogFactory.getLog(BackupTodoListAction.class);

    @Autowired
    private TodoListsManager todoListsManager;

    /**
     * Backup a Todo List.
     */
    public ModelAndView execute()
            throws Exception {

        /*log.debug("Execute action");
        DynaActionForm todoListForm = (DynaActionForm) form;
        String listId = (String) todoListForm.get("listId");
        TodoList todoList = todoListsManager.findTodoList(listId);
        Document doc = todoListsManager.backupTodoList(todoList);
        request.getSession().setAttribute("todoListDocument", doc);*/
        return new ModelAndView();
    }
}
