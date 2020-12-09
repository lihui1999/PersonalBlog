package com.ahui.test;

import com.ahui.dao.IAccountDao;
import com.ahui.dao.IUserDao;
import com.ahui.domain.Account;
import com.ahui.service.IUserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    @Test
    public void run1() throws IOException {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //根据工厂创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //根据sqlSession反射创建代理对象
        IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
        //执行相应方法
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
        //释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void run2() throws IOException {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //根据工厂创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //根据sqlSession反射创建代理对象
        IAccountDao accountDao = sqlSession.getMapper(IAccountDao.class);
        //执行相应方法
        Account account = new Account();
        account.setName("阿伟");
        account.setMoney(300f);
        accountDao.saveAccount(account);

        sqlSession.commit();
        //释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void run3() throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//        userDao.findUserByPassWord("li13964021617");
        sqlSession.close();
        in.close();

    }
}
