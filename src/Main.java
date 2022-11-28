import managers.*;
import tasks.*;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        System.out.println("Let's go!");
        TaskManager inMemoryTaskManager = (new Managers()).getDefault();
      /*  FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(Path.of("./resources/test.csv"));

        Task task1 = fileBackedTasksManager.fromString("1,SUBTASK,task1,NEW,task2details,33");
        System.out.println(task1);

       */
/*
        Task task1 = new Task("task1", "task1details");
        Task task2 = new Task("task2", "task2details");
        Epic epic1 = new Epic("epic1", "epic1details");
        Epic epic2 = new Epic("epic2", "epic2details");
        Subtask subtask1 = new Subtask("subtask1", "subtask1details");
        Subtask subtask2 = new Subtask("subtask2", "subtask2details");
        Subtask subtask3 = new Subtask("subtask3", "subtask3details");


        inMemoryTaskManager.newTask(task1);
        inMemoryTaskManager.newTask(task2);
        inMemoryTaskManager.newEpic(epic1);
        inMemoryTaskManager.newEpic(epic2);
        inMemoryTaskManager.newSubtask(subtask1, epic1.getId());
        inMemoryTaskManager.newSubtask(subtask2, epic1.getId());
        inMemoryTaskManager.newSubtask(subtask3, epic2.getId());

        System.out.println(inMemoryTaskManager.getTasksList());
        System.out.println(inMemoryTaskManager.getSubtasksList());
        System.out.println(inMemoryTaskManager.getEpicsList());

        inMemoryTaskManager.getTask(1);
        inMemoryTaskManager.getEpic(3);
        inMemoryTaskManager.getSubtask(5);
        inMemoryTaskManager.getTask(2);


        System.out.println();
        System.out.println(inMemoryTaskManager.getHistory());
        System.out.println();
        inMemoryTaskManager.getTask(1);


        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.deleteTask(1);

        System.out.println(inMemoryTaskManager.getHistory());

*/


    }

}
