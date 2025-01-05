package org.projects.TaskScheduler.services;

import lombok.extern.slf4j.Slf4j;
import org.projects.TaskScheduler.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class TaskScheduler {

    @Autowired
    TaskService taskService;

    @Autowired
    EmailService emailService;

    public void checkAndSendTaskNotification(){
        log.info("Checking for scheduled tasks...");
        LocalDateTime currentTime = LocalDateTime.now();
        List<Task> scheduledTasks = taskService.findTasksDueForNotification(currentTime);

        for(Task task:scheduledTasks){
        if(!task.isCompleted() && !task.getEmails().isEmpty()){
            sendTaskNotification();
        }
        }
    }

    public void sendTaskNotification(){

    }


}
