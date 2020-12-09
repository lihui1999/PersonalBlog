package com.ahui.service;

import com.ahui.domain.Blog;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * 博文的接口层
 */
public interface IBlogService {
    /**
     * 插入博文
     */
    Integer insertBlog(Blog blog);

    List<Blog> findAllBlog();

    /**
     * 根据ID查询博文
     */
    Blog findBlogByID(Integer blogID);
}
