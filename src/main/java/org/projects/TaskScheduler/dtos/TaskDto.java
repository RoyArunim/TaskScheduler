package org.projects.TaskScheduler.dtos;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDto {
    private String taskId;
    private String taskName;
    private String description;
    private LocalDateTime scheduleTime;
    private boolean completed;

}
