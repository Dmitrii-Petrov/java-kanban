package tasks;

public class Subtask extends Task {

    Integer epicID;

    public Subtask(String name, String details) {
        super(name, details);
    }

    public Integer getEpicID() {
        return epicID;
    }

    public void setEpicID(Integer epicID) {
        this.epicID = epicID;
    }

    @Override
    public String toString() {
        return "tasks.Subtask{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", iD=" + id +
                ", status=" + status +
                ", epicID=" + epicID +
                '}';
    }
}
