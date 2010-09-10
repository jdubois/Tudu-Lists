package tudu.domain.comparator;

import org.junit.Test;
import tudu.domain.Todo;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class TodoByDueDateAscComparatorTest {

    @Test
    public void testCompare() {
        Todo todo1 = new Todo();
        todo1.setTodoId("01");
        todo1.setCompleted(false);
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2006, 10, 5);
        todo1.setDueDate(cal1.getTime());

        Todo todo2 = new Todo();
        todo2.setTodoId("02");
        todo2.setCompleted(false);

        Todo todo3 = new Todo();
        todo3.setTodoId("03");
        todo3.setCompleted(false);
        Calendar cal3 = Calendar.getInstance();
        cal3.set(2007, 5, 1);
        todo3.setDueDate(cal3.getTime());

        Todo todo4 = new Todo();
        todo4.setTodoId("04");
        todo4.setCompleted(false);
        Calendar cal4 = Calendar.getInstance();
        cal4.set(2002, 1, 1);
        todo4.setDueDate(cal4.getTime());

        Todo todo5 = new Todo();
        todo5.setTodoId("05");
        todo5.setCompleted(false);

        Todo todo6 = new Todo();
        todo6.setTodoId("06");
        todo6.setCompleted(true);
        Calendar cal6 = Calendar.getInstance();
        cal6.set(2008, 1, 1);
        todo6.setDueDate(cal6.getTime());

        Todo todo7 = new Todo();
        todo7.setTodoId("07");
        todo7.setCompleted(true);
        Calendar cal7 = Calendar.getInstance();
        cal7.set(2008, 2, 1);
        todo7.setDueDate(cal7.getTime());

        Todo todo8 = new Todo();
        todo8.setTodoId("08");
        todo8.setCompleted(true);

        Comparator<Todo> comparator = new TodoByDueDateAscComparator();
        Collection<Todo> sortedTodos = new TreeSet<Todo>(comparator);
        sortedTodos.add(todo3);
        sortedTodos.add(todo2);
        sortedTodos.add(todo5);
        sortedTodos.add(todo4);
        sortedTodos.add(todo8);
        sortedTodos.add(todo1);
        sortedTodos.add(todo7);
        sortedTodos.add(todo6);

        assertEquals(8, sortedTodos.size());
        Iterator<Todo> iterator = sortedTodos.iterator();

        Todo testTodo = iterator.next();
        assertEquals("05", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("02", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("03", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("01", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("04", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("08", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("07", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("06", testTodo.getTodoId());
    }
}
