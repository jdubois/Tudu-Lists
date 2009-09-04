package tudu.domain.dao;

import tudu.domain.model.User;

import java.util.List;

/**
 * DAO for the User table.
 * 
 * @author Julien Dubois
 */
public interface UserDAO {

    /**
     * Get the number of users.
     */
    long getNumberOfUsers();

    /**
     * Get a specific user.
     * 
     * @param login
     *            The user login
     * @return A user
     */
    User getUser(String login);

    /**
     * Find all users with a login starting with the "loginStart" string.
     */
    List<User> findUsersByLogin(String loginStart);

    /**
     * Update a user.
     * 
     * @param user
     *            The user value object
     */
    void updateUser(User user);

    /**
     * Save a user.
     * 
     * @param user
     *            The user value object
     */
    void saveUser(User user);
}
