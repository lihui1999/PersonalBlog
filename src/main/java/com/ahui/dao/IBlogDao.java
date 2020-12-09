package com.ahui.dao;

import com.ahui.domain.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博文的持久层接口
 */
@Repository
public interface IBlogDao {
    /**
     * 根据ID获取博文
     */
    Blog findBlogByID(Integer blogID);

    /**
     * 查询所有Blog
     * @return
     */
    List<Blog> findAllBlog();

    /**
     * 插入博文
     */
    Integer insertBlog(Blog blog);

}
