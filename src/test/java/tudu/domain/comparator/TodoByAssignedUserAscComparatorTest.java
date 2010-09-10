package tudu.domain.comparator;

import org.junit.Test;
import tudu.domain.Todo;
import tudu.domain.User;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class TodoByAssignedUserAscComparatorTest {

    @Test
    public void testCompare() {
        User userA = new User();
        userA.setLogin("a");

        User userB = new User();
        userB.setLogin("b");

        Todo todo1 = new Todo();
        todo1.setTodoId("01");
        todo1.setCompleted(false);
        todo1.setDescription("bb");
        todo1.setPriority(0);

        Todo todo2 = new Todo();
        todo2.setAssignedUser(userB);
        todo2.setTodoId("02");
        todo2.setCompleted(false);
        todo2.setDescription("aa");
        todo2.setPriority(0);

        Todo todo3 = new Todo();
        todo3.setTodoId("03");
        todo3.setCompleted(false);
        todo3.setDescription("AA");
        todo3.setPriority(10);

        Todo todo4 = new Todo();
        todo4.setAssignedUser(userA);
        todo4.setTodoId("04");
        todo4.setCompleted(false);
        todo4.setDescription("bb");
        todo4.setPriority(10);

        Todo todo5 = new Todo();
        todo5.setAssignedUser(userA);
        todo5.setTodoId("05");
        todo5.setCompleted(true);
        todo5.setDescription("cc");
        todo5.setPriority(10);

        Todo todo6 = new Todo();
        todo6.setTodoId("06");
        todo6.setCompleted(true);
        todo6.setDescription("aa");
        todo6.setPriority(10);

        Comparator<Todo> comparator = new TodoByAssignedUserAscComparator();
        Collection<Todo> sortedTodos = new TreeSet<Todo>(comparator);
        sortedTodos.add(todo2);
        sortedTodos.add(todo4);
        sortedTodos.add(todo6);
        sortedTodos.add(todo5);
        sortedTodos.add(todo1);
        sortedTodos.add(todo3);

        assertEquals(6, sortedTodos.size());
        Iterator<Todo> iterator = sortedTodos.iterator();

        Todo testTodo = iterator.next();
        assertEquals("01", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("03", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("02", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("04", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("06", testTodo.getTodoId());
        testTodo = iterator.next();
        assertEquals("05", testTodo.getTodoId());
    }
}
