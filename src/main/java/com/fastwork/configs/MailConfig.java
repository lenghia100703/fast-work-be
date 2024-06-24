package com.fastwork.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value("${spring.mail.host}")
    String mailHost;

    @Value("${spring.mail.port}")
    String mailPort;

    @Value("${spring.mail.username}")
    String mailUsername;

    @Value("${spring.mail.password}")
    String mailPassword;

    @Value("${spring.mail.properties.mail.smtp.transport.protocol}")
    String mailProtocol;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    String mailAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    String mailEnable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    String mailRequired;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(Integer.parseInt(mailPort));

        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailProtocol);
        props.put("mail.smtp.auth", mailAuth);
        props.put("mail.smtp.starttls.enable", mailEnable);
        props.put("mail.smtp.starttls.required", mailRequired);
        props.put("mail.smtp.connectiontimeout", 5000);
        props.put("mail.smtp.timeout", 5000);
        props.put("mail.smtp.writetimeout", 5000);

        return mailSender;
    }
}
