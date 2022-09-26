import java.util.ArrayList;

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
        manager.newSubtask(subtask1,2);
        manager.newSubtask(subtask2,2);
        manager.newSubtask(subtask3,3);

        System.out.println(manager.getTasksList());
        System.out.println(task1);
        System.out.println(epic1);
        System.out.println(subtask1);

        task1.setStatus(Task.TaskStatus.DONE);
        manager.updateTask(task1, 0);
        System.out.println(task1);

        subtask1.setStatus(Task.TaskStatus.IN_PROGRESS);
        manager.updateTask(subtask1, 4);
        System.out.println(subtask1);
        System.out.println(epic1);


        subtask1.setStatus(Task.TaskStatus.DONE);
        subtask2.setStatus(Task.TaskStatus.DONE);
        manager.updateTask(subtask1, 4);
        manager.updateTask(subtask2, 5);
        System.out.println(epic1);

        manager.deleteTask(4);

        System.out.println(epic1);










    }

}
