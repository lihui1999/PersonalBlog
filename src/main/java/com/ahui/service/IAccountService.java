package com.ahui.service;

import com.ahui.domain.Account;

import java.util.List;

public interface IAccountService {
    /**
     * 保存账号
     */
    void saveAccount(Account account);

    /**
     * 查询所有账户
     */
    List<Account> findAll();

    /**
     * 根据id查询用户
     */
    Account findById(Integer id);


}
