package com.example.demo;

import com.example.demo.model.User;

import java.util.Optional;

public class TestOptional {

    public static void main(String[] args) {
        // 1. 将可能为空的对象转换为Optional对象
        User user = new User("张三", 20, 1);
        Optional<User> optionalUser = Optional.ofNullable(user);
        if (user != null) {
            int age = user.getAge();
        }


        // 2. Optional链式操作
        Integer age = optionalUser.map(User::getAge).orElse(30);
        System.out.println(age);

        // 3. Optional短路
        // 从数据库中查出某个用户，如果不存在则向数据库中新建用户并返回
//        User user1 = optionalUser.orElse(newUser()); // 错误
//        User user1 = optionalUser.orElseGet(() -> newUser());
        // 4. Optional抛出异常
        optionalUser.orElseThrow(()-> new RuntimeException("没有用户"));

        optionalUser.map(User::getAge)
                .filter(a -> a < 30)
                .ifPresent(a -> System.out.println(a));
    }
}
