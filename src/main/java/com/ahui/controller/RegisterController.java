package com.ahui.controller;

import com.ahui.domain.User;
import com.ahui.service.IUserService;
import com.ahui.untils.MailSenderSrvSerices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.random;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller("registerController")
@RequestMapping("/register")
@SessionAttributes(value = "securityCodeRegister",types = Integer.class)
public class RegisterController {
    @Autowired
    private IUserService userService;

    @Autowired
    private MailSenderSrvSerices mailSenderSrvSerices;  //发送邮件对象

    private String to = "3037257584@qq.com";  //收件人地址
    private String subject = "验证码";   //邮件标题
    private String content = null;    //邮件内容
    private Integer num = null;
    private String newUserAccount = null; //存储新随机产生的账号


    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> registerUser(@RequestBody Map<String,String> map, HttpServletRequest request,HttpServletResponse response){
        Map<String,String> jsonMap = new HashMap<String,String>();//用于返回json的验证码等信息
        User user = new User(); //用于给service层传输数据
        HttpSession session = request.getSession(); //session对象
        //获取前端输入的内容
        String userEmail = map.get("userEmail");    //获取到前端的输入的  邮箱
        String userPassWord = map.get("userPassWord");  //获取到前端的  密码
        String repeatUserPassWord = map.get("repeatUserPassWord");  //获取前端的  重复确认密码
        String securityEmailClientCode = map.get("securityEmailClientCode");  //获取前端输入的  验证码

        System.out.println("前端用户输入的信息"+userEmail);
        System.out.println("前端用户输入的信息"+userPassWord);
        System.out.println("前端用户输入的信息"+repeatUserPassWord);
        System.out.println("前端用户输入的信息"+securityEmailClientCode);
        //进行验证码是否与输入的一致
        String sessionSecurityEmailServerCode = (String)session.getAttribute("securityEmailServerCode") ; //获取发送的验证码
        System.out.println("服务器产生的验证码"+sessionSecurityEmailServerCode);
        if (securityEmailClientCode.equals(sessionSecurityEmailServerCode) == false) {
            jsonMap.put("securityCodeRegisterStatus","securityCodeRegisterError");
            System.out.println("验证验证码失败");
            System.out.println(jsonMap);
            return jsonMap;
        }

        //进行查询该邮箱已经注册过，防止重复注册
        if (userService.findUserByEmail(userEmail) != null){
            System.out.println("用户已经注册");
            jsonMap.put("userEmailStatus","userEmailRepeatError");
            return jsonMap;
        }


        //进行二次验证，判断两次密码是否一致
        if (userPassWord.equals(repeatUserPassWord) == false) {
            jsonMap.put("repeatUserPassWord","repeatUserPassWordError");
            System.out.println("两次验证密码失败");
            System.out.println(jsonMap);
            return jsonMap;
        }


        //所有验证工作结束，进行动态产生账号
        Long lastUserAccount = Long.parseLong(userService.findLastUserAccount());
        for (long i = lastUserAccount + 1; i<999999999&&i<lastUserAccount+2;i++){
            System.out.println("动态产生的账号"+i);
            newUserAccount = Long.toString(i);
        }

        //进行数据封装
        user.setUserEmail(userEmail);
        user.setUserPassWord(userPassWord);
        user.setUserAccount(newUserAccount);
        System.out.println("查看用户封装信息");
        System.out.println(user);
        Integer insertUserStatus = userService.insertUser(user);
        if (insertUserStatus == 1) {
            jsonMap.put("insertUserStatus","insertUserStatusSuccess");
            System.out.println("插入数据成功");
            System.out.println(jsonMap);
            return jsonMap;
        }else {
            jsonMap.put("insertUserStatus","insertUserStatusError");
            System.out.println("插入数据失败");
            System.out.println(jsonMap);
            return jsonMap;
        }
    }

    @RequestMapping("/securityEmailCode")
    @ResponseBody
    public Map<String,String> img(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        num = (int) ((random() * 9 + 1) * 100000);//验证码
        session.setAttribute("securityEmailServerCode",Integer.toString(num));
//        model.addAttribute("securityEmailServerCode",Integer.toString(num));
        content = "注册验证码为:" + num + "，请勿泄露给他人使用，5分钟内有效!";
        mailSenderSrvSerices.sendEmail(to, subject, content);
//        model.addAttribute("securityCode", newNum);
        Map<String,String> jsonMap = new HashMap<String, String>();
        jsonMap.put("securityEmailServerCode",Integer.toString(num));
        System.out.println("后端产生的邮箱验证码   "+num);
        System.out.println(jsonMap);
        return jsonMap;
    }
}
