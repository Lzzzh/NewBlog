package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.mapper.BlogMapper;
import com.blog.service.BlogService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/{dateTime}")
    public String showBlog(@PathVariable(name = "dateTime")String time, Model model) {
        String dateTimeStr = time.split("\\.")[0];
        Timestamp dateTime = Timestamp.valueOf(dateTimeStr);
        List<Blog> list = blogService.getOneBlog(dateTime);
        model.addAttribute("blogdata", list.get(0));
        return "blog";
    }

    @RequestMapping("/addblog")
    public String addBlog(@RequestParam("blogtitle") String blogTitle,
            @RequestParam("blogtext") String blogText,
                          HttpServletRequest request) {
        if (!blogText.equals("") && request.getSession(false) != null) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化类型
            String currentTime = sf.format(new Date().getTime());// 获取当前日期和时间
            Timestamp ct = Timestamp.valueOf(currentTime);// 将 String 类型转为 Timestamp 类型
            Blog blog = new Blog();
            blog.setUserId(request.getSession().getAttribute("loginUser").toString());
            blog.setBlogTitle(blogTitle);
            blog.setBlogText(blogText);
            blog.setDateTime(ct);
            blogService.insertBlog(blog);
            return "redirect:/index";
        }else {
            return "redirect:/write";
        }
    }

    @RequestMapping("/index")
    public String getBlogList(Model model) {
        List<Blog> list = blogService.getBlogList();
        model.addAttribute("lists", list);
        return "index";
    }

    @RequestMapping("/write")
    public String write () {
        return "write";
    }

    @RequestMapping("/blog")
    public String blog () {
        return "blog";
    }

    @RequestMapping("/personal_center")
    public String PersonalCenter () {
        return "personal_center";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/regist")
    public String regist() {
        return "regist";
    }
}
