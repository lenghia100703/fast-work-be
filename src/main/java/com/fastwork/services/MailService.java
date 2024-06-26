package com.fastwork.services;

import com.fastwork.dtos.mail.MailConfirmDto;
import jakarta.mail.MessagingException;

public interface MailService {
    void sendConfirmationEmail(MailConfirmDto mailConfirmDto) throws MessagingException;

    void sendForgotPasswordEmail(MailConfirmDto mailConfirmDto) throws MessagingException;
}
