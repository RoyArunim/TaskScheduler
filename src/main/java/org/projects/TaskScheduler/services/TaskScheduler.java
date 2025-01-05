package org.projects.TaskScheduler.services;

import lombok.extern.slf4j.Slf4j;
import org.projects.TaskScheduler.models.EmailNotification;
import org.projects.TaskScheduler.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            sendTaskNotification(task);
        }
        }
    }

    public void sendTaskNotification(Task task){
        EmailNotification notification = EmailNotification.builder()
                        .subject("Task Reminder for: "+ task.getTaskName())
                        .body(buildEmail(task))
                .recipients(task.getEmails())
                .build();
        emailService.sendReminder(notification);
    }

    public String buildEmail(Task task){
        return String.format("""
            Hello,
            
            This is a reminder for the following task:
            
            Task: %s
            Description: %s
            Scheduled Time: %s
            
            Best regards,
            Task Scheduler
            """,
                task.getTaskName(),
                task.getDescription(),
                task.getScheduleTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
    }


}
