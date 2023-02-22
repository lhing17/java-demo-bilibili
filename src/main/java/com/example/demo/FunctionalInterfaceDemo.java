package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FunctionalInterfaceDemo {

    public static void main(String[] args) {

        // 函数式接口

        // 1. 无参无返回值
        Runnable runnable = () -> System.out.println("hello world");


        // 2. 有参无返回值
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello world");

        // 3. 有参有返回值
        Function<String, Integer> function = String::length;
        System.out.println(function.apply("hello world"));

        // 4. 无参有返回值
        Supplier<String> supplier = () -> "hello world";
        System.out.println(supplier.get());

        // 5. 有参有返回值，返回值是boolean类型
        Predicate<String> predicate = s -> true;
        System.out.println(predicate.test("hello world"));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 6, 5, 7, 8, 9);

        Stream.of(1, 2, 3, 4, 5)
                .filter(i -> i % 2 == 0)
                .map(String::valueOf)
                .forEach(System.out::println);



    }


}
