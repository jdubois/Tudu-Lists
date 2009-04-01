package tudu.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Todo.
 * 
 * @author Julien Dubois
 */
@Entity
@Table(name = "todo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Todo implements Serializable, Comparable<Todo> {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 4048798961366546485L;

    private String todoId;

    private TodoList todoList;

    private Date creationDate;

    private String description;

    private int priority;

    private boolean completed;

    private Date completionDate;

    private Date dueDate;

    private User assignedUser;

    private String notes;

    private boolean hasNotes;

    private Set<Todo> subTodos = new HashSet<Todo>();

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @ManyToOne
    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public boolean isHasNotes() {
        return hasNotes;
    }

    public void setHasNotes(boolean hasNotes) {
        this.hasNotes = hasNotes;
    }

    /**
     * The length of this field is 10000, which is OK with MySQL but which will
     * cause trouble with other databases (Oracle is limited at 4000 characters,
     * SQL Server at 8000).
     */
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 10000)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Transient
    public Set<Todo> getSubTodos() {
        return subTodos;
    }

    public void setSubTodos(Set<Todo> subTodos) {
        this.subTodos = subTodos;
    }

    @ManyToOne
    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    public int compareTo(Todo that) {
        int order = that.getPriority() - this.getPriority();
        if (this.isCompleted()) {
            order += 10000;
        }
        if (that.isCompleted()) {
            order -= 10000;
        }
        if (order == 0) {
            order = (this.getDescription() + this.getTodoId()).compareTo(that
                    .getDescription()
                    + that.getTodoId());
        }
        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }

        final Todo todo = (Todo) o;

        if (todoId != null ? !todoId.equals(todo.todoId) : todo.todoId != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (todoId != null ? todoId.hashCode() : 0);
    }
}
