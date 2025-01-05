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
    public ResponseEntity<TaskDto> createTask(@RequestBody Task task){
        TaskDto taskDto = taskMapper(taskService.createTask(task));
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/getTask/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") String taskId) {
        TaskDto taskDto = taskMapper(taskRepository.getTaskById(taskId));
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping("/addUser/{id}")
    public String addUserEmail(@PathVariable("id") String taskId, @RequestBody String emailId){
        return taskRepository.addUserEmail(taskId, emailId);
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
