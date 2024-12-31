package org.projects.TaskScheduler.services;

import org.projects.TaskScheduler.models.Task;
import org.projects.TaskScheduler.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public void createOrUpdateTask(Task task){
        task.setTaskId(UUID.randomUUID());
        taskRepository.save(task);
    }

    public Task getTask(UUID taskId) throws Exception {
       return taskRepository.findById(taskId).orElseThrow(()->new Exception("This task is invalid"));
    }
}
