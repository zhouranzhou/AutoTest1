package com.course.bean;

import lombok.Data;

@Data   //注解，作用就是get set  toString等方法都不用写了
public class User {

    private String userName;
    private String password;
    private String name;
    private String age;
    private String sex;
}