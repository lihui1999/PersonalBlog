package com.ahui.controller;

import com.ahui.domain.User;
import com.ahui.service.IUserService;
import com.ahui.untils.MailSenderSrvSerices;
import com.ahui.untils.impl.RandomValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller("/login")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private IUserService userService;   //UserService对象
    @Autowired
    private User loginUser;

    private String rememberAccountOrEmailAndPassWord = null;

    private String securityCodeServer = null;
//    private Boolean rememberUserPassWord;
    private String rememberUserPassWord;
    @Autowired
    private MailSenderSrvSerices mailSenderSrvSerices;  //发送邮件对象
    private Integer num = null;

    @RequestMapping(value="/checkCode",method = RequestMethod.GET)
    @ResponseBody
    public void  checkCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
//        HttpSession session1 =
        Map<String,String> jsonMap = new HashMap<>();
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");

        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
//            System.out.println(randomValidateCode.getRandomString());
//            System.out.println(request.getCookies("randomcode_key"));
            securityCodeServer = (String)session.getAttribute("randomcode_key");
            System.out.println(session.getAttribute("randomcode_key"));
            System.out.println("checkCode方法产生的验证码");
            System.out.println(securityCodeServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/findUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> login(@RequestBody Map<String,String> map,HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //获取到账号或者邮箱，并进行判断
        String userAccountOrEmail =  map.get("userAccountOrEmail");   //获取到账号
        String userPassWord = map.get("userPassWord");//获取密码
        String securityCodeClient = map.get("securityCodeClient");//获取到验证码

        //rememberUserPassWord = Boolean.parseBoolean(map.get("rememberUserPassWord")); //获取是否记住密码，并转成boolean
        rememberUserPassWord = map.get("rememberUserPassWord"); //获取是否记住密码，并转成boolean
        System.out.println("前端的数据"+userAccountOrEmail);
        System.out.println("前端的数据"+userPassWord);
        System.out.println("前端的数据"+securityCodeClient);
        System.out.println("前端的数据"+rememberUserPassWord);
        // 创建用于返回json的map
        Map<String,String> jsonMap = new HashMap<>();
//        securityCodeServer = (String)session.getAttribute("randomcode_key");
//        jsonMap.put("securityCodeServer",securityCodeServer);
//        System.out.println(jsonMap);

        //验证 验证码
        securityCodeServer = (String) session.getAttribute("randomcode_key");   //从session中拿到验证码
        jsonMap.put("securityCodeServer",securityCodeServer);
        System.out.println("成功返回给前端securityCodeServer");
        System.out.println(jsonMap);

        //进行第二次验证码校验
        System.out.println("看看你这个验证码变量怎么回事");
        System.out.println("======="+ securityCodeServer);
//        System.out.println(session.getAttribute("randomcode_key"));

        if (securityCodeServer.equals(securityCodeClient)){
            jsonMap.put("securityCodeStatus","securityCodeSucceed");
            //return jsonMap;
        }else {
            jsonMap.put("securityCodeStatus","securityCodeError");
            return jsonMap;
        }

        System.out.println("验证码校验成功");
        System.out.println(jsonMap);

       // securityCodeServer = null; //用于存储服务器产生的验证码
        //进行数据封装到实体类
        //User user = new User();
       // user.setUserPassWord(userPassWord); //由于不知道是账号还是邮箱，所以先封装密码
//        System.out.println(session.getAttribute("securityCodeLogin"));
//        System.out.println(session.getAttribute("securityCodeLogin"));
        //调用方法进行判断，是账号登录还是邮箱登录      若账号成功则进行验证密码
        userAccountOrEmailStatus(userAccountOrEmail,userPassWord);
        //把判断账号的信息放进jsonMap
        jsonMap.put("userAccountOrEmailStatus",userAccountOrEmailStatus(userAccountOrEmail,userPassWord).get("userAccountOrEmailStatus"));
//        return jsonMap;
        if (jsonMap.get("userAccountOrEmailStatus").equals("userAccountOrEmailError")){ //如果返回的值中是error那么说明账号错误
            return jsonMap;
        }else {
            loginUser.setUserPassWord(userPassWord);
            if ("userPassWordSucceed".equals(userService.findUserByPassWord(loginUser).get("userPassWordServiceStatus"))){
//            if (userService.findUserByPassWord(userPassWord) != null){
               // loginUser.setUserPassWord(userPassWord);
                System.out.println("密码查询成功");
                System.out.println(userPassWord);
                rememberAccountOrEmailAndPassWord = rememberAccountOrEmailAndPassWord + "-"+userPassWord;
                jsonMap.put("userPassWordStatus","userPassWordSucceed");
                //return jsonMap;
            }else {
                jsonMap.put("userPassWordStatus","userPassWordError");
                return jsonMap;
            }
        }

        System.out.println(jsonMap);
        System.out.println("账号密码验证完成");


        //判断是否选择记住密码      保证账号密码正确，验证码也交给前端了   现在账号密码已经全部封装完毕  正式开始cookie了

        if (rememberUserPassWord.equals("true")){
           // String loginInfo = loginUser.getUserAccount()+":"+loginUser.getUserEmail()+":"+loginUser.getUserPassWord();
            jsonMap.put("rememberUserPassWordStatus",rememberAccountOrEmailAndPassWord);
            System.out.println("记住密码");
            System.out.println(jsonMap);
            return jsonMap;
        }else{
            jsonMap.put("rememberUserPassWordStatus","rememberUserPassWordStatusError");
            System.out.println("没有记住密码");
            System.out.println(jsonMap);
            return jsonMap;
        }
    }






    //定义方法判断账号是否正确
    public Map<String,String> userAccountOrEmailStatus(String userAccountOrEmail,String userPassWord){
       // User user = new User();
        Map<String,String> jsonMap = new HashMap<>();
//        user.setUserPassWord(userPassWord); //由于不知道是账号还是密码登录，所以先封装密码
        if (userAccountOrEmail.contains("@")){  //若包含@说明是邮箱
           // user.setUserAccount(userAccountOrEmail);
//            if (userService.findUserByEmail(user).getUserEmail() == null){   //若为空，说明没有查到账号
            if (userService.findUserByEmail(userAccountOrEmail) != null){   //若不为空，则说明查到了账号
               // loginUser.setUserEmail(userAccountOrEmail); //将账号封装到user
                rememberAccountOrEmailAndPassWord = userAccountOrEmail;
                loginUser.setUserEmail(userAccountOrEmail);
                System.out.println("账号成功");
                System.out.println(userAccountOrEmail);
                jsonMap.put("userAccountOrEmailStatus","userAccountOrEmailSucceed");
                return jsonMap;
            } //否则不就没有查到账号吗
                jsonMap.put("userAccountOrEmailStatus","userAccountOrEmailError");
                return jsonMap;

        }else if (userAccountOrEmail.length() == 11){ //如果长度为11  那么就说明是手机号
            if (userService.findUserByPhone(userAccountOrEmail) != null){  //如果不为空那么就说明这个手机号存在
                rememberAccountOrEmailAndPassWord = userAccountOrEmail;
                loginUser.setUserPhone(userAccountOrEmail);
                System.out.println("手机号查找成功");
                System.out.println(userAccountOrEmail);
                jsonMap.put("userAccountOrEmailStatus","userAccountOrEmailSucceed");
                return jsonMap;
            } //否则不就没有查到手机号吗
            jsonMap.put("userAccountOrEmailStatus","userAccountOrEmailError");
            return jsonMap;

        } else {
            //user.setUserEmail(userAccountOrEmail);
            if (userService.findUserByAccount(userAccountOrEmail) == null){
                jsonMap.put("userAccountOrEmailStatus","userAccountOrEmailError");
                return jsonMap;
            }
            System.out.println("账号成功");
            System.out.println(userAccountOrEmail);
            rememberAccountOrEmailAndPassWord = userAccountOrEmail;
            loginUser.setUserAccount(userAccountOrEmail);
//            loginUser.setUserAccount(userAccountOrEmail);// 将账号封装到user
            jsonMap.put("userAccountOrEmailStatus","userAccountOrEmailSucceed");
            return jsonMap;
        }
    }


}
