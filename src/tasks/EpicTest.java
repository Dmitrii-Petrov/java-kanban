package tasks;

import managers.InMemoryTaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
    Epic epic;

    @BeforeEach
    public void beforeEach() throws IOException {

        epic = new Epic(1, "testEpic", TaskStatus.NEW, "test details", 33, "22_02_2022|22:23");
        inMemoryTaskManager.newEpic(epic);
    }

    @Test
    void shouldReturnNewStatusForEmptyEpic() {
        Assertions.assertEquals(TaskStatus.NEW, epic.getStatus());
    }

    @Test
    void shouldReturnNewStatusForEpicWithNewSubtasks() throws IOException {
        Subtask subtask1 = new Subtask(2, "testSubtask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23", 1);
        Subtask subtask2 = new Subtask(3, "testSubtask", TaskStatus.NEW, "test details", 33, "24_02_2022|22:23", 1);
        inMemoryTaskManager.newSubtask(subtask1, 1);
        inMemoryTaskManager.newSubtask(subtask2, 1);
        Assertions.assertEquals(TaskStatus.NEW, epic.getStatus());
    }

    @Test
    void shouldReturnDoneStatusForEpicWithDoneSubtasks() throws IOException {
        Subtask subtask1 = new Subtask(2, "testSubtask", TaskStatus.DONE, "test details", 33, "23_02_2022|22:23", 1);
        Subtask subtask2 = new Subtask(3, "testSubtask", TaskStatus.DONE, "test details", 33, "24_02_2022|22:23", 1);
        inMemoryTaskManager.newSubtask(subtask1, 1);
        inMemoryTaskManager.newSubtask(subtask2, 1);
        Assertions.assertEquals(TaskStatus.DONE, epic.getStatus());
    }

    @Test
    void shouldReturnInProgressStatusForEpicWithDoneAndNewSubtasks() throws IOException {
        Subtask subtask1 = new Subtask(2, "testSubtask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23", 1);
        Subtask subtask2 = new Subtask(3, "testSubtask", TaskStatus.DONE, "test details", 33, "24_02_2022|22:23", 1);
        inMemoryTaskManager.newSubtask(subtask1, 1);
        inMemoryTaskManager.newSubtask(subtask2, 1);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus());
    }
    @Test
    void shouldReturnInProgressStatusForEpicWithInProgressSubtasks() throws IOException {
        Subtask subtask1 = new Subtask(2, "testSubtask", TaskStatus.IN_PROGRESS, "test details", 33, "23_02_2022|22:23", 1);
        Subtask subtask2 = new Subtask(3, "testSubtask", TaskStatus.IN_PROGRESS, "test details", 33, "24_02_2022|22:23", 1);
        inMemoryTaskManager.newSubtask(subtask1, 1);
        inMemoryTaskManager.newSubtask(subtask2, 1);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus());
    }
}