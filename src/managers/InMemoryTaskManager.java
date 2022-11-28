package managers;

import tasks.*;

import java.io.IOException;
import java.util.*;


public class InMemoryTaskManager implements TaskManager {
     HashMap<Integer, Task> tasks = new HashMap<>();
     HashMap<Integer, Subtask> subtasks = new HashMap<>();
     HashMap<Integer, Epic> epics = new HashMap<>();

    HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();


    private Integer taskID = 1;

    @Override
    public  Task getTask(Integer taskID) {
        inMemoryHistoryManager.add(tasks.get(taskID));
        return tasks.get(taskID);
    }

    @Override
    public  Subtask getSubtask(Integer taskID) {
        inMemoryHistoryManager.add(subtasks.get(taskID));
        return subtasks.get(taskID);
    }

    @Override
    public  Epic getEpic(Integer taskID) {
        inMemoryHistoryManager.add(epics.get(taskID));
        return epics.get(taskID);
    }

    @Override
    public ArrayList<Task> getTasksList() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Task> getSubtasksList() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public ArrayList<Task> getEpicsList() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void newTask(Task task) throws IOException {
        task.setId(taskID);
        tasks.put(taskID, task);
        taskID++;
    }

    @Override
    public void newEpic(Epic epic) throws IOException {
        epic.setId(taskID);
        epics.put(taskID, epic);
        taskID++;
    }

    @Override
    public void newSubtask(Subtask subtask, Integer epicID) throws IOException {
        subtask.setId(taskID);
        subtask.setEpicID(epicID);
        subtasks.put(taskID, subtask);
        (epics.get(epicID)).subtasksList.put(taskID, subtask);                                                                //передаем эпику информацию о подзадаче
        (epics.get(epicID)).updateStatus();                                                                                         //меняем статус эпика
        taskID++;
    }

    @Override
    public void updateTask(Task task, Integer id) throws IOException {
        task.setId(id);
        tasks.put(id, task);
    }

    @Override
    public void updateSubtask(Subtask subtask, Integer id) throws IOException {
        subtask.setId(id);
        subtasks.put(id, subtask);
        epics.get(subtask.getEpicID()).updateStatus();
    }

    @Override
    public void updateEpic(Epic epic, Integer id) throws IOException {
        epic.setId(id);
        epics.put(id, epic);
    }

    @Override
    public void deleteEverything() throws IOException {
        tasks.clear();
        subtasks.clear();
        epics.clear();
        taskID = 1;
        inMemoryHistoryManager = Managers.getDefaultHistory();
    }

    @Override
    public void deleteAllTasks() throws IOException {
        for (Integer taskID : tasks.keySet()){
            inMemoryHistoryManager.remove(taskID);
        }
        tasks.clear();
    }

    @Override
    public void deleteAllSubtasks() throws IOException {
        for (Subtask subtask : subtasks.values()) {
            if ((subtask.getEpicID() != null) && (epics.get(subtask.getEpicID()) != null)) {
                epics.get(subtask.getEpicID()).subtasksList.remove(subtask.getId());
            }
        }
        for (Integer subtaskID : subtasks.keySet()){
            inMemoryHistoryManager.remove(subtaskID);
        }
        subtasks.clear();
    }

    @Override
    public void deleteAllEpics() throws IOException {
        for (Epic epic : epics.values()) {
            for (Integer id : epic.subtasksList.keySet()) {
                subtasks.remove(id);
            }
        }
        for (Integer epicID : epics.keySet()){
            inMemoryHistoryManager.remove(epicID);
        }
        epics.clear();
    }

    @Override
    public void deleteTask(Integer id) throws IOException {
        inMemoryHistoryManager.remove(id);
        tasks.remove(id);
    }

    @Override
    public void deleteSubtask(Integer id) throws IOException {
        epics.get((subtasks.get(id)).getEpicID()).subtasksList.remove(id);
        epics.get((subtasks.get(id)).getEpicID()).updateStatus();
        inMemoryHistoryManager.remove(id);
        subtasks.remove(id);
    }

    @Override
    public void deleteEpic(Integer id) throws IOException {
        for (Integer subtaskID : (epics.get(id)).subtasksList.keySet()) {
            subtasks.remove(subtaskID);
            inMemoryHistoryManager.remove(subtaskID);
        }
        inMemoryHistoryManager.remove(id);
        epics.remove(id);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return inMemoryHistoryManager.getTasks();
    }
}

