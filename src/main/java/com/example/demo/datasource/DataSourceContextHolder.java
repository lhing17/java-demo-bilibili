package com.example.demo.datasource;

public class DataSourceContextHolder {

    public static ThreadLocal<String> key = new ThreadLocal<>();

    public static void setKey(String key) {
        DataSourceContextHolder.key.set(key);
    }

    public static String getKey() {
        return key.get();
    }

    public static void clearKey() {
        key.remove();
    }
}
