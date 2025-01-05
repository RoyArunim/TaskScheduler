package org.projects.TaskScheduler.controllers;

import jakarta.mail.MessagingException;
import org.projects.TaskScheduler.models.EmailNotification;
import org.projects.TaskScheduler.models.Task;
import org.projects.TaskScheduler.services.EmailService;
import org.projects.TaskScheduler.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;
    @Autowired
    TaskService taskService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody EmailNotification notification) throws Exception {
        emailService.sendReminder(notification);
        return ResponseEntity.ok("Reminders sent successfully");
    }


}
