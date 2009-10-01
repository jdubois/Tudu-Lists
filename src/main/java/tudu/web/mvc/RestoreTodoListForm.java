package tudu.web.mvc;

/**
 * Form used by RestoreTodoListAction.
 *
 * @author Julien Dubois
 */
public class RestoreTodoListForm {

    private String listId;

    private String restoreChoice;

    private String backupFile;

    public String getRestoreChoice() {
        return restoreChoice;
    }

    public void setRestoreChoice(String restoreChoice) {
        this.restoreChoice = restoreChoice;
    }

    public String getBackupFile() {
        return backupFile;
    }

    public void setBackupFile(String backupFile) {
        this.backupFile = backupFile;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }
}
