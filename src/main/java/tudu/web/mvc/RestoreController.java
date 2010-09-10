package tudu.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import tudu.domain.TodoList;
import tudu.service.TodoListsService;

/**
 * Restore a Todo List.
 * 
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/restore")
public class RestoreController {

    @Autowired
    private TodoListsService todoListsService;

    @InitBinder
	public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringMultipartFileEditor());
	}

    /**
     * Display the main screen for restoring a Todo List.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView display(@RequestParam String listId) {
        ModelAndView mv = new ModelAndView();
        TodoList todoList = todoListsService.findTodoList(listId);
        mv.addObject("todoList", todoList);
        RestoreTodoListForm form = new RestoreTodoListForm();
        form.setRestoreChoice("create");
        form.setListId(listId);
        mv.addObject("restoreTodoListForm", form);
        mv.setViewName("RestoreController");
        return mv;
    }

    /**
     * Restore a Todo List.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView restore(@ModelAttribute RestoreTodoListForm form) {

        /*log.debug("Execute RestoreController action");
        ActionMessages errors = form.validate(mapping, request);
        if (errors.size() != 0) {
            saveErrors(request, errors);
            return mapping.getInputForward();
        }
        RestoreTodoListForm restoreTodoListForm = (RestoreTodoListForm) form;
        try {
            todoListsService.restoreTodoList(restoreTodoListForm
                    .getRestoreChoice(), restoreTodoListForm.getListId(),
                    restoreTodoListForm.getBackupFile().getInputStream());

        } catch (FileNotFoundException e) {
            log.info("FileNotFoundException : " + e.getMessage());
            ActionMessage message = new ActionMessage("RestoreController.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
        } catch (IOException e) {
            log.info("IOException : " + e.getMessage());
            ActionMessage message = new ActionMessage("RestoreController.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        } catch (JDOMException e) {
            log.info("JDOMException : " + e.getMessage());
            ActionMessage message = new ActionMessage("RestoreController.file.error");
            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            log.info("Exception : " + e.getMessage());
            ActionMessage message = new ActionMessage("RestoreController.file.error");
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

    public String cancel() {
        return "redirect:showTodos.action";
    }

}
