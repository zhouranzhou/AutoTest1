package com.course.testng.Group;

import org.testng.annotations.Test;

@Test(groups ="teacher")
public class GroupOnClass3 {
    public void stu1(){
        System.out.println("运行teacher组的stu1方法");
    }
    public void stu2(){
        System.out.println("运行teacher组的stu2方法");
    }
}
