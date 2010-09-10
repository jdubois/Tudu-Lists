package tudu.domain.comparator;

import tudu.domain.Todo;

import java.util.Comparator;

/**
 * Comparator used to sort todos by their priority, in ascending order.
 * 
 * @author Julien Dubois
 */
public class TodoByPriorityAscComparator implements Comparator<Todo> {

    public int compare(Todo first, Todo second) {
        int order = first.getPriority() - second.getPriority();
        if (first.isCompleted()) {
            order += 10000;
        }
        if (second.isCompleted()) {
            order -= 10000;
        }
        if (order == 0) {
            order = (second.getDescription() + second.getTodoId())
                    .compareTo(first.getDescription() + first.getTodoId());
        }
        return order;
    }
}
