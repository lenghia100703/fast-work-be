package com.fastwork.services.impls;

import com.fastwork.dtos.mail.MailConfirmDto;
import com.fastwork.services.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender sender;

    @Value("${frontend.domain}")
    String domain;

    @Value("${spring.mail.username}")
    String from;

    @Override
    public void sendConfirmationEmail(MailConfirmDto mailConfirmDto) throws MessagingException {
        this.generateMailRequest(mailConfirmDto);
    }

    @Override
    public void sendForgotPasswordEmail(MailConfirmDto mailConfirmDto) throws MessagingException {
        this.generateMailRequest(mailConfirmDto);
    }

    private void generateMailRequest(MailConfirmDto mailConfirmDto) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(mailConfirmDto.getUser().getEmail());
        helper.setFrom(from);
        if (Objects.equals(mailConfirmDto.getType(), "CONFIRM")) {
            helper.setSubject("Confirm you E-Mail");
            helper.setText("<html>" +
                            "<body>" +
                            "<h3>Dear " + mailConfirmDto.getUser().getUsername() + ",</h3>"
                            + "<br/> We're excited to have you get started. " +
                            "Please click on below link to confirm your account."
                            + "<br />"
                            + "<br/> " + generateConfirmationLink(mailConfirmDto.getToken(), "CONFIRM")
                            + "<br />" +
                            "<br/> Regards,<br/>" +
                            "Registration team" +
                            "</body>" +
                            "</html>"
                    , true);
        } else if (Objects.equals(mailConfirmDto.getType(), "FORGOT")) {
            helper.setSubject("Forgot password");
            helper.setText("<html>" +
                            "<body>" +
                            "<h3>Dear " + mailConfirmDto.getUser().getUsername() + ",</h3>" +
                            "Please click the link below to get your account back."
                            + "<br />"
                            + "<br/> " + generateConfirmationLink(mailConfirmDto.getToken(), "FORGOT")
                            + "<br />" +
                            "<br/> Regards,<br/>" +
                            "Fast Work Team" +
                            "</body>" +
                            "</html>"
                    , true);
        }

        sender.send(helper.getMimeMessage());
    }

    private String generateConfirmationLink(String token, String type) {
        if (Objects.equals(type, "CONFIRM")) {
            return "<a href=" + domain + "/confirm-email?token=" + token + ">Redirect</a>";
        } else if (Objects.equals(type, "FORGOT")) {
            return "<a href=" + domain + "/forgot-password?token=" + token + ">Redirect</a>";
        }
        return "";
    }
}
