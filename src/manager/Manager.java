package manager;

import tasks.*;


import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();


    private Integer taskID = 1;

    public Task getTask(Integer taskID) {
        return tasks.get(taskID);
    }

    public Subtask getSubtask(Integer taskID) {
        return subtasks.get(taskID);
    }

    public Epic getEpic(Integer taskID) {
        return epics.get(taskID);
    }

    public ArrayList<String> getTasksList() {
        ArrayList <String> taskList = new ArrayList<>();                        //возвращаем список всех задач
        for (Task task : tasks.values()) {
            taskList.add(task.getName());
        }
        return taskList;
    }
    public ArrayList<String> getSubtasksList() {
        ArrayList <String> taskList = new ArrayList<>();                        //возвращаем список всех задач
        for (Task task : subtasks.values()) {
            taskList.add(task.getName());
        }
        return taskList;
    }
    public ArrayList<String> getEpicsList() {
        ArrayList <String> taskList = new ArrayList<>();                        //возвращаем список всех задач
        for (Task task : epics.values()) {
            taskList.add(task.getName());
        }
        return taskList;
    }

    public Integer newTask(Task task) {
        task.setId(taskID);
        tasks.put(taskID, task);
        return taskID++;
    }

    public Integer newEpic(Epic epic) {
        epic.setId(taskID);
        epics.put(taskID, epic);
        return taskID++;
    }

    public Integer newSubtask(Subtask subtask, Integer epicID) {
        subtask.setId(taskID);
        subtask.setEpicID(epicID);
        subtasks.put(taskID, subtask);
        (epics.get(epicID)).subtasksList.put(taskID,subtask);                                                                //передаем эпику информацию о подзадаче
        (epics.get(epicID)).updateStatus();                                                                                         //меняем статус эпика
        return taskID++;
    }

    public void updateTask(Task task, Integer id) {
        task.setId(id);
        tasks.put(id, task);
    }

    public void updateSubtask(Subtask subtask, Integer id) {
        subtask.setId(id);
        subtasks.put(id, subtask);
        epics.get(subtask.getEpicID()).updateStatus();
    }

    public void updateEpic(Epic epic, Integer id) {
        epic.setId(id);
        epics.put(id, epic);
    }

    public void deleteEverything() {
        tasks.clear();
        subtasks.clear();
        epics.clear();
        taskID = 1;
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllSubtasks() {
        for (Subtask subtask : subtasks.values()) {
            if ((subtask.getEpicID()!=null)&&(epics.get(subtask.getEpicID())!=null)) {
                epics.get(subtask.getEpicID()).subtasksList.remove(subtask.getId());
            }
        }
        subtasks.clear();
    }

    public void deleteAllEpics() {
        for (Epic epic : epics.values()) {
            for (Integer id : epic.subtasksList.keySet()){
                subtasks.remove(id);
            }
        }
        epics.clear();
    }


    public void deleteTask(Integer id) {
        tasks.remove(id);
    }

    public void deleteSubtask(Integer id) {
        epics.get((subtasks.get(id)).getEpicID()).subtasksList.remove(id);
        subtasks.remove(id);
    }

    public void deleteEpic(Integer id) {
        for (Integer subtaskID : (epics.get(id)).subtasksList.keySet()){
            subtasks.remove(subtaskID);
        }
        epics.remove(id);
    }

}

