package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.Comment;
import com.blog.mapper.BlogMapper;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/{dateTime}")
    public String showBlog(@PathVariable(name = "dateTime")String time, Model model) {
        String dateTimeStr = time.split("\\.")[0];
        Timestamp dateTime = Timestamp.valueOf(dateTimeStr);
        List<Blog> list = blogService.getOneBlog(dateTime);
        int blogId = list.get(0).getId();
        List<Comment> commentList = commentService.getBlogComments(blogId);
        model.addAttribute("blogdata", list.get(0));
        model.addAttribute("blogcomment", commentList);
        return "blog";
    }
    @RequestMapping("/addcomment")
    public void addComment(@RequestParam("commenttext") String blogComment,
                           @RequestParam("blogTime") String time,
                           HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String dateTimeStr = time.split("\\.")[0].replace("T", " ");
        Timestamp dateTime = Timestamp.valueOf(dateTimeStr);
        List<Blog> list = blogService.getOneBlog(dateTime);
        int blogId = list.get(0).getId();
        Comment comment = new Comment();
        comment.setBlogId(blogId);
        comment.setBlogComment(blogComment);
        comment.setCommentUserId(request.getSession().getAttribute("loginUser").toString());
        commentService.insertBlogComments(comment);
        out.write("<script>alert('发表成功！');window.location.href='/index';</script>");
    }

    @RequestMapping("/addblog")
    public void addBlog(@RequestParam("blogtitle") String blogTitle,
                          @RequestParam("blogtext") String blogText,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        InputStream in;

        if (!blogText.equals("") && request.getSession(false) != null && !blogText.trim().equals("")) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化类型
            String currentTime = sf.format(new Date().getTime());// 获取当前日期和时间
            Timestamp ct = Timestamp.valueOf(currentTime);// 将 String 类型转为 Timestamp 类型
            Blog blog = new Blog();
            blog.setUserId(request.getSession().getAttribute("loginUser").toString());
            blog.setBlogTitle(blogTitle);
            blog.setBlogText(blogText);
            blog.setDateTime(ct);
            blogService.insertBlog(blog);
            out.write("<script> alert('发表成功！');window.location.href='/index';</script>");
        }else {
            out.write("<script> alert('发表失败，请检查博客！');window.location.href='/write';</script>");
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
