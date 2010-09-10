package tudu.domain.comparator;

import tudu.domain.Todo;

import java.util.Comparator;

/**
 * Comparator used to sort todos by their due date.
 * 
 * @author Julien Dubois
 */
public class TodoByDueDateComparator implements Comparator<Todo> {

    public int compare(Todo first, Todo second) {
        if (first.isCompleted() && !second.isCompleted()) {
            return 1;
        } else if (!first.isCompleted() && second.isCompleted()) {
            return -1;
        }
        int order = 0;
        if (first.getDueDate() == null && second.getDueDate() != null) {
            return 1;
        } else if (first.getDueDate() != null && second.getDueDate() == null) {
            return -1;
        } else if (first.getDueDate() != null && second.getDueDate() != null) {
            long compare = first.getDueDate().getTime()
                    - second.getDueDate().getTime();
            if (compare > 0) {
                order = 1;
            } else if (compare < 0) {
                order = -1;
            }
        }
        if (order == 0) {
            order = first.compareTo(second);
        }
        return order;
    }
}
