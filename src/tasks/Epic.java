package tasks;

import java.time.Duration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {

    LocalDateTime endTime;
    public HashMap<Integer, Subtask> subtasksList = new HashMap<>();

    public Epic(Integer id, String name, TaskStatus status, String details, Integer duration, String startTime) {
        super(id, name, status, details, duration, startTime);

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public ArrayList<Subtask> getEpicsSubtasks (){
        return new ArrayList<>(subtasksList.values());
    }
    public void updateEpic() {
        int newTasks = 0;
        int doneTasks = 0;
        for (Subtask subtask : subtasksList.values()) {
            if (subtask.getStatus() == TaskStatus.NEW) {
                newTasks++;
            } else if (subtask.getStatus() == TaskStatus.DONE) doneTasks++;
        }
        if (subtasksList.size() == newTasks) {
            setStatus(TaskStatus.NEW);
        } else if (subtasksList.size() == doneTasks) {
            setStatus(TaskStatus.DONE);
        } else setStatus(TaskStatus.IN_PROGRESS);

        LocalDateTime startTime = this.startTime;
        LocalDateTime endTime = this.endTime;
        Duration duration = Duration.ofSeconds(0);

        for (Subtask subtask : subtasksList.values()) {
            if (startTime.isAfter(subtask.startTime)) {
                startTime = subtask.startTime;
            }
            if (getEndTime().isBefore(subtask.getEndTime())) {
                endTime = subtask.getEndTime();
            }
            duration = duration.plus(subtask.duration);
        }
        if (duration.isZero()) {
            duration = Duration.between(startTime, endTime);
        }
    }
}
