package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileBackedTasksManager extends InMemoryTaskManager {
private Path path;


    public FileBackedTasksManager(Path path) {
        this.path = path;
    }

    void save() {


    }

    public Task fromString(String value){
        String[] string = value.split(",");
        return new Task(Integer.parseInt(string[0]), string[2],TaskStatus.valueOf(string[2]), string[4]);
    }



    @Override
    public void newTask(Task task) {
        super.newTask(task);
        save();
    }

    @Override
    public void newEpic(Epic epic) {
        super.newEpic(epic);
        save();
    }

    @Override
    public void newSubtask(Subtask subtask, Integer epicID) {
        super.newSubtask(subtask, epicID);
        save();
    }

    @Override
    public void updateTask(Task task, Integer id) {
        super.updateTask(task, id);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask, Integer id) {
        super.updateSubtask(subtask, id);
        save();
    }

    @Override
    public void updateEpic(Epic epic, Integer id) {
        super.updateEpic(epic, id);
        save();
    }

    @Override
    public void deleteEverything() {
        super.deleteEverything();
        save();
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteAllSubtasks() {
        super.deleteAllSubtasks();
        save();
    }

    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    @Override
    public void deleteTask(Integer id) {
        super.deleteTask(id);
        save();
    }

    @Override
    public void deleteSubtask(Integer id) {
        super.deleteSubtask(id);
        save();
    }

    @Override
    public void deleteEpic(Integer id) {
        super.deleteEpic(id);
        save();
    }
}
