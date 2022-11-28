package tasks;

import java.util.HashMap;

public class Epic extends Task {

    public HashMap<Integer, Subtask> subtasksList = new HashMap<>();

    public Epic(String name, String details) {
        super(name, details);
    }

    public Epic(Integer id, String name, TaskStatus status, String details) {
        super(id, name, status, details);

    }

    @Override
    public String toString() {
        return super.toString();
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
