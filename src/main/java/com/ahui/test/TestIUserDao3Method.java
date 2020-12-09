package com.ahui.test;

import com.ahui.dao.IUserDao;
import com.ahui.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestIUserDao3Method {
    @Autowired
    private IUserDao userDao;

    @Test
    public void findAll() throws IOException {
//        List<User> users = userDao.findAll();
//        for (User user : users) {
//            System.out.println(user);
//        }

//        String email = userDao.findUserByEmail("2641133486@qq.com");
//        System.out.println(email);

        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String email = userDao.findUserByEmail("2641133486@qq.com");
        System.out.println(email);
        sqlSession.close();
        in.close();
    }
    @Test
    public void run2(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findLasyAccount(){
        String account = userDao.findLastUserAccount();
        System.out.println(account);
    }
}
