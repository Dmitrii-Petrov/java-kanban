import java.util.Objects;

public class Task {

    String name;
    String details;
    Integer iD;
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

    public Integer getiD() {
        return iD;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
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
        return Objects.equals(name, task.name) && details.equals(task.details) && Objects.equals(iD, task.iD) && Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, details, iD, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", iD=" + iD +
                ", status=" + status +
                '}';
    }

    enum TaskStatus {
        NEW,
        IN_PROGRESS,
        DONE
    }

}
