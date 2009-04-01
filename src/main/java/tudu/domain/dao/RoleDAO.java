package tudu.domain.dao;

import tudu.domain.model.Role;

/**
 * DAO for the Role table.
 * 
 * @author Julien Dubois
 */
public interface RoleDAO {

    /**
     * find a role by name.
     * 
     * @param roleName
     *            The role name
     * @return The role
     */
    Role getRole(String roleName);

    /**
     * Save a role.
     * 
     * @param role
     *            The role to save
     */
    void saveRole(Role role);
}
