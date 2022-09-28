package tasks;

import java.util.HashMap;

public class Epic extends Task {

    public HashMap<Integer, Subtask> subtasksList = new HashMap<>();

    public Epic(String name, String details) {
        super(name, details);
    }

    @Override
    public String toString() {
        return "tasks.Epic{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", iD=" + id +
                ", status=" + status +
                ", subtasksList=" + subtasksList.keySet() +
                '}';
    }

    public void updateStatus() {
        int newTasks = 0;
        int doneTasks = 0;
        for (Subtask subtask : subtasksList.values()) {
            if (subtask.getStatus() == TaskStatus.NEW) {
                newTasks++;
            } else if (subtask.getStatus() == TaskStatus.DONE)
                doneTasks++;
        }
        if (subtasksList.size() == newTasks) {
            setStatus(TaskStatus.NEW);
        } else if (subtasksList.size() == doneTasks) {
            setStatus(TaskStatus.DONE);
        } else setStatus(TaskStatus.IN_PROGRESS);
    }
}
