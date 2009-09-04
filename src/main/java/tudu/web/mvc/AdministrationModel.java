package tudu.web.mvc;

/**
 * Model class for the administration pages.
 *
 * @author Julien Dubois
 */
public class AdministrationModel {

    private String action;

    private String propertyStaticPath;

    private String googleAnalyticsKey;

    private String smtpHost;

    private String smtpPort;

    private String smtpUser;

    private String smtpPassword;

    private String smtpFrom;

    private String searchLogin;

    private String login;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPropertyStaticPath() {
        return propertyStaticPath;
    }

    public void setPropertyStaticPath(String propertyStaticPath) {
        this.propertyStaticPath = propertyStaticPath;
    }

    public String getGoogleAnalyticsKey() {
        return googleAnalyticsKey;
    }

    public void setGoogleAnalyticsKey(String googleAnalyticsKey) {
        this.googleAnalyticsKey = googleAnalyticsKey;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUser() {
        return smtpUser;
    }

    public void setSmtpUser(String smtpUser) {
        this.smtpUser = smtpUser;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public String getSmtpFrom() {
        return smtpFrom;
    }

    public void setSmtpFrom(String smtpFrom) {
        this.smtpFrom = smtpFrom;
    }

     public String getSearchLogin() {
        return searchLogin;
    }

    public void setSearchLogin(String searchLogin) {
        this.searchLogin = searchLogin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
