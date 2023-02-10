package com.example.demo.model;

public class User2 {

    private String name;
    private int age;

    public static User2 fromUser(User user){
        User2 user2 = new User2();
        user2.setName(user.getName());
        user2.setAge(user.getAge());
        return user2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
