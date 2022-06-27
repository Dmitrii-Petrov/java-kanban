public class Subtask extends Task {

    Integer epicID;

    public Subtask(String name, String details, Integer iD, String status, Integer epicID) {
        super(name, details, iD, status);
        this.epicID = epicID;
    }
}
