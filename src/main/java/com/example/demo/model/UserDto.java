package com.example.demo.model;

public class UserDto {

    /**
     * 姓名
     */
    private String username;

    /**
     * 年龄
     */
    private int age;

    /**
     * 姓名
     */
    private int sex;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getName());
        userDto.setAge(user.getAge());
        userDto.setSex(user.getGender());

        return userDto;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
