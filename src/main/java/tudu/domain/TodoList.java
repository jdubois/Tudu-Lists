package tudu.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A Todo List.
 * 
 * @author Julien Dubois
 */
@Entity
@Table(name = "todo_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TodoList implements Serializable, Comparable<TodoList> {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 4048798961366546485L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String listId;

    private String name;

    private boolean rssAllowed;

    private Date lastUpdate;

    @OneToMany(mappedBy = "todoList")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Todo> todos = new HashSet<Todo>();

    @ManyToMany(mappedBy = "todoLists")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<User> users = new HashSet<User>();

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRssAllowed() {
        return rssAllowed;
    }

    public void setRssAllowed(boolean rssAllowed) {
        this.rssAllowed = rssAllowed;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<Todo> getTodos() {
        return todos;
    }

    public void setTodos(Set<Todo> todos) {
        this.todos = todos;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int compareTo(TodoList that) {
        return (this.getName().toLowerCase() + this.getListId()).compareTo(that
                .getName().toLowerCase()
                + that.getListId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TodoList)) {
            return false;
        }

        final TodoList todoList = (TodoList) o;

        if (listId != null ? !listId.equals(todoList.listId)
                : todoList.listId != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (listId != null ? listId.hashCode() : 0);
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "listId='" + listId + '\'' +
                ", name='" + name + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", todos=" + todos +
                ", users=" + users +
                '}';
    }
}
