import java.util.HashMap;

public class Manager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    Integer taskID = 1;


    public String[] getTasksList() {                                            //возвращаем список всех задач
        String[] taskList = new String[taskID];
        for (int i = 0; i < taskID; i++) {
            taskList[i] = tasks.get(i).getName();
        }
        return taskList;
    }

    public void newTask(Task task) {
        task.setiD(taskID);
        tasks.put(taskID, task);
        taskID++;
    }

    public void newEpic(Epic epic) {
        epic.setiD(taskID);
        epics.put(taskID, epic);
        tasks.put(taskID, epic);
        taskID++;
    }

    public void newSubtask(Subtask subtask) {
        subtask.setiD(taskID);
        subtasks.put(taskID, subtask);
        tasks.put(taskID, subtask);
        epics.get(subtask.epicID).subtasksList.add(taskID);                             //передаем эпику информацию о подзадаче
        taskID++;
    }




    public void deleteAllTasks() {
        for (Integer key : tasks.keySet()) {
            tasks.remove(key);
            taskID = 1;
        }
    }

    public Task getTask(Integer id) {
        return tasks.get(id);
    }
}

