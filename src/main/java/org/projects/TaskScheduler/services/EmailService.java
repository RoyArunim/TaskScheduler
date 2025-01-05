package org.projects.TaskScheduler.services;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.projects.TaskScheduler.models.EmailNotification;
import org.projects.TaskScheduler.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class EmailService {


    private final String fromEmail;

    @Autowired
    private final AmazonSimpleEmailService sesClient;

    public EmailService(@Value("${from.email.address}") String fromEmail,
                        @Value("${aws.region}") String region) {
        this.fromEmail = fromEmail;
        this.sesClient = AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(region)
                .build();
    }

    @Async
public void sendReminder(EmailNotification notification) {
    SendEmailRequest emailRequest = new SendEmailRequest()
            .withDestination(new Destination().withBccAddresses(notification.getRecipients()))
            .withMessage(
                    new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8")
                                            .withData(notification.getBody()))
                            )
                            .withSubject(new Content()
                                    .withCharset("UTF-8")
                                    .withData(notification.getSubject()))
            )
            .withSource(fromEmail);

    SendEmailResult result = sesClient.sendEmail(emailRequest);
    log.info("Sent mail to recipient!");
}



//    @Async
//    public void sendReminder(Task task) throws MessagingException, UnsupportedEncodingException {
//        for(String email  : task.getEmails()) {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//            helper.setFrom(fromEmailAddress, "My Email Address");
//            helper.setTo(email);
//            helper.setSubject("Sample Subject");
//            helper.setText("<p>this is a test message, from Arunim.</p>", true);
//            mailSender.send(message);
//        }
//    }



}
