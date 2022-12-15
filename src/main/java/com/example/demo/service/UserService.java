package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserTypeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Resource
    HttpServletRequest request;

    public String getUser() {
        System.out.println(request.getParameter("name"));
        return "user";
    }


    public User getByUserId(String userId) {
        if ("1".equals(userId)) {
            return new User("张三", 15, 1);
        } else if ("2".equals(userId)) {
            return new User("李四", 20, 1);
        } else if ("3".equals(userId)) {
            return new User("王五", 70, 1);
        }

        return null;

    }

    public Integer getUserType(String userId) {
        User user = getUser(userId);
        return getUserTypeByUser(user);


    }

    private int getUserTypeByUser(User user) {
        if (user.getAge() < 18) {
            return UserTypeEnum.UNDER_AGE.getCode();
        } else if (user.getAge() < 65) {
            return UserTypeEnum.ADULT.getCode();
        } else {
            return UserTypeEnum.ELDER.getCode();
        }
    }

    private User getUser(String userId) {
        User user = getByUserId(userId);
        if (user == null) {
            throw new RuntimeException("未找到用户");
        }
        return user;
    }
}
