package com.blog.service;

import com.blog.bean.Result;
import com.blog.bean.User;

public interface UserService {
    Result regist(User user);

    Result login(User user);
}
