import tasks.*;


import java.util.HashMap;

public class Manager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();


    private Integer taskID = 0;

    public Task getTask(Integer taskID) {
        return tasks.get(taskID);
    }

    public Subtask getSubtask(Integer taskID) {
        return subtasks.get(taskID);
    }

    public Epic getEpic(Integer taskID) {
        return epics.get(taskID);
    }

    public String getTasksList() {
        StringBuilder taskList = new StringBuilder();                        //возвращаем список всех задач
        for (Task task : tasks.values()) {
            taskList.append(task.getName()).append(" ");
        }
        return taskList.toString();
    }
    public String getSubtasksList() {
        StringBuilder taskList = new StringBuilder();                        //возвращаем список всех задач
        for (Task task : subtasks.values()) {
            taskList.append(task.getName()).append(" ");
        }
        return taskList.toString();
    }
    public String getEpicsList() {
        StringBuilder taskList = new StringBuilder();                        //возвращаем список всех задач
        for (Task task : epics.values()) {
            taskList.append(task.getName()).append(" ");
        }
        return taskList.toString();
    }

    public void newTask(Task task) {
        task.setId(taskID);
        tasks.put(taskID, task);
        taskID++;
    }

    public void newEpic(Epic epic) {
        epic.setId(taskID);
        epics.put(taskID, epic);
        taskID++;
    }

    public void newSubtask(Subtask subtask, Integer epicID) {
        subtask.setId(taskID);
        subtask.setEpicID(epicID);
        subtasks.put(taskID, subtask);
        (epics.get(epicID)).subtasksList.put(taskID,subtask);                                                                //передаем эпику информацию о подзадаче
        (epics.get(epicID)).updateStatus();                                                                                         //меняем статус эпика
        taskID++;
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
        for (Integer key : tasks.keySet()) {
            tasks.remove(key);
        }
        for (Integer key : subtasks.keySet()) {
            subtasks.remove(key);
        }
        for (Integer key : epics.keySet()) {
            epics.remove(key);
        }
        taskID = 0;
    }

    public void deleteAllTasks() {
        for (Integer key : tasks.keySet()) {
            tasks.remove(key);
        }
    }

    public void deleteAllSubtasks() {
        for (Integer key : subtasks.keySet()) {
            subtasks.remove(key);
        }
    }

    public void deleteAllEpics() {
        for (Integer key : epics.keySet()) {
            epics.remove(key);
        }
    }


    public void deleteTask(Integer id) {
        tasks.remove(id);
    }

    public void deleteSubtask(Integer id) {
        epics.get(subtasks.get(id).getEpicID()).subtasksList.remove(id);
        subtasks.remove(id);
    }

    public void deleteEpic(Integer id) {
        epics.remove(id);
    }

}

