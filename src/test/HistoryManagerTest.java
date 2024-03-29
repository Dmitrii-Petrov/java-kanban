package test;


import managers.KVServer;
import managers.Managers;
import managers.TaskManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.TaskStatus;

import java.io.IOException;
import java.util.Collections;


import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {
    TaskManager taskManager = Managers.getDefault();

    HistoryManagerTest() throws IOException, InterruptedException {
    }

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

    @Test
    void tests() throws IOException {
        Task task = new Task(1, "testTask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23");
        taskManager.newTask(task);
        taskManager.getTask(1);

        assertNotNull(taskManager.getHistory(), "Иcтория не найдена.");
        assertNotEquals(Collections.emptyList(), taskManager.getHistory(), "Иcтория не найдена.");

        assertEquals(1, taskManager.getHistory().size(), "Неверное количество задач.");
        assertEquals(task, taskManager.getHistory().get(0), "Задачи не совпадают.");

        taskManager.getTask(1);  //дублирование

        assertEquals(1, taskManager.getHistory().size(), "Неверное количество задач.");
        assertEquals(task, taskManager.getHistory().get(0), "Задачи не совпадают.");


        taskManager.deleteTask(1);
        assertEquals(Collections.emptyList(), taskManager.getHistory(), "Задача не удалена.");
    }

}