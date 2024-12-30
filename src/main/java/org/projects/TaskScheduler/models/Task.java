package org.projects.TaskScheduler.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Task {
    private UUID taskId;
    private String taskName;
    private String description;
    private LocalDateTime scheduleTime;
    private boolean completed;
    private List<String> emails;

}
