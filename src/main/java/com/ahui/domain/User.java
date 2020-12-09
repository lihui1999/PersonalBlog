package com.ahui.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应mysql中的User表，用于存储用户的基本信息
 */
@Component("user")
public class User implements Serializable {
    /*
    userId	int
userAccount	varchar
userPhone	int
userEmail	varchar
userPassWord	varchar
userName	varchar
userSex	varchar
userBirthday	date
userAddress	varchar
userImage	varchar
userStatus	varchar

     */
    private Integer userID = null;     //*
    private String userAccount = null;     //*
    private String userPhone = null;
    private String userEmail = null;   //*
    private String userPassWord = null;    //*
    private String userName = null;
    private String userSex = null;
    private Date userBirthday = null;
    private String userAddress = null;
    private String userImage = null;
    private String userStatus = "user";  //*

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userAccount='" + userAccount + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassWord='" + userPassWord + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userBirthday=" + userBirthday +
                ", userAddress='" + userAddress + '\'' +
                ", userImage='" + userImage + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}
