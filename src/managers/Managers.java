package managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class Managers {


    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
    public static TaskManager getFileBackedTaskManager(Path path) {
        return new FileBackedTasksManager(path);
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

}
