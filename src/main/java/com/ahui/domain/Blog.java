package com.ahui.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * 对应mysql的Blog博文表
 */
@Component("blog")
public class Blog implements Serializable {
    private Integer blogID = null;//id
    private Integer blogUserID = null; //博文的发布用户
    private Date blogReleaseDate = null; //博文的发布日期
    private String blogLabel = null; //博文的标签
    private String blogTitle = null; //标题
    private String blogContent = null; //内容  其mysql中数据类型是longtext  所以java中用String存储
    private String blogImg = null; //图片  图片的路径

    public Integer getBlogID() {
        return blogID;
    }

    public void setBlogID(Integer blogID) {
        this.blogID = blogID;
    }

    public Integer getBlogUserID() {
        return blogUserID;
    }

    public void setBlogUserID(Integer blogUserID) {
        this.blogUserID = blogUserID;
    }

    public Date getBlogReleaseDate() {
        return blogReleaseDate;
    }

    public void setBlogReleaseDate(Date blogReleaseDate) {
        this.blogReleaseDate = blogReleaseDate;
    }

    public String getBlogLabel() {
        return blogLabel;
    }

    public void setBlogLabel(String blogLabel) {
        this.blogLabel = blogLabel;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogImg() {
        return blogImg;
    }

    public void setBlogImg(String blogImg) {
        this.blogImg = blogImg;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogID=" + blogID +
                ", blogUserID=" + blogUserID +
                ", blogReleaseDate=" + blogReleaseDate +
                ", blogLabel='" + blogLabel + '\'' +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", blogImg='" + blogImg + '\'' +
                '}';
    }
}
