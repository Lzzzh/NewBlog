package com.blog.service;

import com.blog.entity.Result;
import com.blog.entity.User;

public interface UserService {
    //添加用户
    void addUser(User user);

    boolean ifUser(String userId) ;
}
