package tudu.web.dwr.bean;

import java.io.Serializable;

public class RemoteTodoList implements Serializable, Comparable<RemoteTodoList> {

    private static final long serialVersionUID = -158971969843741512L;

    private String listId;

    private String name;

    private String description;

    private boolean rssAllowed;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public int compareTo(RemoteTodoList that) {
        return (this.getName().toLowerCase() + this.getListId()).compareTo(that
                .getName().toLowerCase()
                + that.getListId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RemoteTodoList)) {
            return false;
        }

        final RemoteTodoList that = (RemoteTodoList) o;

        return !(listId != null ? !listId.equals(that.listId)
                : that.listId != null);

    }

    @Override
    public int hashCode() {
        return (listId != null ? listId.hashCode() : 0);
    }
}
