package tudu.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Id
    @Size(min = 1, max = 50)
    private String login;

    @Size(min = 5, max = 50)
    private String password;

    @Transient
    @Size(min = 5, max = 50)
    private String verifyPassword;

    @Size(min = 1, max = 60)
    private String firstName;

    @Size(min = 1, max = 60)
    private String lastName;

    @Size(min = 0, max = 150)
    @Email
    private String email;

    private Date creationDate;

    private Date lastAccessDate;

    private boolean enabled;

    private String dateFormat;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Role> roles = new HashSet<Role>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TodoList> todoLists = new HashSet<TodoList>();

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

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
