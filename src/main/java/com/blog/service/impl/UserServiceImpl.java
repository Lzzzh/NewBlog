package com.blog.service.impl;


import com.blog.entity.User;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public boolean ifUser(String userId) {
        return userMapper.ifUser(userId) == 0;
    }

    @Override
    public boolean ifPassword(String userId, String userPassword) {
        return userMapper.ifPassword(userId, userPassword) == 1;
    }

    @Override
    public void changeUserData(User user) {
        userMapper.changeUserData(user);
    }

}




