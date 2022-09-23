public class Subtask extends Task {

    Integer epicID;

    public Subtask(String name, String details, Integer epicID) {
        super(name, details);
        this.epicID = epicID;
    }
}
