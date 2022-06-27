public class Epic extends Task {

    Integer subtaskID;

    public Epic(String name, String details, Integer iD, String status, Integer subtaskID) {
        super(name, details, iD, status);
        this.subtaskID = subtaskID;
    }
}
