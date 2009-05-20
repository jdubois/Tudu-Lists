package tudu.web.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import tudu.service.TodoListsManager;

/**
 * Restore a Todo List.
 * 
 * @author Julien Dubois
 */
@Controller
public class RestoreTodoListAction {

    private final Log log = LogFactory.getLog(RestoreTodoListAction.class);

    @Autowired
    private TodoListsManager todoListsManager;

    /**
     * Display the main screen for restoring a Todo List.
     */
    public ModelAndView display() {

        /*log.debug("Execute display action");
        RestoreTodoListForm restoreTodoListForm = (RestoreTodoListForm) form;
        String listId = restoreTodoListForm.getListId();

        TodoList todoList = todoListsManager.findTodoList(listId);
        request.setAttribute("todoList", todoList);

        if (restoreTodoListForm.getRestoreChoice() == null) {
            restoreTodoListForm.setRestoreChoice("create");
        }
        return mapping.findForward("restore");*/
        return new ModelAndView();
    }

    /**
     * Restore a Todo List.
     */
    public ModelAndView restore() {

        /*log.debug("Execute restore action");
        ActionMessages errors = form.validate(mapping, request);
        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        RestoreTodoListForm restoreTodoListForm = (RestoreTodoListForm) form;
        try {
            todoListsManager.restoreTodoList(restoreTodoListForm
                    .getRestoreChoice(), restoreTodoListForm.getListId(),
                    restoreTodoListForm.getBackupFile().getInputStream());

        } catch (FileNotFoundException e) {
            log.info("FileNotFoundException : " + e.getMessage());
            ActionMessage message = new ActionMessage("restore.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
        } catch (IOException e) {
            log.info("IOException : " + e.getMessage());
            ActionMessage message = new ActionMessage("restore.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        } catch (JDOMException e) {
            log.info("JDOMException : " + e.getMessage());
            ActionMessage message = new ActionMessage("restore.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            log.info("Exception : " + e.getMessage());
            ActionMessage message = new ActionMessage("restore.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        return mapping.findForward("showTodosAction");*/
        return new ModelAndView();
    }

    public ModelAndView cancel() {
        return new ModelAndView();
    }

}
