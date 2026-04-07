package CapstoneProjects.TaskManagerCLI;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Task {
    private static int idCounter = 1; // shared across ALL tasks.

    private final int id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDate dueDate;
    private LocalDateTime createdAt;


    public Task(String title, String description, Priority priority, Status status,
                LocalDate dueDate, LocalDateTime createdAt) {

        this.id = idCounter++; // assign then increment. Give current value, then increment for the next Task. 1->2 ->3...
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }

//    If I restart program idCounter resets to 1, to avoid, it I have a second constructor - though not the case when I test it
    public Task(int id, String title, String description, Priority priority,
                Status status, LocalDate dueDate, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        this.createdAt = createdAt;

        if (id >= idCounter) {
            idCounter = id + 1;
        }
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
/**
 * Two constructors
 * New task from user - no id -> auto-generated
 * Load from CSV - use existing id
 */
