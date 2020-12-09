package com.ahui.dao;

import com.ahui.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {
//    /**
//     * 查询用户邮箱是否正确    --根据邮箱查询
//     */
//    @Select("select * from user where userEmail=#{userEmail}")
//    String findUserByEmail(String userEmail);
//
//    /**
//     * 查询用户账号是否正确    --根据账号查询
//     */
//    @Select("select * from user where userAccount=#{userAccount}")
//    String findUserByAccount(String userAccount);
//
//
//    /**
//     * 查找对应用户的密码是否正确
//     */
//    @Select("select userPassWord from user where (userPassWord = #{userPassWord} and userAccount = #{userAccountOrEmail})" +
//            "or (userPassWord = #{userPassWord} and userEmail = #{userAccountOrEmail})")
//    @Results(value = {
//            @Result(column = "userPassWord",property = "userPassWord"),
//            @Result(column = "userAccount",property = "userAccountOrEmail"),
//            @Result(column = "userEmail",property = "userAccountOrEmail")
//    })
//    String findUserByPassWord(String userPassWord,String userAccountOrEmail);
//
//
//    /**
//     * 查找账号密码
//     */
////    User findUser(User user);
//
//    /**
//     * 插入用户信息   --注册
//     */
//    @Insert("insert into user (userEmail,userPassWord,userStatus) values(#{userEmail},#{userPassWord},#{userStatus})")
//    Integer insertUser(User user);












    /**
     * 查询用户邮箱是否正确    --根据邮箱查询
     */
  //  @Select("select * from user where userEmail=#{userEmail}")
    String findUserByEmail(String userEmail);

    /**
     * 查询用户账号是否正确    --根据账号查询
     */
   // @Select("select * from user where userAccount=#{userAccount}")
    String findUserByAccount(String userAccount);

    String findUserByPhone(String userPhone);

    /**
     * 查找对应用户的密码是否正确
     */
    String findUserByPassWord(User loginUser);


    /**
     * 查找账号密码
     */
//    User findUser(User user);

    /**
     * 查询 账号的最后一条记录
     */
    String findLastUserAccount();

    /**
     * 插入用户信息   --注册
     */
    //@Insert("insert into user (userEmail,userPassWord,userStatus) values(#{userEmail},#{userPassWord},#{userStatus})")
    Integer insertUser(User user);


    List<User> findAll();
}
