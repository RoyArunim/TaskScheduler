package org.projects.TaskScheduler.dtos;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TaskDto {
    private String taskId;
    private String taskName;
    private String description;
    private LocalDateTime scheduleTime;
    private boolean completed;

    public void setTaskId(String taskId){
        this.taskId=taskId;
    }

    public void setTaskName(String taskName){
        this.taskName=taskName;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void setScheduleTime(LocalDateTime scheduleTime){
        this.scheduleTime=scheduleTime;
    }

    public void setCompleted(boolean isCompleted){
        this.completed=isCompleted;
    }
}
