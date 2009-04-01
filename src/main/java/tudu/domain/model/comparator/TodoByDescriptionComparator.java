package tudu.domain.model.comparator;

import java.util.Comparator;

import tudu.domain.model.Todo;

/**
 * Comparator used to sort todos by their description.
 * 
 * @author Julien Dubois
 */
public class TodoByDescriptionComparator implements Comparator<Todo> {

    public int compare(Todo first, Todo second) {
        if (first.isCompleted() && !second.isCompleted()) {
            return 1;
        } else if (!first.isCompleted() && second.isCompleted()) {
            return -1;
        }
        int order;
        order = first.getDescription().toLowerCase().compareTo(
                second.getDescription().toLowerCase());

        if (order == 0) {
            order = first.compareTo(second);
        }
        return order;
    }
}
