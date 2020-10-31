package com.blog.mapper;

import com.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    //获取博客评论
    List<Comment> getBlogComment(int blogId);

    //插入博客评论
    void insertBlogComment(Comment comment);
}
