package tudu.web.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import tudu.domain.TodoList;
import tudu.service.TodoListsService;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Restore a Todo List.
 * 
 * @author Julien Dubois
 */
@Controller
@RequestMapping("/restore")
public class RestoreController {

    private final Log log = LogFactory.getLog(RestoreController.class);

    @Autowired
    private TodoListsService todoListsService;

    @InitBinder
	public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringMultipartFileEditor());
	}

    /**
     * Display the main screen for restoring a Todo List.
     */
    @RequestMapping(value="/{listId}", method = RequestMethod.GET)
    public ModelAndView display(@PathVariable String listId) {
        ModelAndView mv = new ModelAndView();
        TodoList todoList = todoListsService.findTodoList(listId);
        mv.addObject("todoList", todoList);
        RestoreTodoListModel restoreTodoListModel = new RestoreTodoListModel();
        restoreTodoListModel.setRestoreChoice("create");
        restoreTodoListModel.setListId(listId);
        mv.addObject("restoreTodoListModel", restoreTodoListModel);
        mv.setViewName("restore");
        return mv;
    }

    /**
     * Restore a Todo List.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView restore(@Valid RestoreTodoListModel restoreTodoListModel, BindingResult result) {
        log.debug("Execute RestoreController");
        ModelAndView mv = new ModelAndView();
        if (restoreTodoListModel.getListId() == null) {
            mv.setViewName("redirect:/tudu/lists");
            return mv;
        }
        TodoList todoList = todoListsService.findTodoList(restoreTodoListModel.getListId());
        mv.addObject("todoList", todoList);
        mv.addObject("restoreTodoListModel", restoreTodoListModel);
        if (result.hasErrors()) {
            mv.setViewName("restore");
            return mv;
        }
        if (restoreTodoListModel.getBackupFile().isEmpty()) {
            result.rejectValue("backupFile", "restore.file.empty", "Empty file.");
            mv.setViewName("restore");
            return mv;
        }
        try {
            todoListsService.restoreTodoList(restoreTodoListModel.getRestoreChoice(),
                    restoreTodoListModel.getListId(),
                    restoreTodoListModel.getBackupFile().getInputStream());

            mv.setViewName("redirect:/tudu/lists");
        } catch (JDOMException e) {
            log.info("JDOMException : " + e.getMessage());
            mv.setViewName("restore");
        } catch (IOException e) {
            log.info("FileNotFoundException : " + e.getMessage());
            mv.setViewName("restore");
        }
        return mv;
    }

}
