package com.blog.service.impl;


import com.blog.bean.Result;
import com.blog.bean.User;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Result regist(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            User existUser = userMapper.findUserByName(user.getUserId(), user.getUserPassword());
            if (existUser != null) {
                //如果用户名已存在
                result.setMessage("用户名已存在");

            } else {
                userMapper.regist(user);
                System.out.println(user.getUserId());

                result.setMessage("注册成功");
                result.setSuccess(true);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 登录
     *
     * @param user 用户名和密码
     * @return Result
     */
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            String userId = userMapper.login(user);
            //判断是否为空
            if (userId == null || userId.equals("")) {
                result.setMessage("用户名或密码错误");
            } else {
                result.setMessage("登录成功");
                result.setSuccess(true);
                user.setUserId(userId);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}




