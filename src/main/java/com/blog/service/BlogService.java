package com.blog.service;

import com.blog.entity.Blog;

import java.sql.Timestamp;
import java.util.List;

public interface BlogService {
    //插入博客内容
    void insertBlog(Blog blog);

    List<Blog> getBlogList();

    List<Blog> getOneBlog(Timestamp dateTime);
}
