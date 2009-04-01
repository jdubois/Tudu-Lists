package tudu.domain.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import tudu.domain.dao.UserDAO;
import tudu.domain.model.User;

/**
 * Hibernate implementation of the tudu.domain.dao.UserDAO interface.
 * 
 * @author Julien Dubois
 */
@Repository
public class UserDAOJpa implements UserDAO {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /**
     * Get the number of users.
     * 
     * @see tudu.domain.dao.UserDAO#getNumberOfUsers()
     */
    public long getNumberOfUsers() {
        Query query = em.createNamedQuery("User.getNumberOfUsers");
        return (Long) query.getSingleResult();
    }

    /**
     * Get a specific user.
     * 
     * @see tudu.domain.dao.UserDAO#getUser(String)
     */
    public final User getUser(final String login) {
        return this.em.find(User.class, login);
    }

    /**
     * Find all users with a login starting with the "loginStart" string.
     * 
     * @see tudu.domain.dao.UserDAO#findUsersByLogin(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<User> findUsersByLogin(String loginStart) {
        Query query = em.createNamedQuery("User.findUsersByLogin");
        query.setParameter("login", loginStart + "%");
        query.setMaxResults(200);
        List<User> users = query.getResultList();
        return users;
    }

    /**
     * Save a user.
     * 
     * @see tudu.domain.dao.UserDAO#saveUser(tudu.domain.model.User)
     */
    public final void saveUser(final User user) {
        this.em.persist(user);
    }

    /**
     * Update a user.
     * 
     * @see tudu.domain.dao.UserDAO#updateUser(tudu.domain.model.User)
     */
    public final void updateUser(final User user) {
        this.em.merge(user);
    }
}
