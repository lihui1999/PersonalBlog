package com.ahui.controller;

import com.ahui.domain.Account;
import com.ahui.service.IAccountService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@Controller("accountController")
@RequestMapping("/account")
@SessionAttributes(value = "aa",types = Integer.class)
public class AccountController {
    @Autowired
    private IAccountService accountService;


    /**
     *      测试搭建ssm环境
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(){
        System.out.println("执行了controller的查询所有方法123");
        List<Account> accounts =  accountService.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
        return "success";
    }

    /**
     * 测试 不分离 保存数据
     * @param account
     * @return
     */
    @RequestMapping("/save")
    public String save(Account account){
        accountService.saveAccount(account);
        return "success";
    }

    /**
     *      测试前后端分离json
     * @param map
     * @return
     */
    @RequestMapping(value = "/jsonUp",method = RequestMethod.POST)
    @ResponseBody
    public Account jsonUp(@RequestBody Map<String,String> map){
        //response.setHeader("Access-Control-Allow-Origin", "*");

        int id = Integer.parseInt(map.get("id"));
        System.out.println(id);
        Account account = accountService.findById(2);

        System.out.println(account);
        return account;
    }


    @RequestMapping("testPut")
    public void add(Model model){
        model.addAttribute("aa","AAA");
        System.out.println("addddddddddddddddddddddddddddddddddd");
        System.out.println(model);
    }

    @RequestMapping("/testGet")
    public void test(ModelMap modelMap){
        modelMap.get("aa");
        System.out.println("tttttttttttttttttttttt");
        System.out.println(modelMap.get("aa"));
    }
}
