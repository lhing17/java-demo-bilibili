package com.example.demo.service;

import com.example.demo.datasource.UsingDataSource;
import com.example.demo.model.User;

public interface UserService {
    String getUser();

    @UsingDataSource("ds1")
    User getByUserId1(String userId);

    User getByUserId(String userId);

    @UsingDataSource("ds2")
    User getByUserId2(String userId);

    Integer getUserType(String userId);
}
