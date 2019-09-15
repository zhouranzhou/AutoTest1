package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
    //最基本的注解 表示方法是测试的一部分
    @Test
    public void testCases1(){
        System.out.println("Test这是测试用例1");
    }
    //beforeMethod 在执行测试用例之前运行
    @BeforeMethod
    public void BeforeMethod(){
        System.out.println("BeforeMethod这是测试用例之前运行的方法");
    }

    //AfterMethod 在执行测试方法之后运行
    @AfterMethod
    public void AfterMethod(){
        System.out.println("AfterMethod这是执行完测试用例之后运行的方法");
    }
    @BeforeClass
    public void BeforeClassMethod(){
        System.out.println("BeforeClass 这是在类之前运行的方法");
    }
    @AfterClass
    public void AfterClassMethod(){
        System.out.println("AfterClass 这是在类之后运行的方法");
    }

    @BeforeSuite
    public void BeforeSuite(){
        System.out.println("BeforeSuite 测试套件");
    }
    @AfterSuite
    public void AfterSuite(){
        System.out.println("AfterSuite 测试套件");
    }

}
