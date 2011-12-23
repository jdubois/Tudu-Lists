package tudu.domain.model;

import org.junit.Test;
import tudu.domain.Todo;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class TodoTest {

    @Test
    public void testCompareTo() {
        Todo todo1 = new Todo();
        todo1.setTodoId("01");
        todo1.setCompleted(false);
        todo1.setDescription("Description");
        todo1.setPriority(0);

        Todo todo2 = new Todo();
        todo2.setTodoId("02");
        todo2.setCompleted(true);
        todo2.setDescription("Description");
        todo2.setPriority(0);

        Todo todo3 = new Todo();
        todo3.setTodoId("03");
        todo3.setCompleted(false);
        todo3.setDescription("Description");
        todo3.setPriority(10);

        Todo todo4 = new Todo();
        todo4.setTodoId("04");
        todo4.setCompleted(false);
        todo4.setDescription("AA");
        todo4.setPriority(10);

        Todo todo5 = new Todo();
        todo5.setTodoId("05");
        todo5.setCompleted(true);
        todo5.setDescription("Description");
        todo5.setPriority(10);

        Todo todo6 = new Todo();
        todo6.setTodoId("06");
        todo6.setCompleted(false);
        todo6.setDescription("Description");
        todo6.setPriority(10);

        Collection<Todo> sortedTodos = new TreeSet<Todo>();
        sortedTodos.add(todo2);
        sortedTodos.add(todo6);
        sortedTodos.add(todo4);
        sortedTodos.add(todo1);
        sortedTodos.add(todo5);
        sortedTodos.add(todo3);

        assertEquals(6, sortedTodos.size());
        Iterator<Todo> iterator = sortedTodos.iterator();

        Todo testTodo = iterator.next();
        assertEquals("04", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("03", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("06", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("01", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("05", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("02", testTodo.getTodoId());
    }

    @Test
    public void testEquals() {
        Todo todo1 = new Todo();
        todo1.setTodoId("001");

        Todo todo2 = new Todo();
        todo2.setTodoId("001");

        assertEquals(todo1, todo2);

        Todo todo3 = new Todo();
        todo3.setTodoId("003");

        assertNotSame(todo1, todo3);
    }
}
