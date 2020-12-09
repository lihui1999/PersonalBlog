package com.ahui.untils;

import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

public interface MailSenderSrvSerices {
    /**
     *      普通格式发送
     * @param recipient 收件人地址
     * @param subject   主题
     * @param content   正文
     */
    @Transactional
    void sendEmail(String recipient,String subject,String content);

    /**  带抄送  */
    void sendHtmlEmail(String recipient,String subject,String content)throws MessagingException,Exception;
}
