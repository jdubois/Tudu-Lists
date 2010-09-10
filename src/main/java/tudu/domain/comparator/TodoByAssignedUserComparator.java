package tudu.domain.comparator;

import tudu.domain.Todo;

import java.util.Comparator;

/**
 * Comparator used to sort todos by their assigned user.
 * 
 * @author Julien Dubois
 */
public class TodoByAssignedUserComparator implements Comparator<Todo> {

    public int compare(Todo first, Todo second) {
        if (first.isCompleted() && !second.isCompleted()) {
            return 1;
        } else if (!first.isCompleted() && second.isCompleted()) {
            return -1;
        }

        if (first.getAssignedUser() != null && second.getAssignedUser() == null) {
            return -1;
        } else if (first.getAssignedUser() == null
                && second.getAssignedUser() != null) {
            return 1;
        }

        int order = 0;
        if (first.getAssignedUser() != null && second.getAssignedUser() != null) {
            order = first.getAssignedUser().getLogin().compareTo(
                    second.getAssignedUser().getLogin());
        }
        if (order == 0) {
            order = first.compareTo(second);
        }
        return order;
    }
}
