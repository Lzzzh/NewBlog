package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/")
    public String toBlogList(Model model) {
        List<Blog> list = blogService.getBlogList();
        model.addAttribute("lists", list);
        return "index";
    }
    @RequestMapping("/index")
    public String BlogList(Model model) {
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
