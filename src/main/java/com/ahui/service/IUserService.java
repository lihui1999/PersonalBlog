package com.ahui.service;


import com.ahui.domain.User;

import java.util.Map;

public interface IUserService {
    /**
     * 查询账号和密码是否正确      --根据邮箱查询
     */
    String findUserByEmail(String userEmail);

    /**
     * 查询账号密码       --根据账号查询登录
     */
    String findUserByAccount(String userAccount);

    /**
     * 查找对应用户的密码是否正确
     */
    Map<String,String> findUserByPassWord(User loginUser);
//    String findUserByPassWord(String userPassWord);

    /**
     * 查询用户账号的最后一条记录
     */
    String findLastUserAccount();

    /**
     * 查询用户手机号
     */
    String findUserByPhone(String userPhone);

    /**
     * 插入用户     --  注册
     */
    Integer insertUser(User user);



    

}
