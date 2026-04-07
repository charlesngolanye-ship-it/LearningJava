package CapstoneProjects.TaskManagerCLI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;


public class TaskService {
    private TaskRepository taskRepository = new TaskRepository(); // inject in constructor - dependency injection
    private List<Task> tasks; // working memory where all current tasks are written

    public TaskService() {
        tasks = taskRepository.loadTasks(); // loads existing tasks -> Go to the CSV file, bring all tasks, and put them on the list
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

        tasks.add(task);// add it to your in-memory list
        taskRepository.saveTasks(tasks); // persist everything to CSV -> take everything on the list and rewrite the file
        // On startup: CSV file -> loadTasks() -> List<Task> (memory)
        // During runtime: List<Task> -> modified (add/delete/update)
        // On save: List<Task> -> saveTasks() -> CSV file overwritten
    }

    // Find task by ID -> delete it -> save changes
    public void deleteTask(int id) {
       boolean removed = false;

       for (int i = 0; i < tasks.size(); i++) {
           if (tasks.get(i).getId() == id) {
               tasks.remove(i);
               removed = true;
               break; // stop after finding one
           }
       }
       if (!removed) {
           System.out.println("Task not found");
       }

        taskRepository.saveTasks(tasks);

       /*
       for (Task t : tasks) {
    if (t.getId() == id) {
        tasks.remove(t); // 💥 ConcurrentModificationException risk
    }
}
        */
    }

    public void updateTasksStatus(int id, Status newStatus) {

        for (Task task : tasks){
            if (task.getId() == id) {
//                Status newStatus = null;
//
//                while (true) {
//                    try {
//                        newStatus = Status.valueOf()
//                    }
//                }

                // business rule
                if (task.getStatus() == Status.TODO && newStatus == Status.DONE) {
                    System.out.println("Cannot skip IN_PROGRESS");
                    return;
                    // Instead of returning make it a while loop so that user has to enter the right status
                    // Handle IllegalArgumentException errors of value.Of is not found...
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
        System.out.println("---------------------------------------------------------");

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

        for (Task t : tasks) {
            if (t.getStatus() == status) {
                System.out.println(t.getTitle());
            }
        }

        /*
        // Turn list into a stream pipeline
        tasks.stream()
                .filter(t -> t.getStatus() == status)
                .forEach(t -> System.out.println(t.getTitle()));

         */
    }

    public void sortByDueDate() {
        // Bubble Sort
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = 0; j < tasks.size() - i - 1; j++) {
                LocalDate d1 = tasks.get(j).getDueDate();
                LocalDate d2 = tasks.get(j + 1).getDueDate();

                if (d1.isAfter(d2)) {
                    Task temp = tasks.get(j);
                    tasks.set(j, tasks.get(j + 1));
                    tasks.set(j + 1, temp);
                }
            }
        }

        /*
        tasks.sort(Comparator.comparing(Task::getDueDate));

         */
    }


    // shouldn't this be able to list tasks then output them by due date? it seems list task is outputting tasks by due date


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

 * Flow: App starts, TaskService is created, it calls: repo.loadTasks(), CSV converted into List<Task>, Stored in memory (tasks)
 */

