package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their description, in ascending order.
 * 
 * @author Julien Dubois
 */
public class TodoByDescriptionAscComparator implements Comparator<Todo> {

    public int compare(Todo first, Todo second) {
        if (first.isCompleted() && !second.isCompleted()) {
            return 1;
        } else if (!first.isCompleted() && second.isCompleted()) {
            return -1;
        }
        int order;
        order = second.getDescription().toLowerCase().compareTo(
                first.getDescription().toLowerCase());

        if (order == 0) {
            order = second.compareTo(first);
        }
        return order;
    }
}
