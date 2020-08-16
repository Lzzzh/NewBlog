package com.blog.mapper;

import com.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    //查询
    int ifUser(String userId);

    //添加用户
    void addUser(User user);


}
