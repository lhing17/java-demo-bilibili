package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
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
        return userService.getUser();
    }

    @GetMapping("/getUserType")
    public Integer getUserType(String userId) {
        return userService.getUserType(userId);
    }

    @GetMapping("/v1/user/{id}")
    public User getById1(@PathVariable String id) {
        return userService.getByUserId1(id);
    }

    @GetMapping("/v2/user/{id}")
    public User getById2(@PathVariable String id) {
        return userService.getByUserId2(id);
    }
}
