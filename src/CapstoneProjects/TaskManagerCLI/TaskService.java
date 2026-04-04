package CapstoneProjects.TaskManagerCLI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;


public class TaskService {
    private TaskRepository taskRepository = new TaskRepository();
    private List<Task> tasks;

    public TaskService() {
        tasks = taskRepository.loadTasks();
    }

    public void addTask(String title, String description, Priority priority, LocalDate dueDate){
        Task task = new Task(
                title,
                description,
                priority,
                Status.TODO,
                dueDate,
                LocalDateTime.now()
        );

        tasks.add(task);
        taskRepository.saveTasks(tasks);
    }

    public void deleteTask(int id) {
        boolean removed = tasks.removeIf(t -> t.getId() == id);

        if(!removed) {
            System.out.println("Task not found.");
        }

        taskRepository.saveTasks(tasks);
    }

    public void updateTasksStatus(int id, Status newStatus) {

        for (Task task : tasks){
            if (task.getId() == id) {

                // business rule
                if (task.getStatus() == Status.TODO && newStatus == Status.DONE) {
                    System.out.println("Cannot skip IN_PROGRESS");
                    return;
                }

                task.setStatus(newStatus);
                taskRepository.saveTasks(tasks);
                return;
            }
        }
        System.out.println("Task not found");
    }

    public void listTasks() {
        System.out.printf("%-5s %-15s %-10s %-12s %-10s%n",
                "ID", "Title", "Priority", "Status", "DueDate");

        for (Task task : tasks) {
            System.out.printf("%-5s %-15s %-10s %-12s %-10s%n",
            task.getId(),
            task.getTitle(),
            task.getPriority(),
            task.getStatus(),
            task.getDueDate());
        }
    }

    public void listByStatus(Status status) {

        tasks.stream()
                .filter(t -> t.getStatus() == status)
                .forEach(t -> System.out.println(t.getTitle()));
    }

    public void sortByDueDate() {
        tasks.sort(Comparator.comparing(Task::getDueDate));
    }

    public void printSummary() {
        System.out.println("===SUMMARY===");

        for (Status status : Status.values()) {
            long count = tasks.stream()
                    .filter(t -> t.getStatus() == status)
                    .count();
            System.out.println(status + ": " + count);
        }

        long overdue = tasks.stream()
                .filter(t -> t.getDueDate().isBefore(LocalDate.now()))
                .filter(t -> t.getStatus() != Status.DONE)
                .count();
        System.out.println("Overdue: " + overdue);
    }
}
/**
 * these should all be methods under Task?? or under TaskService??...what is difference between TaskRepository vs TaskService?
 * Should below (Add task, List tasks, Update task, Delete task) be their own methods under TaskService?
 * Maybe not all of them, list task seems handled within TaskRepository
 *
 * TaskService.java — validates input, applies business rules (e.g. cannot move directly from TODO to DONE), delegates storage to the repository
 * Validates input - when adding tasks etc, how do I apply business rules? how do i delegate storage to repository?
 *
 * Add task — prompt for title, description, priority, and due date (parse from yyyy-MM-dd string)....not sure how to connect Add task to TaskRepository..where task is added automatically
 * At the moment I just have a sample csv file with an hand-coded task..Files.writeString(csvPath....)...Task Service calls TaskRepository, that is the answer.
 *
 * List tasks — display all tasks in a table; support filtering by status and sorting by due date or priority
 * I think filtering by status is done, what is remains is finding out how to sort by due date or priority
 *
 * Update task — change status (move a task from TODO to IN_PROGRESS to DONE)...have another method ...updateTasks in TaskRepository after the loadTasks method
 * In this method find out how to update files...maybe read more in io
 *
 * Delete task — remove by ID..have another method ...deleteTasks in TaskRepository after the updateTasks method
 * In this method find out how to delete by id
 *
 * Summary — print how many tasks exist per status and how many are overdue (due date is in the past and status is not DONE)
 * I think this is partly done under List tasks above...
 * Go further and print how many tasks are overdue
 *
 *
 * In general, should I read more about collections? List.add etc, it seems that tasks are just a list, then see how to manipulate that list!!
 */

