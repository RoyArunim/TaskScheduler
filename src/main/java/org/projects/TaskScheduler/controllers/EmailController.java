package org.projects.TaskScheduler.controllers;

import jakarta.mail.MessagingException;
import org.projects.TaskScheduler.models.Task;
import org.projects.TaskScheduler.services.EmailService;
import org.projects.TaskScheduler.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class EmailController {

    @Autowired
    EmailService emailService;
    @Autowired
    TaskService taskService;

    @PostMapping("/send/{taskId}")
    public ResponseEntity<String> sendMail(@PathVariable String taskId) throws Exception {
        emailService.sendReminder(taskService.getTask(taskId));
        return ResponseEntity.ok("Reminders sent successfully");
    }


}
