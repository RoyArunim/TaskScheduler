package org.projects.TaskScheduler.controllers;

import org.projects.TaskScheduler.dtos.TaskDto;
import org.projects.TaskScheduler.models.Task;
import org.projects.TaskScheduler.repositories.TaskRepository;
import org.projects.TaskScheduler.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/createTask")
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @GetMapping("/getTask/{id}")
    public Task getTask(@PathVariable("id") String taskId) {
        return taskRepository.getTaskById(taskId);
    }

    private TaskDto taskMapper(Task task){
        TaskDto dto = new TaskDto();
        dto.setTaskId(task.getTaskId());
        dto.setTaskName(task.getTaskName());
        dto.setCompleted(task.isCompleted());
        dto.setDescription(task.getDescription());
        dto.setScheduleTime(task.getScheduleTime());
        return dto;
    }
}
