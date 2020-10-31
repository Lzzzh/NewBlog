package com.blog.service.impl;

import com.blog.entity.Blog;
import com.blog.mapper.BlogMapper;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void insertBlog(Blog blog) {
        blogMapper.insertBlog(blog);
    }

    @Override
    public List<Blog> getBlogList() {
        return blogMapper.getBlogList();
    }

    @Override
    public List<Blog> getOneBlog(Timestamp dateTime) {
        return blogMapper.getOneBlog(dateTime);
    }
}
