package tasks;

import java.time.format.DateTimeFormatter;

public class Subtask extends Task {

    Integer epicID;

    public Subtask(Integer id, String name, TaskStatus status, String details, Integer duration, String startTime, Integer epicID) {
        super(id, name, status, details, duration, startTime);
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
        return String.format("%d,%S,%s,%s,%s,%d,%s,%d", id, this.getClass().getSimpleName(), name, status, details,duration.toMinutes(),startTime.format(DateTimeFormatter.ofPattern("dd_MM_yyyy|HH:mm")), epicID);
    }
}
