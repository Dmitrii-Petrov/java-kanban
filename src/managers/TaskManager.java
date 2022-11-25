package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;


import java.util.*;


public interface TaskManager {

    Task getTask(Integer taskID);

    Subtask getSubtask(Integer taskID);

    Epic getEpic(Integer taskID);

    ArrayList<Task> getTasksList();

    ArrayList<Task> getSubtasksList();

    ArrayList<Task> getEpicsList();

    void newTask(Task task);

    void newEpic(Epic epic);

    void newSubtask(Subtask subtask, Integer epicID);

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

    List<Task> getHistory();

}

