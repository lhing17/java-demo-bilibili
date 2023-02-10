package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.model.User2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
//        int a;
//        System.out.println(a = 1);
//        List<User> users = List.of(
//                new User("张三", 18, 1),
//                new User("李四", 19, 0),
//                new User("王五", 20, 1),
//                new User("赵六", 21, 0),
//                new User("田七", 22, 1),
//                new User("孙八", 23, 0),
//                new User("周九", 24, 1),
//                new User("吴十", 25, 0));
//
//        // 根据性别分组
//        // 0-女 1-男
//        // 流的创建、数据在流中进行转换、数据的聚合
//        Map<Integer, List<User>> groups = users.stream().collect(Collectors.groupingBy(User::getGender));
////        Map<Integer, List<User>> groups = users.stream().collect(Collectors.groupingBy(u -> u.getGender()));
//
//        // 根据年龄段分组 18-20 21-23 24-26
//        Map<String, List<User>> groups2 = users.stream().collect(Collectors.groupingBy(user -> {
//            int age = user.getAge();
//            if (age >= 18 && age <= 20) {
//                return "18-20";
//            } else if (age >= 21 && age <= 23) {
//                return "21-23";
//            } else if (age >= 24 && age <= 26) {
//                return "24-26";
//            } else {
//                return "其他";
//            }
//        }));
//
//        // 统计每种性别的人数
//        Map<Integer, Long> count = users.stream().collect(Collectors.groupingBy(User::getGender, Collectors.counting()));
//
//        // reduce大法 初始值 每一个数据怎么给合并到初始值中 怎么把两个结果集数据合并到一块
//        HashMap<Integer, Long> reduce = users.stream().reduce(new HashMap<Integer, Long>(), (m, u) -> {
//            m.put(u.getGender(), m.getOrDefault(u.getGender(), 0L) + 1);
//            return m;
//        }, (m1, m2) -> {
//            m1.putAll(m2);
//            return m1;
//        });
//
//
//        Stream.generate(() -> 1).limit(5).forEach(System.out::println);

//        LinkedHashMap<Integer, Integer> map = Stream.iterate(1, i -> i + 1)
//                .parallel()
//                .limit(5)
//                .map(i -> i * 5)
//                .filter(i -> i % 2 == 0)
//                .collect(Collectors.toMap(i -> i, i -> i * 2, (v1, v2) -> v2, LinkedHashMap::new));
//
//
//        System.out.println(map);

        User user = new User("李四", 29, 0);

        List<User2> user2s = Stream.of(user).map(User2::fromUser).collect(Collectors.toList());
        System.out.println(user2s);


    }


    public static <T> void test(T[] value) {
        System.out.println(value);
    }

}
