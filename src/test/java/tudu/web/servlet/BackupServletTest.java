package tudu.web.servlet;

import org.jdom.Document;
import org.jdom.Element;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

public class BackupServletTest {

    @Test
    public void testDoGet() throws Exception {

        Document doc = new Document();
        Element todoListElement = new Element("todolist");
        todoListElement.addContent(new Element("title")
                .addContent("Backup List"));
        doc.addContent(todoListElement);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("todoListDocument", doc);
        request.setSession(session);

        MockHttpServletResponse response = new MockHttpServletResponse();

        BackupServlet backupServlet = new BackupServlet();
        backupServlet.doGet(request, response);

        String xmlContent = response.getContentAsString();

        assertTrue(xmlContent.indexOf("<title>Backup List</title>") > 0);
    }
}
