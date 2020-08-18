package com.blog.config;

import com.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    public final static String SESSION_KEY = "loginUser";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/emp/**","/index.html", "/", "/index", "/write.html", "/personal_center.html").excludePathPatterns("/login", "/login.html");
    }
}
