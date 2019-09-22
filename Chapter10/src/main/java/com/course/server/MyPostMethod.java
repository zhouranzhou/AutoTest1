package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "这是我全部的post请求")
@RequestMapping("v1")
public class MyPostMethod {
    //cookie变量用来装我们的cookies信息
    private static Cookie cookie;

    //用户登陆成功获取到cookies，然后在访问其他接口获取到的列表，必须要求是post方法才可以访问
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登陆接口，成功后获取cookies信息", httpMethod = "POST")
    public String login(HttpServletResponse response,     //传入response这个参数是会在浏览器中看到cookie的属性
                        @RequestParam(value = "userName", required = true) String userName,  //true代表一定要传
                        @RequestParam(value = "password", required = true) String password) {

        if (userName.equals("zhangsan") && password.equals("123456")) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜你登陆成功！";
        }
        return "用户名或者密码错误！";
    }

    @RequestMapping(value = "/get/userList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    public String getUserList(HttpServletRequest request,     //没有HttpServletRequest request的话，cookies是带不进来的
                              @RequestBody User u) {
        User user;
        //获取cookies
        Cookie[] cookies = request.getCookies();

        //验证cookies是否合法
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login")
                    && cookie.getValue().equals("true")
                    && u.getUserName().equals("zhangsan")
                    && u.getPassword().equals("123456")) {
                user = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                return user.toString();  //在lombok框架里面自动写了toString方法，getName getValue set方法
            }
        }
        return "参数不合法";
    }
}
