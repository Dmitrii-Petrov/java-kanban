package tasks;

public class Subtask extends Task {

    Integer epicID;

    public Subtask(String name, String details) {
        super(name, details);
    }

    public Subtask(Integer id, String name, TaskStatus status, String details, Integer epicID) {
        super(id, name, status, details);
        this.epicID = epicID;
    }

    public Integer getEpicID() {
        return epicID;
    }

    public void setEpicID(Integer epicID) {
        this.epicID = epicID;
    }

    @Override
    public String toString() {
        return String.format("%d,%S,%s,%s,%s,%d", id, this.getClass().getSimpleName(), name, status, details, epicID);
    }
}
