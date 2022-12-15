package com.example.demo;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Either<L, R> {

    /**
     * 代表异常情况
     */
    private L left;

    /**
     * 代表正常情况
     */
    private R right;

    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public void setRight(R right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    /**
     * 工厂方法-左值
     */
    public static <L, R> Either<L, R> left(L exception) {
        Either<L, R> e = new Either<>();
        e.left = exception;
        return e;
    }

    /**
     * 工厂方法-右值
     */
    public static <L, R> Either<L, R> right(R value) {
        Either<L, R> e = new Either<>();
        e.right = value;
        return e;
    }

    public <T> Either<L, T> map(Function<R, T> function) {
        if (isLeft()) {
            return left(left);
        } else {
            return right(function.apply(right));
        }
    }

    public static <L, R> Either<L, List<R>> sequence(List<Either<L, R>> eitherList, BinaryOperator<L> accumulator) {
        if (eitherList.stream().allMatch(Either::isRight)) {
            // 将所有数据存入
            return right(eitherList.stream().map(Either::getRight).collect(Collectors.toList()));
        } else {
            // 结合需求，确定是取第一个异常，还是取全部异常
            // 1. 取第一个异常
//            return left(eitherList.stream().filter(Either::isLeft).findFirst().orElseThrow().getLeft());
            // 2. 取全部异常
            return left(eitherList
                    .stream()
                    .filter(Either::isLeft)
                    .map(Either::getLeft)
                    .reduce(accumulator)
                    .orElseThrow()
            );
        }
    }
}
