package tudu.service;

import tudu.domain.Property;

/**
 * Manage the application configuration.
 * 
 * @author Julien Dubois
 */
public interface ConfigurationService {

    /**
     * Initialize the database.
     */
    void initDatabase();

    /**
     * Initialize the application properties.
     */
    void initApplicationProperties();

    /**
     * Find a property by key.
     * 
     * @param key
     *            The property key
     * @return The property
     */
    Property getProperty(String key);

    /**
     * Update email properties.
     * 
     * @param smtpHost
     *            SMTP host
     * @param smtpPort
     *            SMTP port
     * @param smtpUser
     *            SMTP user
     * @param smtpPassword
     *            SMTP password
     * @param smtpFrom
     *            From address of the emails sent by the application
     */
    void updateEmailProperties(String smtpHost, String smtpPort,
            String smtpUser, String smtpPassword, String smtpFrom);

    /**
     * Update the application properties.
     * 
     * @param staticPath
     *            The path to the static files
     * @param googleKey
     *            The Google Analytics key used to track users
     */
    void updateApplicationProperties(String staticPath, String googleKey);
}
