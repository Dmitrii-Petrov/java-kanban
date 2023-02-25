package managers;

import java.io.IOException;
import java.nio.file.Path;

public class Managers {


    public static TaskManager getDefault() throws IOException, InterruptedException {
        return new HttpTaskManager("http://localhost:8078/");
    }
    public static TaskManager getFileBackedTaskManager(Path path) {
        return new FileBackedTasksManager(path);
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

}
