package tudu.domain.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import tudu.domain.dao.RoleDAO;
import tudu.domain.model.Role;

/**
 * Hibernate implementation of the tudu.domain.dao.RoleDAO interface.
 * 
 * @author Julien Dubois
 */
@Repository
public class RoleDAOJpa implements RoleDAO {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    /***************************************************************************
     * @see tudu.domain.dao.RoleDAO#getRole(java.lang.String)
     */
    public Role getRole(String roleName) {
        Role role = this.em.find(Role.class, roleName);
        if (role == null) {
            throw new ObjectRetrievalFailureException(Role.class, roleName);
        }
        return role;
    }

    public void saveRole(Role role) {
        this.em.persist(role);
        em.flush();
    }

}
