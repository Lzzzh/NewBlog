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
    public String userlogin(@RequestParam("userid") String userId,
                                  @RequestParam("userpassword") String userPassword,
                                  HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String MD5UserPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        if (userService.ifPassword(userId, MD5UserPassword)){
//            out.println("<script language='javascript'>");
//            out.println("alert('登录成功！');");
//            out.print("</script>");
            session.setAttribute("loginUser", userId);
            return "redirect:/index";
        }else {
            out.println("<script language='javascript'>");
            out.println("alert('用户名或密码错误！');");
            out.print("</script>");
            return "login";
        }
    }



    //用户注册
    @RequestMapping("/regist")
    public String addUser(@RequestParam("userid") String userId,
                                @RequestParam("username") String username,
                                @RequestParam("userpassword") String userPassword,
                                @RequestParam("userpassword_check") String userPasswordCheck,
                                @RequestParam("sex") int sex,
                                HttpServletResponse response) throws IOException {
        if (!userId.equals("") && !username.equals("") && !userPassword.equals("") && userPassword.equals(userPasswordCheck)) {
            if(!userService.ifUser(userId)) {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script language='javascript'>");
                out.println("alert('该用户已存在！');");
                out.print("</script>");
                return "regist";
            }
            User user = new User();
            user.setUserId(userId);
            user.setUserName(username);
            String MD5UserPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());
            user.setUserPassword(MD5UserPassword);
            userService.addUser(user);
            return "redirect:/login";
        }
        return "login";
    }

}
