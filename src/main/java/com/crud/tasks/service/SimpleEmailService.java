package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    private boolean isHTMLmail(final Mail mail) {
        switch (mail.getSubject()) {
            case "Tasks: New Trello card":
                mail.setMessage(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
                return true;
            case "Tasks: One a day email":
                mail.setMessage(mailCreatorService.buildScheaduledTasksEmail(mail.getMessage()));
                return true;
            default: {
                return false;
            }
        }
    }

    public void send(final Mail mail){
        LOGGER.info("Starting email preparation...");
        try {
            if (isHTMLmail(mail)) {
                javaMailSender.send(createMimeMessage(mail));
            } else {
                javaMailSender.send(createMailMessage(mail));
            }
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage(), true);
        };
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if (mail.getToCC() != null) mailMessage.setCc(mail.getToCC());
        return mailMessage;
    }
}
