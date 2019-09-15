package com.course.testng;

import org.testng.annotations.Test;

//依赖测试
public class DependTest {
    @Test
    public void test1(){
        System.out.println("test1 run");
        throw new RuntimeException();
    }

    //依赖测试：test2方法依赖test1。
    // test2执行之前先执行test1.如果test1执行通过了，则执行test2
    //如果test1执行不通过（也就是抛出了异常），那么test2就不被执行了。
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("test2 run");
    }
}
