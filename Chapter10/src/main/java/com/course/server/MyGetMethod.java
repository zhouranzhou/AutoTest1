package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController    //被告诉我是你需要扫描的类
@Api(value = "/",description = "这是我全部的get方法")

public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)    //访问的路径是什么，用到什么样的方法
    @ApiOperation(value = "通过这个方法可以获取到cookies的值",httpMethod ="GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest    装请求信息
        //HttpServerletResponse   装响应信息
        Cookie cookie = new Cookie("login","ture");  //定义一个cookies信息
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }


    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();  //从请求中获取cookies信息
        if(Objects.isNull(cookies)){
            return "你必须携带cookies信息来";
        }

        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login") && cookie.getName().equals("true")){
                return "恭喜你访问成功";
            }
        }
        return "你必须携带cookies信息来";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求
     * 第一种实现方式是 url： ip:port/get/with/param?key=value&key=value
     * 模拟获取商品列表  开始页数，结束的页数，一页20条数据
     * */

    //第一种需要携带参数访问的get请求，将参数定义在方法传参位置处，用@RequestParam关键字，在浏览器地址栏中传入
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)  //请求的url
    @ApiOperation(value = "携带参数才能访问的get请求",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("衬衫",300);
        myList.put("干脆面",1);
        return myList;

    }

    /**
     *第2种需要携带参数访问的get请求，用到的是@PathVariable 关键字，因为是穿的路径
     * url：  ip:port/get/with/param/10/20
     * */

    @RequestMapping(value = "/get/with/param/{start}/{end}")  //另一种请求url
    @ApiOperation(value = "第2种需要携带参数访问的get请求",httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end){

        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("衬衫",300);
        myList.put("干脆面",1);
        return myList;
    }
}