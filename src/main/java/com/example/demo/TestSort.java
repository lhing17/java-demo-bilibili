package com.example.demo;

import com.example.demo.model.User;

import java.util.*;
import java.util.stream.Stream;

public class TestSort {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>(List.of(new User("张三", 18, 1),
                new User("李四", 29, 0),
                new User("王五", 20, 1),
                new User("赵六", 20, 0),
                new User("田七", 20, 1),
                new User("孙八", 23, 0),
                new User("周九", 26, 1),
                new User("吴十", 25, 0)));

        // 1. Java怎么支持数据排序？Comparable和Comparator接口
        List<Integer> list = new ArrayList<>(List.of(2, 3, 4, 6, 5, 1));
        Collections.sort(list);
        System.out.println(list);

        // 2. 怎么构造Comparator
        // 针对一些内置类，Java已经提供一些方法可以用于构造
        Comparator<Integer> compare = Integer::compare;
        Comparator<CharSequence> compare1 = CharSequence::compare;

        // 3. 按字段比较 按用户年龄排序
        Comparator<User> userComparator = Comparator.comparing(User::getAge);
        users.sort(userComparator);
        System.out.println(users);

        // 4. 按多个字段比较 先按年龄、再按性别排序
        Comparator<User> userComparator1 = Comparator.comparing(User::getAge).thenComparing(User::getGender);
        users.sort(userComparator1);

        // 5. 倒序 按年龄倒序
        Comparator<User> userComparator2 = Comparator.comparing(User::getAge).reversed();
        users.sort(userComparator2);
        System.out.println(users);

        // 6. 排序API
        // 数据存在数组里
        User[] array = users.stream().toArray(User[]::new);
        Arrays.sort(array, userComparator);

        // 数据存在List集合里
        Collections.sort(users, userComparator);

        // 不改变原始的集合，进行排序
        Stream.of(array).sorted(userComparator);
        users.stream().sorted(userComparator);
    }
}
