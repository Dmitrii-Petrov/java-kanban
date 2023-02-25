package managers;

import tasks.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class HttpTaskManager extends FileBackedTasksManager {

    KVTaskClient taskClient;
    public HttpTaskManager(Path path) {
        super(path);
    }

    public HttpTaskManager (String url) throws IOException, InterruptedException {

        super(Files.copy(Path.of("./resources/emptyTest.csv"), Path.of("./resources/test.csv"), REPLACE_EXISTING));
        this.taskClient = new KVTaskClient(url);
    }

    @Override
    void save() throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("./resources/test.csv"))) {
            out.write("id,type,name,status,description,duration,startTime,epic");
            int i = 0;
            out.newLine();
            for (Task task : tasks.values()) {
                out.write(task.toString());
                this.taskClient.put(String.valueOf(i++),task.toString());
                out.newLine();
            }
            for (Task epic : epics.values()) {
                out.write(epic.toString());
                this.taskClient.put(String.valueOf(i++),epic.toString());
                out.newLine();
            }
            for (Task subtask : subtasks.values()) {
                this.taskClient.put(String.valueOf(i++),subtask.toString());
                out.write(subtask.toString());
                out.newLine();
            }
            out.newLine();
            out.write(historyToString(inMemoryHistoryManager));
            this.taskClient.put(String.valueOf(i),historyToString(inMemoryHistoryManager));

        } catch (ManagerSaveException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
