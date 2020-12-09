package com.ahui.service.impl;

import com.ahui.dao.IUserDao;
import com.ahui.domain.User;
import com.ahui.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    /**
     * 根据  邮箱  查询用户
     */
    @Override
    public String findUserByEmail(String userEmail) {
        return userDao.findUserByEmail(userEmail);
    }

    /**
     * 根据  账号  查询用户
     */
    @Override
    public String findUserByAccount(String userAccount) {
        return userDao.findUserByAccount(userAccount);
    }

    /**
     * 查找对应用户的密码是否正确
     */
    @Override
    public Map<String,String> findUserByPassWord(User loginUser){
        Map<String,String> map = new HashMap<String, String>();
        if (userDao.findUserByPassWord(loginUser) == null){  //如果空说明密码错误
            map.put("userPassWordServiceStatus","userPassWordError");
            return map;
        }else{
            map.put("userPassWordServiceStatus","userPassWordSucceed");
            return map;
        }
    }


//    @Override
//    public String findUserByPassWord(String userPassWord) {
//        return userDao.findUserByPassWord(userPassWord);
//    }


    /**
     * 查询用户账号的最后一条记录
     */
    @Override
    public String findLastUserAccount() {
        return userDao.findLastUserAccount();
    }

    /**
     * 查询用户手机号
     */
    @Override
    public String findUserByPhone(String userPhone) {
        return userDao.findUserByPhone(userPhone);
    }

    /**
     * 注册账户
     */
    @Override
    public Integer insertUser(User user) {
        return userDao.insertUser(user);
    }
}
