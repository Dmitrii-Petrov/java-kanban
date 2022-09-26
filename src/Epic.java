import java.util.ArrayList;

public class Epic extends Task {

    ArrayList<Integer> subtasksList = new ArrayList<>();

    public Epic(String name, String details) {
        super(name, details);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", iD=" + iD +
                ", status=" + status +
                ", subtasksList=" + subtasksList +
                '}';
    }
}
