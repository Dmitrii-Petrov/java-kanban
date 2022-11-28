package managers;

import tasks.Epic;
import tasks.Subtask;
import tasks.Task;


import java.io.IOException;
import java.util.*;


public interface TaskManager {

    Task getTask(Integer taskID);

    Subtask getSubtask(Integer taskID);

    Epic getEpic(Integer taskID);

    ArrayList<Task> getTasksList();

    ArrayList<Task> getSubtasksList();

    ArrayList<Task> getEpicsList();

    void newTask(Task task) throws IOException;

    void newEpic(Epic epic) throws IOException;

    void newSubtask(Subtask subtask, Integer epicID) throws IOException;

    void updateTask(Task task, Integer id) throws IOException;

    void updateSubtask(Subtask subtask, Integer id) throws IOException;

    void updateEpic(Epic epic, Integer id) throws IOException;

    void deleteEverything() throws IOException;

    void deleteAllTasks() throws IOException;

    void deleteAllSubtasks() throws IOException;

    void deleteAllEpics() throws IOException;

    void deleteTask(Integer id) throws IOException;

    void deleteSubtask(Integer id) throws IOException;

    void deleteEpic(Integer id) throws IOException;

    List<Task> getHistory();

}

