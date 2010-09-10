package tudu.domain.comparator;

import tudu.domain.Todo;

import java.util.Comparator;

/**
 * Comparator used to sort todos by their assigned user, in ascending order.
 * 
 * @author Julien Dubois
 */
public class TodoByAssignedUserAscComparator implements Comparator<Todo> {

    public int compare(Todo first, Todo second) {
        if (first.isCompleted() && !second.isCompleted()) {
            return 1;
        } else if (!first.isCompleted() && second.isCompleted()) {
            return -1;
        }

        if (first.getAssignedUser() != null && second.getAssignedUser() == null) {
            return 1;
        } else if (first.getAssignedUser() == null
                && second.getAssignedUser() != null) {
            return -1;
        }

        int order = 0;
        if (first.getAssignedUser() != null && second.getAssignedUser() != null) {
            order = second.getAssignedUser().getLogin().compareTo(
                    first.getAssignedUser().getLogin());
        }
        if (order == 0) {
            order = second.compareTo(first);
        }
        return order;
    }
}
