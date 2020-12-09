package com.ahui.service.impl;

import com.ahui.dao.IBlogDao;
import com.ahui.domain.Blog;
import com.ahui.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private IBlogDao blogDao = null;    //利用spring创建接口对象

    /**
     * 插入博文
     */
    @Override
    public Integer insertBlog(Blog blog) {
        return blogDao.insertBlog(blog);
    }

    /**
     * 查询所有Blog
     */
    @Override
    public List<Blog> findAllBlog() {
        return blogDao.findAllBlog();
    }

    /**
     * 根据ID获取博文
     */
    @Override
    public Blog findBlogByID(Integer blogID) {

        return blogDao.findBlogByID(blogID);
    }
}
