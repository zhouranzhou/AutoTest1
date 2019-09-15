package com.course.testng.Group;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethods {
    @Test(groups = "server")
    public  void  ServerTest1(){
        System.out.println("这是服务端测试Test1");
    }

    @Test(groups = "server")
    public  void  ServerTest2(){
        System.out.println("这是服务端测试Test2");
    }

    @Test(groups = "client")
    public  void  ClientTest1(){
        System.out.println("这是客户端测试Test1");
    }

    @Test(groups = "client")
    public  void  ClientTest2(){
        System.out.println("这是客户端测试Test2");
    }


    @BeforeGroups(groups = "server")
    public void TestBeforeServerGroups(){
        System.out.println("这是服务端组测试方法运行之前执行的TestBeforeServerGroups");
    }

    @BeforeGroups(groups = "client")
    public void TestBeforeClientGroups(){
        System.out.println("这是客户端组测试方法运行之前执行的TestBeforeClientGroups");
    }


}
