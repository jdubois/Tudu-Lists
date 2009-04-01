package tudu.domain.dao;

import tudu.domain.model.Property;

/**
 * DAO for the Property table.
 * 
 * @author Julien Dubois
 */
public interface PropertyDAO {

    /**
     * Find a property by key.
     * 
     * @param key
     *            The property key
     * @return The property
     */
    Property getProperty(String key);

    /**
     * Update a property.
     * 
     * @param property
     *            The property to update
     */
    void updateProperty(Property property);

    /**
     * Save a property.
     * 
     * @param property
     *            The property to save
     */
    void saveProperty(Property property);
}
