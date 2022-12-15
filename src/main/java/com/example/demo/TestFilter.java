package com.example.demo;

import com.example.demo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestFilter {


    public static void main(String[] args) {
        List<User> users = new ArrayList<>(List.of(new User("张三", 18, 1),
                new User("李四", 29, 0),
                new User("王五", 20, 1),
                new User("赵六", 20, 0),
                new User("田七", 20, 1),
                new User("孙八", 23, 0),
                new User("周九", 26, 1),
                new User("吴十", 25, 0)));

        // 1. 按条件过滤 取出所有年龄在25岁以下的用户
        List<User> userList = users.stream().filter(u -> u.getAge() < 25).collect(Collectors.toList());

        // 2. 保留前几项/跳过前几项 保留前5项
        users.stream().skip(5).limit(5);

        // 3. 寻找符合条件的第一项
        Optional<User> user = users.stream().filter(u -> u.getName().startsWith("孙")).findFirst();
        User user1 = user.orElse(null);

        // 4. 判断是否有符合的数据/是否都符合/是否都不符合 相当于js里的some、every
        boolean b = users.stream().allMatch(u -> u.getAge() < 30);
        boolean b1 = users.stream().anyMatch(u -> u.getAge() < 25);
        boolean b2 = users.stream().noneMatch(u -> u.getAge() < 20);

        // 5. 保留第一个不合法数据前面的所有数据 JDK9以上可以使用
        List<User> users1 = users.stream().takeWhile(u -> u.getAge() < 30).collect(Collectors.toList());

        List<User> users2 = users.stream().dropWhile(u -> u.getAge() < 30).collect(Collectors.toList());

    }
}
