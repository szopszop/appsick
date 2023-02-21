package org.eu.appsick.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailConfig {

    @Value("${spring.mail.username}")
    private String mailSenderUsername;
    @Value("${spring.mail.password}")
    private String mailSenderPassword;
    @Value("${spring.mail.host}")
    private String mailSenderHost;
    @Value("${spring.mail.port}")
    private int mailSenderPort;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String mailSmtpAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String mailSmtpStarttls;
    @Value("${appsick.mail.debug}")
    private String mailDebug;
    @Value("${appsick.mail.protocol}")
    private String mailProtocol;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailSenderHost);
        mailSender.setPort(mailSenderPort);

        mailSender.setUsername(mailSenderUsername);
        mailSender.setPassword(mailSenderPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailProtocol);
        props.put("mail.smtp.auth", mailSmtpAuth);
        props.put("mail.smtp.starttls.enable", mailSmtpStarttls);
        props.put("mail.debug", mailDebug);

        return mailSender;
    }
}
