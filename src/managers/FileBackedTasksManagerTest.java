package managers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.jupiter.api.Assertions.*;

class FileBackedTasksManagerTest extends TaskManagerTest {

    @BeforeEach
    public void beforeEach() throws IOException {
        Path path = Files.copy(Path.of("./resources/noEmptyTest.csv"), Path.of("./resources/test2.csv"), REPLACE_EXISTING);
        taskManager = FileBackedTasksManager.loadFromFile(path);
    }

    @Test
    public void shouldCreateNewEpicFromString() {

        Epic epic = (Epic) FileBackedTasksManager.fromString("1,EPIC,testSubtask,NEW,test details,33,23_02_2022|22:23");
        assertEquals(Collections.emptyList(), taskManager.getTasksList(), "Задачи не пусты");
        assertEquals(epic, taskManager.getEpicsList().get(0), "эпик не создался");
    }

    @Test
    public void shouldLoadTaskManagerWithEmptyTaskAndHistory() throws IOException {
        Path path = Files.copy(Path.of("./resources/emptyTest.csv"), Path.of("./resources/test3.csv"), REPLACE_EXISTING);
        taskManager = FileBackedTasksManager.loadFromFile(path);
        assertEquals(Collections.emptyList(), taskManager.getTasksList(), "Задачи не пусты");
        assertEquals(Collections.emptyList(), taskManager.getHistory(), "История не пуста.");

    }

}
