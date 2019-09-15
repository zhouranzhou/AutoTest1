package com.course.testng.Group;

import org.testng.annotations.Test;

@Test(groups ="stu")
public class GroupOnClass1 {
    public void stu1(){
        System.out.println("运行stu组的stu1方法");
    }
    public void stu2(){
        System.out.println("运行stu组的stu2方法");
    }
}
