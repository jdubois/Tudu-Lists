package tudu.web.servlet;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import tudu.domain.Todo;
import tudu.domain.TodoList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * RSS feed presentation layer.
 * 
 * @author Julien Dubois
 */
public class RssFeedServlet extends HttpServlet {

    private static final long serialVersionUID = -7649835277417858193L;

    private final Log log = LogFactory.getLog(RssFeedServlet.class);

    /**
     * Default feed type.
     */
    private static final String FEED_TYPE = "rss_2.0";

    /**
     * Default mime type.
     */
    private static final String MIME_TYPE = "application/xml; charset=UTF-8";

    @Override
    protected final void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        TodoList todoList = (TodoList) request.getAttribute("todoList");
        String link = (String) request.getAttribute("link");
        Collection<Todo> todos = new TreeSet<Todo>(todoList.getTodos());

        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType(FEED_TYPE);

        feed.setTitle(todoList.getName());
        feed.setLink(link);
        feed.setDescription("Tudu Lists | " + todoList.getName());

        List<SyndEntry> entries = new ArrayList<SyndEntry>();
        for (Todo todo : todos) {
            SyndEntry entry = new SyndEntryImpl();
            entry.setLink(link + "?listId=" + todoList.getListId() + "#todoId"
                    + todo.getTodoId());
            SyndContent description = new SyndContentImpl();
            description.setType("text/plain");
            if (todo.isCompleted()) {
                entry.setTitle("Completed : " + todo.getDescription());
                entry.setPublishedDate(todo.getCompletionDate());
                description.setValue(todo.getNotes());
            } else {
                entry.setTitle(todo.getDescription());
                entry.setPublishedDate(todo.getCreationDate());
                description.setValue(todo.getNotes());
            }
            entry.setDescription(description);
            entries.add(entry);
        }
        feed.setEntries(entries);

        response.setContentType(MIME_TYPE);
        SyndFeedOutput output = new SyndFeedOutput();
        try {
            output.output(feed, response.getWriter());
        } catch (FeedException fe) {
            String msg = "The RSS feed could not be generated.";
            log.error("Error while generating the RSS feed : "
                    + fe.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    msg);
        }
    }
}
