package tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {

    String name;
    String details;
    Integer id;
    TaskStatus status = TaskStatus.NEW;
    Duration duration;
    LocalDateTime startTime;


    public Task(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public Task(Integer id, String name, TaskStatus status, String details) {
        this.name = name;
        this.details = details;
        this.id = id;
        this.status = status;
    }

    public Task(Integer id, String name, TaskStatus status, String details, Integer duration, String startTime) {
        this.name = name;
        this.details = details;
        this.id = id;
        this.status = status;
        this.duration = Duration.ofMinutes(duration);
        this.startTime = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("dd_MM_yyyy|HH:mm"));
    }

    public Task( String name, TaskStatus status, String details, Integer duration, String startTime) {
        this.name = name;
        this.details = details;
        this.status = status;
        this.duration = Duration.ofMinutes(duration);
        this.startTime = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("dd_MM_yyyy|HH:mm"));
    }

    public LocalDateTime getEndTime() {
        return startTime.plus(duration);
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) && details.equals(task.details) && Objects.equals(id, task.id) && Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, details, id, status);
    }

    @Override
    public String toString() {
        return String.format("%d,%S,%s,%s,%s,%d,%s", id, this.getClass().getSimpleName(), name, status, details, duration.toMinutes(), startTime.format(DateTimeFormatter.ofPattern("dd_MM_yyyy|HH:mm")));
    }

}
