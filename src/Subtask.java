public class Subtask extends Task {

    Integer epicID;

    public Subtask(String name, String details, Integer iD, Integer epicID) {
        super(name, details, iD);
        this.epicID = epicID;
    }
}
