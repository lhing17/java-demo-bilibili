package com.example.demo;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import javax.annotation.concurrent.Immutable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestImmutableCollection {

    public static void main(String[] args) {

        // 1. 使用不可变集合的场景
        // 1) 线程安全
        // 2) 防御式编程（有不信任的代码时）
        // 3) 函数式编程

        // 2. 构造不可变集合
        List<Integer> list = List.of(1, 2, 3, 4);
//        list.add(5);
        System.out.println(list);

        List<Integer> integers = List.copyOf(list);
        System.out.println(integers);

        // 3. 不可变集合和可变集合的相互转换
        List<Integer> list1 = new ArrayList<>(list);
        list1.add(5);
        System.out.println(list1);

        List<Integer> list2 = Collections.unmodifiableList(list1);
        list2.add(6);
        System.out.println(list2);
    }
}
