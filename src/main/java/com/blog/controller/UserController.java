package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //用户登录验证
    @RequestMapping("/login")
    public void userlogin(@RequestParam("userid") String userId,
                                  @RequestParam("userpassword") String userPassword,
                                  HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String MD5UserPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        if (userService.ifPassword(userId, MD5UserPassword)){
            session.setAttribute("loginUser", userId);
            out.write("<script> alert('登录成功！');window.location.href='/index';</script>");
        }else {
            out.write("<script> alert('用户名或密码错误！');window.location.href='/login';</script>");
        }
    }



    //用户注册
    @RequestMapping("/regist")
    public void addUser(@RequestParam("userid") String userId,
                                @RequestParam("userpassword") String userPassword,
                                @RequestParam("userpassword_check") String userPasswordCheck,
                                HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        if (!userId.equals("") && !userPassword.equals("") && userPassword.equals(userPasswordCheck)) {
            if(!userService.ifUser(userId)) {
                out.write("<script> alert('该用户已存在！');window.location.href='/regist';</script>");
            }else {
                User user = new User();
                user.setUserId(userId);
                String MD5UserPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());
                user.setUserPassword(MD5UserPassword);
                userService.addUser(user);
                out.write("<script> alert('注册成功！现在跳转登录');window.location.href='/login';</script>");
            }
        }else {
            out.write("<script> window.location.href='/regist';</script>");
        }
    }

    //修改用户信息
    @RequestMapping("/changedata")
    public void changeData(@RequestParam("userpassword") String userPassword,
                           @RequestParam("userpassword_check") String userPasswordCheck,
                           @RequestParam("school") String school,
                           @RequestParam("introduction") String introduction,
                           HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        if (!userPassword.equals("") && userPassword.equals(userPasswordCheck)) {
            User user = new User();
            String userId = session.getAttribute("loginUser").toString();
            user.setUserId(userId);
            String MD5UserPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());
            user.setUserPassword(MD5UserPassword);
            user.setSchool(school);
            user.setIntroduction(introduction);
            userService.changeUserData(user);
            out.write("<script> alert('修改成功！');window.location.href='/index';</script>");
        }else {
            out.write("<script> alert('修改失败！请检查个人信息');window.location.href='/personal_center';</script>");
        }
    }
}
