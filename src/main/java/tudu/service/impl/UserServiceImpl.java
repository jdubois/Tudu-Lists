package tudu.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tudu.Constants;
import tudu.domain.*;
import tudu.service.UserAlreadyExistsException;
import tudu.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the tudu.service.UserService interface.
 *
 * @author Julien Dubois
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Log log = LogFactory.getLog(UserServiceImpl.class);


    @PersistenceContext
    private EntityManager em;

    /**
     * Get the number of users.
     *
     * @see tudu.service.UserService#getNumberOfUsers()
     */
    @Transactional(readOnly = true)
    public long getNumberOfUsers() {
        Query query = em.createNamedQuery("User.getNumberOfUsers");
        return (Long) query.getSingleResult();
    }

    /**
     * Find a user by login.
     *
     * @see tudu.service.UserService#findUser(String)
     */
    @Transactional(readOnly = true)
    public User findUser(String login) {
        User user = em.find(User.class, login);
        if (user == null) {
            if (log.isDebugEnabled()) {
                log.debug("Could not find User ID=" + login);
            }
            throw new ObjectRetrievalFailureException(User.class, login);
        }
        if (log.isDebugEnabled()) {
            log.debug("User ID=" + login + " found, user is called "
                    + user.getFirstName() + " " + user.getLastName());
        }
        return user;
    }

    /**
     * Find all users with a login starting with the "loginStart" string.
     *
     * @see tudu.service.UserService#findUsersByLogin(java.lang.String)
     */
    @Transactional(readOnly = true)
    public List<User> findUsersByLogin(String loginStart) {
        Query query = em.createNamedQuery("User.findUsersByLogin");
        query.setParameter("login", loginStart + "%");
        query.setMaxResults(200);
        List<User> users = query.getResultList();
        return users;
    }

    /**
     * Find the current Tudu List user.
     *
     * @see tudu.service.UserService#getCurrentUser()
     */
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        org.springframework.security.core.userdetails.User springSecurityUser = (org.springframework.security.core.userdetails.User) securityContext
                .getAuthentication().getPrincipal();

        return this.findUser(springSecurityUser.getUsername());
    }

    /**
     * Update a user's information.
     */
    public void updateUser(User user) {
        if (log.isDebugEnabled()) {
            log.debug("Updating user '" + user.getLogin() + "'.");
        }
        em.merge(user);
    }

    /**
     * Enable a user account.
     *
     * @param login The user login
     */
    public void enableUser(String login) {
        User user = this.findUser(login);
        user.setEnabled(true);
    }

    /**
     * Disable a user account.
     *
     * @param login The user login
     */
    public void disableUser(String login) {
        User user = this.findUser(login);
        user.setEnabled(false);
    }

    /**
     * Create a new user.
     *
     * @see tudu.service.UserService#createUser(tudu.domain.User)
     */
    public void createUser(User user) throws UserAlreadyExistsException {
        if (log.isDebugEnabled()) {
            log.debug("Creating user '" + user.getLogin() + "'.");
        }

        User testUser = em.find(User.class, user.getLogin());
        if (testUser != null) {
            if (log.isDebugEnabled()) {
                log.debug("User login '" + user.getLogin()
                        + "' already exists.");
            }
            throw new UserAlreadyExistsException("User already exists.");
        }
        user.setEnabled(true);
        Date now = Calendar.getInstance().getTime();
        user.setCreationDate(now);
        user.setLastAccessDate(now);
        user.setDateFormat(Constants.DATEFORMAT_US);
        Role role = em.find(Role.class, RolesEnum.ROLE_USER.name());
        user.getRoles().add(role);
        em.persist(user);

        TodoList todoList = new TodoList();
        todoList.setName("Welcome!");
        todoList.setLastUpdate(Calendar.getInstance().getTime());
        em.persist(todoList);
        user.getTodoLists().add(todoList);
        todoList.getUsers().add(user);

        Todo welcomeTodo = new Todo();
        welcomeTodo.setDescription("Welcome to Tudu Lists!");
        welcomeTodo.setPriority(100);
        welcomeTodo.setCreationDate(now);
        welcomeTodo.setCompletionDate(now);
        welcomeTodo.setTodoList(todoList);
        todoList.getTodos().add(welcomeTodo);
        em.persist(welcomeTodo);
        if (log.isDebugEnabled()) {
            log.debug("User '" + user.getLogin() + "' successfully created.");
        }
    }

    /**
     * @see tudu.service.UserService#sendPassword(tudu.domain.User)
     */
    @Transactional(readOnly = true)
    public void sendPassword(User user) {
        if (log.isDebugEnabled()) {
            log.debug("Send password of user '" + user.getLogin() + "'.");
        }
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        Property smtpHost = em.find(Property.class, "smtp.host");
        sender.setHost(smtpHost.getValue());
        Property smtpPort = em.find(Property.class, "smtp.port");
        int port = 25;
        try {
            port = Integer.parseInt(smtpPort.getValue());
        } catch (NumberFormatException e) {
            log.error("The supplied SMTP port is not a number.");
        }
        sender.setPort(port);
        Property smtpUser = em.find(Property.class, "smtp.user");
        sender.setUsername(smtpUser.getValue());
        Property smtpPassword = em.find(Property.class, "smtp.password");
        sender.setPassword(smtpPassword.getValue());

        SimpleMailMessage message = new SimpleMailMessage();
        Property smtpFrom = em.find(Property.class, "smtp.from");
        message.setTo(user.getEmail());
        message.setFrom(smtpFrom.getValue());
        message.setSubject("Your Tudu Lists password");
        message
                .setText("Dear "
                        + user.getFirstName()
                        + " "
                        + user.getLastName()
                        + ",\n\n"
                        + "Your Tudu Lists password is \""
                        + user.getPassword()
                        + "\".\n"
                        + "Now that this password has been sent by e-mail, we recommend that "
                        + "you change it as soon as possible.\n\n"
                        + "Regards,\n\n" + "Tudu Lists.");

        sender.send(message);
    }
}
