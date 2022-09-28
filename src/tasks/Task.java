package tasks;

import java.util.Objects;

public class Task {

    String name;
    String details;
    Integer id;
    TaskStatus status = TaskStatus.NEW;

    public Task(String name, String details) {
        this.name = name;
        this.details = details;

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
        return "tasks.Task{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", iD=" + id +
                ", status=" + status +
                '}';
    }

}
