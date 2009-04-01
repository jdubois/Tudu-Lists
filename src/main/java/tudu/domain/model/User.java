package tudu.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A user.
 * 
 * @author Julien Dubois
 */
@Entity
@Table(name = "tuser")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries( {
        @NamedQuery(name = "User.getNumberOfUsers", query = "SELECT COUNT(user) FROM User user"),
        @NamedQuery(name = "User.findUsersByLogin", query = "SELECT user FROM User user where user.login LIKE :login") })
public class User implements Serializable, Comparable<User> {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 4048798961366546485L;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Date creationDate;

    private Date lastAccessDate;

    private boolean enabled;

    private String dateFormat;

    private Set<Role> roles = new HashSet<Role>();

    private Set<TodoList> todoLists = new HashSet<TodoList>();

    @Id
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Set<TodoList> getTodoLists() {
        return todoLists;
    }

    public void setTodoLists(Set<TodoList> todoLists) {
        this.todoLists = todoLists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        final User user = (User) o;

        if (!login.equals(user.login)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    public int compareTo(User that) {
        return this.getLogin().compareTo(that.getLogin());
    }
}
