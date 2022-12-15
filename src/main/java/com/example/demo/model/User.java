package com.example.demo.model;

public class User {

    private String id;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "年龄")
    private int age;

    /**
     * 性别 0-女 1-男
     */
    @Excel(name = "性别", dictValue = "0=女,1=男")
    private int gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    public User(String name, int age, int gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
