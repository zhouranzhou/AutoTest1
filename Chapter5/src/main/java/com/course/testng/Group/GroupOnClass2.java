package com.course.testng.Group;

import org.testng.annotations.Test;

@Test(groups ="stu")
public class GroupOnClass2 {
    public void stu3(){
        System.out.println("运行stu组的stu3方法");
    }
    public void stu4(){
        System.out.println("运行stu组的stu4方法");
    }
}
