package tudu.domain.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tudu.domain.dao.PropertyDAO;
import tudu.domain.model.Property;

/**
 * Hibernate implementation of the tudu.domain.dao.PropertyDAO interface.
 * 
 * @author Julien Dubois
 */
@Repository
public class PropertyDAOJpa implements PropertyDAO {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public Property getProperty(String key) {
        return this.em.find(Property.class, key);
    }

    /**
     * @see tudu.domain.dao.PropertyDAO#updateProperty(tudu.domain.model.Property)
     */
    public void updateProperty(Property property) {
        this.em.merge(property);
    }

    public void saveProperty(Property property) {
        this.em.persist(property);
    }

}
