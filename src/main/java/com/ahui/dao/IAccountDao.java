package com.ahui.dao;

import com.ahui.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
*  持久层接口
*/
@Repository
public interface IAccountDao {
    /**
     * 保存账户
     */
    @Insert("insert into account (name,money) values(#{name},#{money})")
    void saveAccount(Account account);

    /**
     * 查询所有账户
     */
    @Select("select * from account")
    List<Account> findAll();

    /**
     * 根据id查询用户
     */
    @Select("select * from account where id = #{id}")
    Account findById(Integer id);
}