package com.blog.service.impl;

import com.blog.entity.Comment;
import com.blog.mapper.CommentMapper;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haoge
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> getBlogComments(int blogId) {
        return commentMapper.getBlogComment(blogId);
    }

    @Override
    public void insertBlogComments(Comment comment) {
        commentMapper.insertBlogComment(comment);
    }
}
