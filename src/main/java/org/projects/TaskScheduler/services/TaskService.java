package org.projects.TaskScheduler.services;

import org.projects.TaskScheduler.models.Task;
import org.projects.TaskScheduler.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.saveTask(task);
    }

    public Task getTask(String taskId) throws Exception {
        return taskRepository.getTaskById(taskId);
    }

    public String deleteTaskById(String taskId){
        return taskRepository.delete(taskId);
    }

    public String updateTask(Task task, String taskId){
        return taskRepository.update(task, taskId);
    }

    public List<Task> findTasksDueForNotification(LocalDateTime currentTime){
        return taskRepository.findTasksDueforNotification(currentTime);
    }
}
