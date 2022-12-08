package managers;

import tasks.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class FileBackedTasksManager extends InMemoryTaskManager {
    private final Path path;


    public FileBackedTasksManager(Path path) {
        this.path = path;
    }

    static FileBackedTasksManager loadFromFile(Path path) {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(path);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine();

            while (reader.ready()) {
                String line = reader.readLine();
                if (line.isEmpty()) {
                    for (Integer i : historyFromString(reader.readLine())) {
                        if (fileBackedTasksManager.tasks.containsKey(i)) {
                            fileBackedTasksManager.getTask(i);
                        }
                        if (fileBackedTasksManager.epics.containsKey(i)) {
                            fileBackedTasksManager.getEpic(i);
                        }
                        if (fileBackedTasksManager.subtasks.containsKey(i)) {
                            fileBackedTasksManager.getSubtask(i);
                        }
                    }
                    break;
                }
                Task task = fromString(line);
                switch (TaskType.valueOf(line.split(",")[1])) {
                    case TASK:
                        fileBackedTasksManager.tasks.put(task.getId(), task);
                        break;
                    case EPIC:
                        fileBackedTasksManager.epics.put(task.getId(), (Epic) task);
                        break;
                    case SUBTASK:
                        fileBackedTasksManager.subtasks.put(task.getId(), (Subtask) task);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileBackedTasksManager;
    }


    void save() throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(String.valueOf(path)))) {
            out.write("id,type,name,status,description,duration,startTime,epic");
            out.newLine();
            for (Task task : tasks.values()) {
                out.write(task.toString());
                out.newLine();
            }
            for (Task epic : epics.values()) {
                out.write(epic.toString());
                out.newLine();
            }
            for (Task subtask : subtasks.values()) {
                out.write(subtask.toString());
                out.newLine();
            }
            out.newLine();
            out.write(historyToString(inMemoryHistoryManager));

        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }

    public static Task fromString(String value) {
        String[] string = value.split(",");
        Task task = null;
        switch (TaskType.valueOf(string[1])) {
            case TASK:
                task = new Task(Integer.parseInt(string[0]), string[2], TaskStatus.valueOf(string[3]), string[4], Integer.parseInt(string[5]), string[6]);
                break;
            case EPIC:
                task = new Epic(Integer.parseInt(string[0]), string[2], TaskStatus.valueOf(string[3]), string[4], Integer.parseInt(string[5]), string[6]);
                break;
            case SUBTASK:
                task = new Subtask(Integer.parseInt(string[0]), string[2], TaskStatus.valueOf(string[3]), string[4], Integer.parseInt(string[5]), string[6], Integer.parseInt(string[7]));
                break;
        }
        return task;
    }

    static String historyToString(HistoryManager manager) {
        String[] array = new String[manager.getTasks().size()];
        int i = 0;
        for (Task task : manager.getTasks()) {
            array[i++] = task.getId().toString();
        }
        return String.join(",", array);
    }

    static List<Integer> historyFromString(String value) {

        String[] string = value.split(",");
        List<Integer> list = new ArrayList<>();

        for (String st : string) {

            if (!st.isEmpty()) {
                list.add(Integer.parseInt(st));
            }
        }
        return list;
    }


    @Override
    public void newTask(Task task) throws IOException {
        super.newTask(task);
        save();
    }

    @Override
    public void newEpic(Epic epic) throws IOException {
        super.newEpic(epic);
        save();
    }

    @Override
    public void newSubtask(Subtask subtask, Integer epicID) throws IOException {
        super.newSubtask(subtask, epicID);
        save();
    }

    @Override
    public void updateTask(Task task, Integer id) throws IOException {
        super.updateTask(task, id);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask, Integer id) throws IOException {
        super.updateSubtask(subtask, id);
        save();
    }

    @Override
    public void updateEpic(Epic epic, Integer id) throws IOException {
        super.updateEpic(epic, id);
        save();
    }

    @Override
    public void deleteEverything() throws IOException {
        super.deleteEverything();
        save();
    }

    @Override
    public void deleteAllTasks() throws IOException {
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteAllSubtasks() throws IOException {
        super.deleteAllSubtasks();
        save();
    }

    @Override
    public void deleteAllEpics() throws IOException {
        super.deleteAllEpics();
        save();
    }

    @Override
    public void deleteTask(Integer id) throws IOException {
        super.deleteTask(id);
        save();
    }

    @Override
    public void deleteSubtask(Integer id) throws IOException {
        super.deleteSubtask(id);
        save();
    }

    @Override
    public void deleteEpic(Integer id) throws IOException {
        super.deleteEpic(id);
        save();
    }
}
