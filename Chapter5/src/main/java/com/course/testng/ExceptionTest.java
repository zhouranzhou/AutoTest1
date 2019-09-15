package com.course.testng;

import org.testng.annotations.Test;

public class ExceptionTest {
    @Test(expectedExceptions =RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是我的异常测试");
        throw new RuntimeException();
    }
}
