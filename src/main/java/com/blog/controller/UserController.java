package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String userlogin(@RequestParam("userid") String userId,
                                  @RequestParam("userpassword") String userPassword,
                                  HttpServletResponse response, HttpSession session) throws IOException, ServletException {
//        ModelAndView modelAndView = new ModelAndView();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        if (userService.ifPassword(userId, userPassword)){
            out.println("<script language='javascript'>");
            out.println("alert('登录成功！');");
            out.print("</script>");
            //model.addAttribute("user", user);
//            modelAndView.setViewName("index");
            session.setAttribute("loginUser", userId);
            return "index";
        }else {
            out.println("<script language='javascript'>");
            out.println("alert('用户名或密码错误！');");
            out.print("</script>");
//            modelAndView.setViewName("regist");
            return "login";
        }
    }

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
            PrintWriter out = response.getWriter();
            out.println("<script language='javascript'>");
            out.println("alert('注册成功请登录！');");
            out.print("</script>");
            mv.setViewName("login");
            return mv;
        }
        mv.setViewName("regist");
        return mv;
    }
}
