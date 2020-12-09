package com.ahui.test;

import com.ahui.untils.MailSenderSrvSerices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring.xml")
public class TestMail {
    private String to = "3037257584@qq.com";  //收件人地址
    private String subject = "这是一封新的邮件";   //邮件标题
    private String content = "今天天气不错";    //邮件内容

    @Test
   public void run1(){
       ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
       MailSenderSrvSerices mailSenderSrvSerices = (MailSenderSrvSerices)applicationContext.getBean("mailSenderSrvSerices");
       mailSenderSrvSerices.sendEmail(to,subject,content);
   }



}
