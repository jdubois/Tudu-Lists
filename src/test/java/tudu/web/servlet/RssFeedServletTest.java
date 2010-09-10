package tudu.web.servlet;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import tudu.domain.Todo;
import tudu.domain.TodoList;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;

public class RssFeedServletTest {

    @Test
    public void testDoGet() throws Exception {

        TodoList todoList = new TodoList();
        todoList.setListId("001");
        todoList.setName("RSS Test Todo List");
        todoList.setRssAllowed(false);

        Todo todo = new Todo();
        todo.setTodoId("0001");
        Calendar creationCal = Calendar.getInstance();
        creationCal.clear();
        creationCal.set(Calendar.YEAR, 2005);
        todo.setCreationDate(creationCal.getTime());
        todo.setDescription("RSS Test description");
        todo.setNotes("RSS Test Note");
        todo.setPriority(0);
        todo.setCompleted(false);

        todoList.getTodos().add(todo);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("todoList", todoList);
        request.setAttribute("link",
                "http://127.0.0.1:8080/tudu/secure/showTodos.action");

        MockHttpServletResponse response = new MockHttpServletResponse();

        RssFeedServlet rssFeedServlet = new RssFeedServlet();

        rssFeedServlet.doGet(request, response);

        String rssContent = response.getContentAsString();

        assertTrue(rssContent.indexOf("<rss") > 0);
        assertTrue(rssContent.indexOf("version=\"2.0\">") > 0);
        assertTrue(rssContent.indexOf("<title>RSS Test Todo List</title>") > 0);
        assertTrue(rssContent
                .indexOf("<link>http://127.0.0.1:8080/tudu/secure/showTodos.action</link>") > 0);
        assertTrue(rssContent
                .indexOf("<description>Tudu Lists | RSS Test Todo List</description>") > 0);
        assertTrue(rssContent.indexOf("<title>RSS Test description</title>") > 0);
        assertTrue(rssContent
                .indexOf("<link>http://127.0.0.1:8080/tudu/secure/showTodos.action?listId=001#todoId0001</link>") > 0);
        assertTrue(rssContent
                .indexOf("<description>RSS Test Note</description>") > 0);
        assertTrue(rssContent
                .indexOf("<guid>http://127.0.0.1:8080/tudu/secure/showTodos.action?listId=001#todoId0001</guid>") > 0);
    }
}
