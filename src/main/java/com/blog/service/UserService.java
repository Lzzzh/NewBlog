package com.blog.service;

import com.blog.entity.User;

public interface UserService {
    //添加用户
    void addUser(User user);

    //查询用户是否存在
    boolean ifUser(String userId);

    //验证用户密码
    boolean ifPassword(String userId, String userPassword);
}
