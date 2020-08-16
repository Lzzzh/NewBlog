package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/regist")
    public ModelAndView addUser(@RequestParam("userid") String userId,
                                @RequestParam("username") String username,
                                @RequestParam("userpassword") String userPassword,
                                @RequestParam("userpassword_check") String userPasswordCheck,
                                @RequestParam("sex") int sex,
                                HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        if (!userId.equals("") && !username.equals("") && !userPassword.equals("") && userPassword.equals(userPasswordCheck)) {
            if(!userService.ifUser(userId)) {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script language='javascript'>");
                out.println("alert('该用户已存在！');");
                out.print("</script>");
                mv.setViewName("regist");
                return mv;
            }
            User user = new User();
            user.setUserId(userId);
            user.setUserName(username);
            user.setUserPassword(userPassword);
            userService.addUser(user);
            mv.setViewName("index");
            return mv;
        }
        mv.setViewName("regist");
        return mv;
    }
}
