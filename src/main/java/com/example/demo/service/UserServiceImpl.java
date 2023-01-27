package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.datasource.UsingDataSource;
import com.example.demo.model.User;
import com.example.demo.model.UserTypeEnum;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    HttpServletRequest request;

    @Resource
    UserDao userDao;

    @Override
    public String getUser() {
        System.out.println(request.getParameter("name"));
        return "user";
    }


    @Override
    @UsingDataSource("ds1")
    public User getByUserId1(String userId) {
        return userDao.getById(Integer.parseInt(userId));
    }

    @Override
    public User getByUserId(String userId) {
        UserService o = (UserService) AopContext.currentProxy();
        return o.getByUserId2(userId);
    }

    @Override
    @UsingDataSource("ds2")
    public User getByUserId2(String userId) {
        return userDao.getById(Integer.parseInt(userId));
    }

    @Override
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
        User user = getByUserId1(userId);
        if (user == null) {
            throw new RuntimeException("未找到用户");
        }
        return user;
    }
}
