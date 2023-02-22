package com.example.demo.controller;

import com.example.demo.datasource.UsingDataSource;
import com.example.demo.exception.GseinException;
import com.example.demo.model.Result;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/user")
    public String user(HttpServletRequest request) {
        System.out.println("abc");
        System.out.println("abc");
            throw new GseinException("请求获取用户信息失败");

//        return userService.getUser();
    }

    @GetMapping("/getUserType")
    public Integer getUserType(String userId) {
        return userService.getUserType(userId);
    }

    @GetMapping("/v1/user/{id}")
    @UsingDataSource("ds1")
    public User getById1(@PathVariable String id) {
        return userService.getByUserId1(id);
    }

    @GetMapping("/v2/user/{id}")
    public User getById2(@PathVariable String id) {
        return userService.getByUserId(id);
    }
}
