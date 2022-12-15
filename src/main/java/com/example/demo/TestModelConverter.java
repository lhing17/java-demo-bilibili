package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestModelConverter {


    public static void main(String[] args) {

        // 给定User的List，转换为UserDto的List
        List<User> users = List.of(new User("张三", 18, 1),
                new User("李四", 29, 0),
                new User("王五", 20, 1),
                new User("赵六", 20, 0),
                new User("田七", 20, 1),
                new User("孙八", 23, 0),
                new User("周九", 26, 1),
                new User("吴十", 25, 0));


        List<UserDto> userDtos = users.stream()
                .map(UserMapper.INSTANCE::userToUserDto)
                .toList();

        System.out.println(userDtos);
    }


}
