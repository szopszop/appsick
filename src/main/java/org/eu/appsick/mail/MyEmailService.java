package org.eu.appsick.mail;

import org.eu.appsick.visit.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Component;

@Component
public class MyEmailService implements EmailService {

    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String mailSenderUsername;

    @Autowired
    public MyEmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendInfoAboutSuccessfulUserRegistration(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSenderUsername);
        message.setTo(email);
        message.setSubject("Your account has been created.");
        message.setText("Your account has been created successfully! You can now log in.");
        emailSender.send(message);
    }

    @Override
    public void sendInfoAboutSuccessfulVisitRegistration(String email, Visit visitDetails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSenderUsername);
        message.setTo(email);
        message.setSubject("Visit registration.");
        message.setText("You've successfully registered new visit on " + visitDetails.getDate());
        emailSender.send(message);
    }
}
