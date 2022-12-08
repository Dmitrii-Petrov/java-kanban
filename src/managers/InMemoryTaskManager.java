package managers;

import tasks.*;

import java.io.IOException;
import java.util.*;


public class InMemoryTaskManager implements TaskManager {
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    TreeSet<Task> prioritizedTasks = new TreeSet<>(Comparator.comparing(Task::getStartTime));

    HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();


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
    public void newTask(Task task) throws IOException {
        try {
            validateTask(task);
            task.setId(taskID);
            tasks.put(taskID, task);
            prioritizedTasks.add(task);
            taskID++;

        } catch (InvalidTaskTime invalidTaskTime) {
            System.out.println(invalidTaskTime.getMessage());
        }
    }

    @Override
    public void newEpic(Epic epic) throws IOException {
        try {
            validateTask(epic);
            epic.setId(taskID);
            epics.put(taskID, epic);
            prioritizedTasks.add(epic);
            taskID++;
        } catch (InvalidTaskTime invalidTaskTime) {
            System.out.println(invalidTaskTime.getMessage());
        }
    }

    @Override
    public void newSubtask(Subtask subtask, Integer epicID) throws IOException {
        try {
            validateTask(subtask);
            subtask.setId(taskID);
            subtask.setEpicID(epicID);
            subtasks.put(taskID, subtask);
            (epics.get(epicID)).subtasksList.put(taskID, subtask);                                                                //передаем эпику информацию о подзадаче
            (epics.get(epicID)).updateEpic();
            validateTask(epics.get(epicID));
            prioritizedTasks.add(subtask);
            taskID++;
        } catch (InvalidTaskTime invalidTaskTime) {
            System.out.println(invalidTaskTime.getMessage());
        }
    }

    @Override
    public void updateTask(Task task, Integer id) throws IOException {
        try {
            validateTask(task);
            task.setId(id);
            prioritizedTasks.remove(tasks.get(id));
            tasks.put(id, task);
            prioritizedTasks.add(task);
        } catch (InvalidTaskTime invalidTaskTime) {
            System.out.println(invalidTaskTime.getMessage());
        }
    }

    @Override
    public void updateSubtask(Subtask subtask, Integer id) throws IOException {
        try {
            validateTask(subtask);
            subtask.setId(id);
            prioritizedTasks.remove(subtasks.get(id));
            subtasks.put(id, subtask);
            epics.get(subtask.getEpicID()).updateEpic();
            validateTask(epics.get(subtask.getEpicID()));
            prioritizedTasks.add(subtask);
        } catch (InvalidTaskTime invalidTaskTime) {
            System.out.println(invalidTaskTime.getMessage());
        }
    }

    @Override
    public void updateEpic(Epic epic, Integer id) throws IOException {
        try {
            validateTask(epic);
            epic.setId(id);
            prioritizedTasks.remove(epics.get(id));
            epics.put(id, epic);
            prioritizedTasks.add(epic);
        } catch (InvalidTaskTime invalidTaskTime) {
            System.out.println(invalidTaskTime.getMessage());
        }

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
        for (Integer taskID : tasks.keySet()) {
            prioritizedTasks.remove(tasks.get(taskID));
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
        for (Integer subtaskID : subtasks.keySet()) {
            prioritizedTasks.remove(subtasks.get(subtaskID));
            inMemoryHistoryManager.remove(subtaskID);
        }
        subtasks.clear();
    }

    @Override
    public void deleteAllEpics() throws IOException {
        for (Epic epic : epics.values()) {
            for (Integer id : epic.subtasksList.keySet()) {
                prioritizedTasks.remove(subtasks.get(id));
                subtasks.remove(id);
            }
        }
        for (Integer epicID : epics.keySet()) {
            prioritizedTasks.remove(epics.get(epicID));
            inMemoryHistoryManager.remove(epicID);
        }
        epics.clear();
    }

    @Override
    public void deleteTask(Integer id) throws IOException {
        prioritizedTasks.remove(tasks.get(id));
        inMemoryHistoryManager.remove(id);
        tasks.remove(id);
    }

    @Override
    public void deleteSubtask(Integer id) throws IOException {
        epics.get((subtasks.get(id)).getEpicID()).updateEpic();
        epics.get((subtasks.get(id)).getEpicID()).subtasksList.remove(id);

        prioritizedTasks.remove(subtasks.get(id));
        inMemoryHistoryManager.remove(id);
        subtasks.remove(id);
    }

    @Override
    public void deleteEpic(Integer id) throws IOException {
        for (Integer subtaskID : (epics.get(id)).subtasksList.keySet()) {
            prioritizedTasks.remove(subtasks.get(subtaskID));
            subtasks.remove(subtaskID);
            inMemoryHistoryManager.remove(subtaskID);
        }
        prioritizedTasks.remove(epics.get(id));
        inMemoryHistoryManager.remove(id);
        epics.remove(id);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return inMemoryHistoryManager.getTasks();
    }

    @Override
    public TreeSet<Task> getPrioritizedTasks() {
        return prioritizedTasks;
    }

    @Override
    public void validateTask(Task task) throws InvalidTaskTime {
        for (Task task1 : prioritizedTasks) {
            if ((task.getStartTime().isBefore(task1.getStartTime()) && (task.getEndTime().isAfter(task1.getStartTime()))) ||
                    (task.getStartTime().isBefore(task1.getEndTime()) && (task.getEndTime().isAfter(task1.getEndTime())))) {
                throw new InvalidTaskTime("Invalid task time");

            }
        }

    }

}

