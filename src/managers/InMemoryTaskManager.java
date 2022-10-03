package managers;

import tasks.*;

import java.util.*;


public class InMemoryTaskManager implements TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    HistoryManager inMemoryHistoryManager = (new Managers()).getDefaultHistory();


    private Integer taskID = 1;

    @Override
    public Task getTask(Integer taskID) {
        inMemoryHistoryManager.add(tasks.get(taskID));
        return tasks.get(taskID);
    }

    @Override
    public Subtask getSubtask(Integer taskID) {
        inMemoryHistoryManager.add(subtasks.get(taskID));
        return subtasks.get(taskID);
    }

    @Override
    public Epic getEpic(Integer taskID) {
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
    public Integer newTask(Task task) {
        task.setId(taskID);
        tasks.put(taskID, task);
        return taskID++;
    }

    @Override
    public Integer newEpic(Epic epic) {
        epic.setId(taskID);
        epics.put(taskID, epic);
        return taskID++;
    }

    @Override
    public Integer newSubtask(Subtask subtask, Integer epicID) {
        subtask.setId(taskID);
        subtask.setEpicID(epicID);
        subtasks.put(taskID, subtask);
        (epics.get(epicID)).subtasksList.put(taskID, subtask);                                                                //передаем эпику информацию о подзадаче
        (epics.get(epicID)).updateStatus();                                                                                         //меняем статус эпика
        return taskID++;
    }

    @Override
    public void updateTask(Task task, Integer id) {
        task.setId(id);
        tasks.put(id, task);
    }

    @Override
    public void updateSubtask(Subtask subtask, Integer id) {
        subtask.setId(id);
        subtasks.put(id, subtask);
        epics.get(subtask.getEpicID()).updateStatus();
    }

    @Override
    public void updateEpic(Epic epic, Integer id) {
        epic.setId(id);
        epics.put(id, epic);
    }

    @Override
    public void deleteEverything() {
        tasks.clear();
        subtasks.clear();
        epics.clear();
        taskID = 1;
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        for (Subtask subtask : subtasks.values()) {
            if ((subtask.getEpicID() != null) && (epics.get(subtask.getEpicID()) != null)) {
                epics.get(subtask.getEpicID()).subtasksList.remove(subtask.getId());
            }
        }
        subtasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        for (Epic epic : epics.values()) {
            for (Integer id : epic.subtasksList.keySet()) {
                subtasks.remove(id);
            }
        }
        epics.clear();
    }

    @Override
    public void deleteTask(Integer id) {
        tasks.remove(id);
    }

    @Override
    public void deleteSubtask(Integer id) {
        epics.get((subtasks.get(id)).getEpicID()).subtasksList.remove(id);
        subtasks.remove(id);
    }

    @Override
    public void deleteEpic(Integer id) {
        for (Integer subtaskID : (epics.get(id)).subtasksList.keySet()) {
            subtasks.remove(subtaskID);
        }
        epics.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        return inMemoryHistoryManager.getHistory();
    }
}
