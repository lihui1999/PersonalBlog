package com.ahui.untils.impl;

import com.ahui.untils.MailSenderSrvSerices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("mailSenderSrvSerices")
public class MailSenderSrvSericesImpl implements MailSenderSrvSerices {
    @Autowired
    private JavaMailSenderImpl mailSender;

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String recipient, String subject, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setFrom("2641133486@qq.com");//发件人
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(content,true);//true代表支持html格式
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendHtmlEmail(String recipient, String subject, String content) throws MessagingException, Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setFrom("2641133486@qq.com");//发件人
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(content,true);
            mimeMessage.setRecipients(Message.RecipientType.CC,"3037257584@qq.com");//抄送人
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}