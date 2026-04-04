package CapstoneProjects.TaskManagerCLI;

public enum Priority {
    HIGH (1),
    MEDIUM (2),
    LOW (3);

    private final int value;

    private Priority(int value) {
        this.value = value;
    }
}
