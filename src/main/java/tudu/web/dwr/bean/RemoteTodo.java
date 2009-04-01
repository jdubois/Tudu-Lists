package tudu.web.dwr.bean;

import java.io.Serializable;

public class RemoteTodo implements Serializable {

    private static final long serialVersionUID = 2801256525807891607L;

    private String description;

    private int priority;

    private String dueDate;

    private boolean hasNotes;

    private String notes;

    private String assignedUserLogin;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isHasNotes() {
        return hasNotes;
    }

    public void setHasNotes(boolean hasNotes) {
        this.hasNotes = hasNotes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAssignedUserLogin() {
        return assignedUserLogin;
    }

    public void setAssignedUserLogin(String assignedUserLogin) {
        this.assignedUserLogin = assignedUserLogin;
    }
}
