package com.application.nutsBee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.application.nutsBee.Entity.Mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(Mail mail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        Context context = new Context();
        context.setVariables(mail.getModel());
        String body = templateEngine.process(mail.getMailTemplate(), context);
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(body, true);
        javaMailSender.send(message);
    }
    
    public void sendOtpEmail(String to, int otp) throws MessagingException {
    	MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("OTP for Password Reset");
        helper.setText("Your OTP for password reset is: " + otp);
        javaMailSender.send(message);
    }
}
