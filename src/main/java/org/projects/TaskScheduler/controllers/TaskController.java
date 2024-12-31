package org.projects.TaskScheduler.controllers;

import org.apache.coyote.Response;
import org.projects.TaskScheduler.dtos.TaskDto;
import org.projects.TaskScheduler.models.Task;
import org.projects.TaskScheduler.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/createTask")
    public ResponseEntity<TaskDto> createTask(@RequestBody Task task){
        taskService.createOrUpdateTask(task);
        TaskDto taskDto =taskMapper(task);
        return ResponseEntity.ok(taskDto);
    }

    private TaskDto taskMapper(Task task){
        TaskDto dto = new TaskDto();
        dto.setTaskId(task.getTaskId());
        dto.setTaskName(task.getTaskName());
        dto.setCompleted(task.getCompleted());
        dto.setDescription(task.getDescription());
        dto.setScheduleTime(task.getScheduleTime());
        return dto;
    }
}
