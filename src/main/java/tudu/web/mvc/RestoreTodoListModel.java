package tudu.web.mvc;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Form used by RestoreController.
 *
 * @author Julien Dubois
 */
public class RestoreTodoListModel {

    @NotNull
    private String listId;

    @NotNull
    private String restoreChoice;

    @NotNull
    private MultipartFile backupFile;

    public String getRestoreChoice() {
        return restoreChoice;
    }

    public void setRestoreChoice(String restoreChoice) {
        this.restoreChoice = restoreChoice;
    }

    public MultipartFile getBackupFile() {
        return backupFile;
    }

    public void setBackupFile(MultipartFile backupFile) {
        this.backupFile = backupFile;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestoreTodoListModel that = (RestoreTodoListModel) o;

        if (backupFile != null ? !backupFile.equals(that.backupFile) : that.backupFile != null) return false;
        if (listId != null ? !listId.equals(that.listId) : that.listId != null) return false;
        if (restoreChoice != null ? !restoreChoice.equals(that.restoreChoice) : that.restoreChoice != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = listId != null ? listId.hashCode() : 0;
        result = 31 * result + (restoreChoice != null ? restoreChoice.hashCode() : 0);
        result = 31 * result + (backupFile != null ? backupFile.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RestoreTodoListModel{" +
                "listId='" + listId + '\'' +
                ", restoreChoice='" + restoreChoice + '\'' +
                ", backupFile=" + backupFile +
                '}';
    }
}
