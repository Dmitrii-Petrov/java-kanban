import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    private Integer taskID = 0;


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
        tasks.put(taskID, epic);
        taskID++;
    }

    public void newSubtask(Subtask subtask, Integer epicID) {
        subtask.setiD(taskID);
        tasks.put(taskID, subtask);
        ((Epic) tasks.get(epicID)).subtasksList.add(taskID);                                                                //передаем эпику информацию о подзадаче
        epicStatusUpdate(epicID);                                                                                           //меняем статус эпика
        taskID++;
    }

    public void updateTask(Task task, Integer iD) {
        task.setiD(iD);
        tasks.put(iD, task);
        if (task.getClass() == Subtask.class) {
            epicStatusUpdate(((Subtask) tasks.get(iD)).epicID);
        }
    }

    public void deleteAllTasks() {
        for (Integer key : tasks.keySet()) {
            tasks.remove(key);
            taskID = 0;
        }
    }

    public void deleteTask(Integer iD) {
        if (tasks.get(iD).getClass() == Subtask.class) {
            ((Epic) tasks.get(((Subtask) tasks.get(iD)).epicID)).subtasksList.remove(iD);                                   //проверить удаление!!!11
            epicStatusUpdate(((Subtask) tasks.get(iD)).epicID);
        } else if (tasks.get(iD).getClass() == Epic.class){
            for (Integer key : ((Epic) tasks.get(((Subtask) tasks.get(iD)).epicID)).subtasksList){
                tasks.remove(key);
            }
        }
        tasks.remove(iD);
    }

    public ArrayList<Integer> getEpicSubtasks(Integer iD) {
        return ((Epic) tasks.get(iD)).subtasksList;
    }

    private void epicStatusUpdate(Integer iD) {
        int newTasks = 0;
        int doneTasks = 0;
        for (Integer key : ((Epic) tasks.get(iD)).subtasksList) {
            if (tasks.get(((Epic) tasks.get(iD)).subtasksList.get(key)).status == Task.TaskStatus.NEW) {
                newTasks++;
            } else if (tasks.get(((Epic) tasks.get(iD)).subtasksList.get(key)).status == Task.TaskStatus.DONE)
                doneTasks++;
        }
        if (((Epic) tasks.get(iD)).subtasksList.size() == newTasks) {
            tasks.get(iD).status = Task.TaskStatus.NEW;
        } else if (((Epic) tasks.get(iD)).subtasksList.size() == doneTasks) {
            tasks.get(iD).status = Task.TaskStatus.DONE;
        } else tasks.get(iD).status = Task.TaskStatus.IN_PROGRESS;

    }
}

