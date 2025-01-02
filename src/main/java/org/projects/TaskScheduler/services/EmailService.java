package org.projects.TaskScheduler.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.projects.TaskScheduler.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Value("${from.email.address}")
    private String fromEmailAddress;

    @Autowired
    JavaMailSender mailSender;

    @Async
    public void sendReminder(Task task) throws MessagingException, UnsupportedEncodingException {
        for(String email  : task.getEmails()) {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(fromEmailAddress, "My Email Address");
            helper.setTo(email);
            helper.setSubject("Sample Subject");
            helper.setText("<p>this is a test message, from Arunim.</p>", true);
            mailSender.send(message);
        }
    }

}
