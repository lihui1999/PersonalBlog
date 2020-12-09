package com.ahui.test;

import com.ahui.domain.Account;
import com.ahui.domain.User;
import com.ahui.service.IAccountService;
import com.ahui.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class TestSpring {
    @Test
    public void run1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        IAccountService accountService = (IAccountService)applicationContext.getBean("accountService");
//        List<Account> accounts =  accountService.findAll();
//        System.out.println(accounts);

        IUserService userService = (IUserService)applicationContext.getBean("userService");
//        Map<String,String> map = userService.findUserByPassWord("li13964021617");
//        System.out.println(map);

        //2020/12/3
//        User user = new User();
//        user.setUserEmail("3037257584@qq.com");
//        user.setUserPassWord("lihuilihui");
//        int i = userService.insertUser(user);
//        System.out.println(i);

        //2020/12/4
        String userEmail = userService.findUserByEmail("2641133486@qq.com");
        System.out.println(userEmail);
    }
}
