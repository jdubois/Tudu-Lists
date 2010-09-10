package tudu.service;

import tudu.domain.User;

import java.util.List;

/**
 * Manage the security: user authentification and autorizations.
 * 
 * @author Julien Dubois
 */
public interface UserService {

    /**
     * Get the number of users.
     */
    long getNumberOfUsers();

    /**
     * Find a user by login.
     * 
     * @param login
     *            The user login
     * @return The user value object
     */
    User findUser(String login);

    /**
     * Find all users with a login starting with the "loginStart" string.
     */
    List<User> findUsersByLogin(String loginStart);

    /**
     * Find the current user.
     * <p>
     * This method relies on Acegy Security.
     * </p>
     * 
     * @return The current user.
     */
    User getCurrentUser();

    /**
     * Update a user's information.
     *
     * @param user
     *            The user to update
     */
    void updateUser(User user);

    /**
     * Enable a user account.
     * 
     * @param login
     *            The user login
     */
    void enableUser(String login);

    /**
     * Disable a user account.
     * 
     * @param login
     *            The user login
     */
    void disableUser(String login);

    /**
     * Create a new user.
     * 
     * @param user
     *            The user to create
     */
    void createUser(User user) throws UserAlreadyExistsException;

    /**
     * Send a user's password by email.
     * 
     * @param user
     *            The user
     */
    void sendPassword(User user);
}
