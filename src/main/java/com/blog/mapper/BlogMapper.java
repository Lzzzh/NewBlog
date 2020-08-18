package com.blog.mapper;

import com.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    void insertBlog(Blog blog);

    List<Blog> getBlogList();

    List<Blog> getOneBlog(Timestamp dateTime);
}
