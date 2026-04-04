package CapstoneProjects.TaskManagerCLI;

import java.time.LocalDate;
import java.util.Scanner;

public class TaskApp {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add Task 2.List Task 3.Update Task Status 4.Delete Task 5.Summary 6.Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Title");
                    String title = scanner.nextLine();

                    System.out.println("Description");
                    String description = scanner.nextLine();

                    System.out.println("Priority (HIGH, MEDIUM, LOW:");
                    Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

                    System.out.println("Due date (yyyy-MM-dd):");
                    LocalDate dueDate = LocalDate.parse(scanner.nextLine());

                    service.addTask(title, description, priority, dueDate);
                    break;

                case 2:
                    service.listTasks();
                    break;

                case 3:
                    System.out.println("Task ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("New Status (TODO, IN_PROGRESS, DONE):");
                    Status status = Status.valueOf(scanner.nextLine().toUpperCase());

                    service.updateTasksStatus(id, status);
                    break;

                case 4:
                    System.out.println("Task ID:");
                    int deleteId = scanner.nextInt();
                    service.deleteTask(deleteId);
                    break;

                case 5:
                    service.printSummary();
                    break;

                case 6:
                    return;

            }
        }
    }
}
/**
 * Task - Data only -> the object
 * TaskRepository - File read/ write -> storage
 * TaskService - Logic + rules -> brain
 * TaskApp - User interaction -> Controller. Will have the main method
 * Simplifying whole project into one sentence -> You are managing a List<Task> in memory, and saving/loading it from a CSV file.
 */