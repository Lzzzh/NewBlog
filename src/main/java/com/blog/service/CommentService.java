package com.blog.service;

import com.blog.entity.Comment;

import java.util.List;

public interface CommentService {
    //获取博客评论
    List<Comment> getBlogComments(int blogId);

    //插入博客评论
    void insertBlogComments(Comment comment);
}
