package test;

import managers.InvalidTaskTime;
import managers.TaskManager;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;
import tasks.TaskStatus;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class TaskManagerTest<T extends TaskManager> {
    T taskManager;



    @Test
    void newTask() throws IOException {
        Task task = new Task(1, "testTask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23");
        taskManager.newTask(task);
        int taskId = task.getId();

        assertNotNull(taskManager.getTask(taskId), "Задача не найдена.");
        assertEquals(task, taskManager.getTask(taskId), "Задачи не совпадают.");
        List<Task> tasks = taskManager.getTasksList();

        assertNotNull(tasks, "Задачи на возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");

        taskManager.deleteTask(1);
        assertEquals(Collections.emptyList(), taskManager.getTasksList(), "Задача не удалена.");
    }

    @Test
    void newEpic() throws IOException {
        Epic epic = new Epic(1, "testSubtask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23");
        taskManager.newEpic(epic);
        int taskId = epic.getId();

        assertNotNull(taskManager.getEpic(taskId), "Задача не найдена.");
        assertEquals(epic, taskManager.getEpic(taskId), "Задачи не совпадают.");
        List<Task> epics = taskManager.getEpicsList();

        assertNotNull(epics, "Задачи на возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertEquals(epic, epics.get(0), "Задачи не совпадают.");

        taskManager.deleteEpic(1);
        assertEquals(Collections.emptyList(), taskManager.getEpicsList(), "Задача не удалена.");
    }

    @Test
    void newSubtask() throws IOException {
        Epic epic = new Epic(1, "testSubtask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23");
        Subtask subtask = new Subtask(2, "testSubtask", TaskStatus.NEW, "test details", 33, "25_02_2022|22:23", 1);
        taskManager.newEpic(epic);
        taskManager.newSubtask(subtask, 1);
        int taskId = subtask.getId();

        assertNotNull(taskManager.getSubtask(taskId), "Задача не найдена.");
        assertEquals(subtask, taskManager.getSubtask(taskId), "Задачи не совпадают.");
        List<Task> subtasks = taskManager.getSubtasksList();

        assertNotNull(subtasks, "Задачи на возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество задач.");
        assertEquals(subtask, subtasks.get(0), "Задачи не совпадают.");

        taskManager.deleteSubtask(subtask.getId());
        assertEquals(Collections.emptyList(), taskManager.getSubtasksList(), "Задача не удалена.");


    }

    @Test
    void shouldDeleteEverything() throws IOException {
        Task task = new Task(1, "testTask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23");
        taskManager.newTask(task);
        taskManager.deleteEverything();
        assertEquals(Collections.emptyList(), taskManager.getTasksList(), "Задача не удалена.");
    }

    @Test
    void shouldDeleteAllTasks() throws IOException {
        Task task = new Task(1, "testTask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23");
        taskManager.newTask(task);
        taskManager.deleteAllTasks();
        assertEquals(Collections.emptyList(), taskManager.getTasksList(), "Задача не удалена.");
    }

    @Test
    void shouldDeleteAllSubtasks() throws IOException {
        Epic epic = new Epic(1, "testSubtask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23");
        Subtask subtask = new Subtask(1, "testSubtask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23", 1);
        taskManager.newEpic(epic);
        taskManager.newSubtask(subtask, 1);
        taskManager.deleteAllSubtasks();
        assertEquals(Collections.emptyList(), taskManager.getSubtasksList(), "Задача не удалена.");
    }

    @Test
    void shouldDeleteAllEpics() throws IOException {
        Epic epic = new Epic(1, "testSubtask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23");
        taskManager.newEpic(epic);
        taskManager.deleteAllEpics();
        assertEquals(Collections.emptyList(), taskManager.getEpicsList(), "Задача не удалена.");
    }

    @Test
    void shouldNotValidateTask() throws IOException {
        Epic epic = new Epic(1, "testSubtask", TaskStatus.NEW, "test details", 33, "23_02_2022|22:23");
        Epic epic1 = new Epic(2, "testSubtask1", TaskStatus.NEW, "test details1", 33, "23_02_2022|22:33");
        taskManager.newEpic(epic);


        InvalidTaskTime ex = assertThrows(InvalidTaskTime.class, () -> taskManager.validateTask(epic1));

        assertEquals("Invalid task time", ex.getMessage());

    }
}