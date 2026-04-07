package CapstoneProjects.TaskManagerCLI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

                    System.out.println("Priority (HIGH, MEDIUM, LOW:)");
                    Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase()); // how does this method work? user enters high, valueOf looks up the constant by its name...converting the string into an enum

                    System.out.println("Due date (yyyy-MM-dd):");
                    LocalDate dueDate = LocalDate.parse(scanner.nextLine());


                    service.addTask(title, description, priority, dueDate);
                    break;

                case 2:
                    System.out.println("List of Tasks");
                    System.out.println("=============");
                    service.listTasks();

                    System.out.println("TODO");
                    System.out.println("====");
                    service.listByStatus(Status.TODO);// not sure

                    System.out.println("IN_PROGRESS");
                    System.out.println("===========");
                    service.listByStatus(Status.IN_PROGRESS);

                    System.out.println("DONE");
                    System.out.println("====");
                    service.listByStatus(Status.DONE);

                    System.out.println("Sorted by Due Date");
                    System.out.println("==================");
                    service.sortByDueDate();
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
                    //Since we are inside main, return means Exit the main method..and when main ends the program terminates automatically.

            }
        }
    }
}
/**
 * Task - Data only -> the object.
 * Model defines how data looks like..e.g Task has some fields etc
 *
 * TaskRepository - File read/ write -> storage.
 * Handles data storage and retrieval. Responsible for saving data and reading it back from wherever it is stored, such as CSV file or dB
 * Hides storage details
 *
 * TaskService - Logic + rules -> brain
 * Contains the app logic
 * Controls what actions happen when users interact with the system, eg adding or updating data
 * Uses repository to store or retrieve data but focuses only on the rules and behavior of application
 *
 * TaskApp - User interaction -> Controller. Will have the main method
 *
 * Simplifying whole project into one sentence -> You are managing a List<Task> in memory, and saving/loading it from a CSV file.
 */