package tudu.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tudu.domain.RolesEnum;
import tudu.domain.dao.PropertyDAO;
import tudu.domain.dao.RoleDAO;
import tudu.domain.model.Property;
import tudu.domain.model.Role;
import tudu.domain.model.User;
import tudu.service.ConfigurationManager;
import tudu.service.UserAlreadyExistsException;
import tudu.service.UserManager;

import java.util.Set;

/**
 * Implementation of the tudu.service.ConfigurationManager interface.
 * 
 * @author Julien Dubois
 */
@Service
@Transactional
public class ConfigurationManagerImpl implements ConfigurationManager,
        ApplicationListener {

    public static String staticFilesPath = "";

    public static String googleAnalyticsKey = "";

    private final Log log = LogFactory.getLog(ConfigurationManagerImpl.class);

    @Autowired
    private PropertyDAO propertyDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private UserManager userManager;

    /**
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            log.warn("Spring context is started : " + event.toString());
            this.initDatabase();
            this.initApplicationProperties();
        }
    }

    /**
     * @see tudu.service.ConfigurationManager#initDatabase()
     */
    public void initDatabase() {
        log.warn("Testing Database.");
        try {
            roleDAO.getRole(RolesEnum.ROLE_USER.name());
        } catch (ObjectRetrievalFailureException erfe) {
            log.warn("Database is empty : populating with default values.");

            log.warn("Populating HSQLDB database.");
            Property hostProperty = new Property();
            hostProperty.setKey("smtp.host");
            hostProperty.setValue("");
            propertyDAO.saveProperty(hostProperty);
            Property portProperty = new Property();
            portProperty.setKey("smtp.port");
            portProperty.setValue("25");
            propertyDAO.saveProperty(portProperty);
            Property userProperty = new Property();
            userProperty.setKey("smtp.user");
            userProperty.setValue("");
            propertyDAO.saveProperty(userProperty);
            Property passwordProperty = new Property();
            passwordProperty.setKey("smtp.password");
            passwordProperty.setValue("");
            propertyDAO.saveProperty(passwordProperty);
            Property fromProperty = new Property();
            fromProperty.setKey("smtp.from");
            fromProperty.setValue("");
            propertyDAO.saveProperty(fromProperty);

            Role userRole = new Role();
            userRole.setRole(RolesEnum.ROLE_USER.name());
            roleDAO.saveRole(userRole);
            Role adminRole = new Role();
            adminRole.setRole(RolesEnum.ROLE_ADMIN.name());
            roleDAO.saveRole(adminRole);

            User adminUser = new User();
            adminUser.setLogin("admin");
            adminUser.setPassword("admin");
            adminUser.setFirstName("Albert");
            adminUser.setLastName("Dmin");
            adminUser.setDateFormat("MM/dd/yyyy");
            try {
                userManager.createUser(adminUser);
            } catch (UserAlreadyExistsException e) {
                log.error("Error while creating the admin user :"
                        + " the user already exists.");
            }
            Set<Role> roles = adminUser.getRoles();
            roles.add(adminRole);
            userManager.updateUser(adminUser);

            User user = new User();
            user.setLogin("user");
            user.setPassword("user");
            user.setFirstName("");
            user.setDateFormat("MM/dd/yyyy");
            try {
                userManager.createUser(user);
            } catch (UserAlreadyExistsException e) {
                log.error("Error while creating the admin user : "
                        + "the user already exists.");
            }
        }
        
    }

    /**
     * @see tudu.service.ConfigurationManager#initApplicationProperties()
     */
    public void initApplicationProperties() {
        Property staticFilesPathProperty = this
                .getProperty("application.static.path");

        if (staticFilesPathProperty != null) {
            staticFilesPath = staticFilesPathProperty.getValue();

        } else {
            staticFilesPathProperty = new Property();
            staticFilesPathProperty.setKey("application.static.path");
            staticFilesPathProperty.setValue(staticFilesPath);
            this.setProperty(staticFilesPathProperty);
        }

        Property googleAnalyticsKeyProperty = this
                .getProperty("google.analytics.key");

        if (googleAnalyticsKeyProperty != null) {
            googleAnalyticsKey = googleAnalyticsKeyProperty.getValue();

        } else {
            googleAnalyticsKeyProperty = new Property();
            googleAnalyticsKeyProperty.setKey("google.analytics.key");
            googleAnalyticsKeyProperty.setValue(googleAnalyticsKey);
            this.setProperty(googleAnalyticsKeyProperty);
        }
    }

    /**
     * @see tudu.service.ConfigurationManager#getProperty(java.lang.String)
     */
    @Transactional(readOnly = true)
    public Property getProperty(String key) {
        return propertyDAO.getProperty(key);
    }

    /**
     * @see tudu.service.ConfigurationManager#updateEmailProperties(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public void updateEmailProperties(String smtpHost, String smtpPort,
            String smtpUser, String smtpPassword, String smtpFrom) {

        Property hostProperty = propertyDAO.getProperty("smtp.host");
        hostProperty.setValue(smtpHost);
        propertyDAO.updateProperty(hostProperty);
        Property portProperty = propertyDAO.getProperty("smtp.port");
        portProperty.setValue(smtpPort);
        propertyDAO.updateProperty(portProperty);
        Property userProperty = propertyDAO.getProperty("smtp.user");
        userProperty.setValue(smtpUser);
        propertyDAO.updateProperty(userProperty);
        Property passwordProperty = propertyDAO.getProperty("smtp.password");
        passwordProperty.setValue(smtpPassword);
        propertyDAO.updateProperty(passwordProperty);
        Property fromProperty = propertyDAO.getProperty("smtp.from");
        fromProperty.setValue(smtpFrom);
        propertyDAO.updateProperty(fromProperty);
    }

    /**
     * @see tudu.service.ConfigurationManager#updateApplicationProperties(java.lang.String,
     *      java.lang.String)
     */
    public void updateApplicationProperties(String staticPath, String googleKey) {
        Property pathProperty = new Property();
        pathProperty.setKey("application.static.path");
        pathProperty.setValue(staticPath);
        this.setProperty(pathProperty);
        staticFilesPath = staticPath;

        Property googleProperty = new Property();
        googleProperty.setKey("google.analytics.key");
        googleProperty.setValue(googleKey);
        this.setProperty(googleProperty);
        googleAnalyticsKey = googleKey;
    }

    /**
     * Set a property.
     * <p>
     * If the property doesn't exist yet, it is created.
     * </p>
     * 
     * @param property
     *            The property
     */
    private void setProperty(Property property) {
        Property databaseProperty = this.getProperty(property.getKey());
        if (databaseProperty == null) {
            this.propertyDAO.saveProperty(property);
        } else {
            databaseProperty.setValue(property.getValue());
        }
    }
}
