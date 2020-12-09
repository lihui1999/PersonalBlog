package com.ahui.service.impl;

import com.ahui.dao.IAccountDao;
import com.ahui.domain.Account;
import com.ahui.service.IAccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    public void saveAccount(Account account) {
        System.out.println("调用了service层的保存方法");
        accountDao.saveAccount(account);
    }

    public List<Account> findAll() {
        System.out.println("调用了 service 的查询全部方法");
        return accountDao.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }
}
