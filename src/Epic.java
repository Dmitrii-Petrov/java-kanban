import java.util.ArrayList;

public class Epic extends Task {

    ArrayList<Integer> subtasksList = new ArrayList<>();

    public Epic(String name, String details) {
        super(name, details);
    }
}
