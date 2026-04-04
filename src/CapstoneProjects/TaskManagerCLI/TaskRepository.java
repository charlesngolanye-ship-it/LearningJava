package CapstoneProjects.TaskManagerCLI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository{

    private static final Path FILE_PATH = Path.of("tasks.csv");

    public List<Task> loadTasks() { // tripped by this method that takes a list
        List<Task> tasks = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return tasks; // first run
        }

        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip the header row
                    continue;
                }
                String[] parts = line.split(",", -1); // prevents issues with empty values

                String createdAtStr = parts[6].trim();

                LocalDateTime createdAt;
                if (createdAtStr.length() == 10) {
                    createdAt = LocalDate.parse(createdAtStr).atStartOfDay();
                } else {
                    createdAt = LocalDateTime.parse(createdAtStr);
                }

                Task task = new Task(
                        parts[1].trim(),
                        parts[2].trim(),
                        Priority.valueOf(parts[3].trim()),
                        Status.valueOf(parts[4].trim()),
                        LocalDate.parse(parts[5].trim()),
                        createdAt

                );
                tasks.add(task);

            }
        }
        catch (Exception e) {
            throw new RuntimeException(e); // not sure about the error type
        }
        return tasks;

    }

    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {

            writer.write("id,title,description,priority,status,dueDate,createdAt");
            writer.newLine();

            for (Task task : tasks) {
                writer.write(String.format("%d,%s,%s,%s,%s,%s,%s",
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getPriority(),
                        task.getStatus(),
                        task.getDueDate(),
                        task.getCreatedAt()));
                writer.newLine();
            }

        } catch (Exception e) {
            throw new RuntimeException("Error saving tasks", e);
        }
    }
}

/**
 * Repository does NOT decide anything, it just reads and writes
 * read -> loadTasks()
 * write -> saveTasks()
 *TaskService decides when to call saveTasks()
 */
