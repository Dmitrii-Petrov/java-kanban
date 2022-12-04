package managers;


import org.junit.jupiter.api.Test;
import tasks.Epic;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FileBackedTasksManagerTest {

    @Test
    public void tests()  {

    FileBackedTasksManager fileBackedTasksManager1 = FileBackedTasksManager.loadFromFile(Path.of("./resources/test1.csv"));

    assertEquals(Collections.emptyList(), fileBackedTasksManager1.getTasksList(), "Задачи не пусты");
    assertEquals(Collections.emptyList(), fileBackedTasksManager1.getHistory(), "История не пуста.");

    FileBackedTasksManager fileBackedTasksManager = FileBackedTasksManager.loadFromFile(Path.of("./resources/test.csv"));
    Epic epic = (Epic) FileBackedTasksManager.fromString("1,EPIC,task1,NEW,task2details,33,22_02_2022|22:23");

    assertEquals(Collections.emptyList(), fileBackedTasksManager1.getTasksList(), "Задачи не пусты");
    assertEquals(epic, fileBackedTasksManager.getEpicsList().get(0), "эпик не создался");

}
}