package com.blog.mapper;

import com.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    //查询用户是否存在
    int ifUser(String userId);

    //查询用户密码是否正确
    int ifPassword(String userId, String userPassword);

    //添加用户
    void addUser(User user);

    //修改用户信息
    void changeUserData(User user);
}
