package com.course.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

//DataProvider与Test方法在同一个class中
public class DataProvideTest {
    @Test(dataProvider = "DataProvider")
    public void DataProviderShow(String data1, String data2) {
        System.out.println("result:" + data1 + data2);
    }

    // DataProvide 作为数据提供者，提供几组数组，则引用他的test方法就会执行几次
    @DataProvider(name = "DataProvider")
    public Object[][] DataProviders() {
        //二维数组 object类型
        Object[][] obj = new Object[][]{{"A", "A1"}, {"B", "B1"}, {"C", "C1"}};
        return obj;
    }
    @Test(dataProvider = "MethodTest")
    public void test1(String data1, int data2) {
        System.out.println("result:" + data1 + data2);
    }

    @Test(dataProvider = "MethodTest")
    public void test2(String data1, int data2) {
        System.out.println("result:" + data1 + data2);
    }

    @DataProvider(name = "MethodTest")
    public Object[][] MethodDataTest(Method method) {
        Object[][] result=null;
        if (method.getName().equals("test1")){
            result=new Object[][]{{"zhangsan",10},{"lisi",20}};
        } else
        {
            if (method.getName().equals("test2"))
            {
            result=new Object[][]{{"wangwu",30},{"zhapliu",50}};
            }
        }
        return result;
    }
}
