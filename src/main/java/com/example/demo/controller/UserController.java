package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
