package CapstoneProjects.TaskManagerCLI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;

public class TaskApp {

    private final static Scanner scanner = new Scanner(System.in);
    private final static TaskService service = new TaskService();

    public static void main(String[] args) {

        while (true) {
            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;

                case 2:
                    service.listTasks();
                    break;

//                    System.out.println("TODO");
//                    System.out.println("====");
//                    service.listByStatus(Status.TODO);// not sure
//
//                    System.out.println("IN_PROGRESS");
//                    System.out.println("===========");
//                    service.listByStatus(Status.IN_PROGRESS);
//
//                    System.out.println("DONE");
//                    System.out.println("====");
//                    service.listByStatus(Status.DONE);
//
//                    System.out.println("Sorted by Due Date");
//                    System.out.println("==================");
//                    service.sortByDueDate();
//                    service.listTasks();


                case 3:
                    updateStatus();
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

    private static void printMenu(){
        System.out.println("""
                1. Add Task
                2. List Task
                3. Update Task Status
                4. Delete Task
                5. Print Summary
                6. Exit
                """);
    }

    private static void addTask() {
        System.out.println("Title");
        String title = scanner.nextLine();

        System.out.println("Description");
        String description = scanner.nextLine();

        Priority priority = readPriority();

        System.out.println("Due date (yyyy-MM-dd):");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        service.addTask(title, description, priority, dueDate);
    }

    private static Priority readPriority(){
        while(true){
            System.out.println("Priority (HIGH, MEDIUM, LOW:)");
            try {
                // how does this method work? user enters high, valueOf looks up the constant by its name...converting the string into an enum
                return Priority.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice: ");
            }
        }
    }

    private static Status readStatus() {
        while (true) {
            System.out.println("New Status (TODO, IN_PROGRESS, DONE):");
            try {
                return Status.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice");
            }

        }
    }

    private static void updateStatus () {
        System.out.println("Task ID:");
        int id = scanner.nextInt();
        scanner.nextLine();

        while (true) {
            Status status = readStatus();
            UpdateResult result = service.updateTasksStatus(id, status);

            switch (result) {
                case SUCCESS:
                    System.out.println("Task updated successfully");
                    return;

                case NOT_FOUND:
                    System.out.println("Task not found");
                    return;

                case INVALID_TRANSITION:
                    System.out.println("Cannot skip IN_PROGRESS. Try again");
                    break;// loop continues
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