import manager.Manager;
import tasks.*;

import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Let's go!");
        Manager manager = new Manager();
        Task task1 = new Task("task1", "task1details");
        Task task2 = new Task("task2", "task2details");
        Epic epic1 = new Epic("epic1", "epic1details");
        Epic epic2 = new Epic("epic2", "epic2details");
        Subtask subtask1 = new Subtask("subtask1", "subtask1details");
        Subtask subtask2 = new Subtask("subtask2", "subtask2details");
        Subtask subtask3 = new Subtask("subtask3", "subtask3details");


        manager.newTask(task1);
        manager.newTask(task2);
        manager.newEpic(epic1);
        manager.newEpic(epic2);
        manager.newSubtask(subtask1, epic1.getId());
        manager.newSubtask(subtask2, epic1.getId());
        manager.newSubtask(subtask3, epic2.getId());

        System.out.println(manager.getTasksList());
        System.out.println(manager.getSubtasksList());
        System.out.println(manager.getEpicsList());
        System.out.println(task1);
        System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println();

        task1.setStatus(TaskStatus.DONE);
        manager.updateTask(task1, task1.getId());
        System.out.println(task1);

        subtask1.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subtask1, subtask1.getId());
        System.out.println(subtask1);
        System.out.println(epic1);
        System.out.println(manager.getSubtasksList());

        subtask1.setStatus(TaskStatus.DONE);
        subtask2.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1, subtask1.getId());
        manager.updateSubtask(subtask2, subtask2.getId());

        System.out.println();
        System.out.println(epic1);
        System.out.println(manager.getEpicsList());
        System.out.println(manager.getSubtasksList());

        manager.deleteEpic(3);
        System.out.println(manager.getEpicsList());
        System.out.println(manager.getSubtasksList());


        manager.deleteAllSubtasks();

        System.out.println(manager.getSubtasksList());

    }

}
