package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

public interface TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    Integer taskID = 1;

    Task getTask(Integer taskID);

    Subtask getSubtask(Integer taskID);

    Epic getEpic(Integer taskID);

    ArrayList<String> getTasksList();

    ArrayList<String> getSubtasksList();

    ArrayList<String> getEpicsList();

    Integer newTask(Task task);

    Integer newEpic(Epic epic);

    Integer newSubtask(Subtask subtask, Integer epicID);

    void updateTask(Task task, Integer id);

    void updateSubtask(Subtask subtask, Integer id);

    void updateEpic(Epic epic, Integer id);

    void deleteEverything();

    void deleteAllTasks();

    void deleteAllSubtasks();

    void deleteAllEpics();

    void deleteTask(Integer id);

    void deleteSubtask(Integer id);

    void deleteEpic(Integer id);

    ArrayList<Task> getHistory();

}

