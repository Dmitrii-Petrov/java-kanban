package test;

import managers.HttpTaskServer;
import managers.KVServer;
import managers.Managers;
import managers.TaskManager;
import org.junit.jupiter.api.*;
import tasks.Epic;
import tasks.Subtask;
import tasks.TaskStatus;

import java.io.IOException;

class EpicTest {

    TaskManager taskManager;
    HttpTaskServer server;
    Epic epic;

    static KVServer kvServer;

    @BeforeAll
    static void beforeAll() throws IOException {
        kvServer= new KVServer();
        kvServer.start();
    }

    @AfterAll
    static void afterAll() {
        kvServer.stop();
    }

    @BeforeEach
    public void beforeEach() throws IOException, InterruptedException {
        taskManager = Managers.getDefault();
        server = new HttpTaskServer();
        server.start();
        epic = new Epic(1, "testEpic", TaskStatus.NEW, "test details", 33, "22_02_2022|22:23");
        taskManager.newEpic(epic);
    }

    @AfterEach
    public void afterEach() {
        server.stop();
    }

    @Test
    void shouldReturnNewStatusForEmptyEpic() {
        Assertions.assertEquals(TaskStatus.NEW, epic.getStatus());
    }

    @Test
    void shouldReturnNewStatusForEpicWithNewSubtasks() throws IOException {
        Subtask subtask1 = new Subtask(2, "testSubtask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23", 1);
        Subtask subtask2 = new Subtask(3, "testSubtask", TaskStatus.NEW, "test details", 33, "24_02_2022|22:23", 1);
        taskManager.newSubtask(subtask1, 1);
        taskManager.newSubtask(subtask2, 1);
        Assertions.assertEquals(TaskStatus.NEW, epic.getStatus());
    }

    @Test
    void shouldReturnDoneStatusForEpicWithDoneSubtasks() throws IOException {
        Subtask subtask1 = new Subtask(2, "testSubtask", TaskStatus.DONE, "test details", 33, "23_02_2022|22:23", 1);
        Subtask subtask2 = new Subtask(3, "testSubtask", TaskStatus.DONE, "test details", 33, "24_02_2022|22:23", 1);
        taskManager.newSubtask(subtask1, 1);
        taskManager.newSubtask(subtask2, 1);
        Assertions.assertEquals(TaskStatus.DONE, epic.getStatus());
    }

    @Test
    void shouldReturnInProgressStatusForEpicWithDoneAndNewSubtasks() throws IOException {
        Subtask subtask1 = new Subtask(2, "testSubtask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23", 1);
        Subtask subtask2 = new Subtask(3, "testSubtask", TaskStatus.DONE, "test details", 33, "24_02_2022|22:23", 1);
        taskManager.newSubtask(subtask1, 1);
        taskManager.newSubtask(subtask2, 1);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus());
    }

    @Test
    void shouldReturnInProgressStatusForEpicWithInProgressSubtasks() throws IOException {
        Subtask subtask1 = new Subtask(2, "testSubtask", TaskStatus.IN_PROGRESS, "test details", 33, "23_02_2022|22:23", 1);
        Subtask subtask2 = new Subtask(3, "testSubtask", TaskStatus.IN_PROGRESS, "test details", 33, "24_02_2022|22:23", 1);
        taskManager.newSubtask(subtask1, 1);
        taskManager.newSubtask(subtask2, 1);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus());
    }
}