package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


// 配置优化就是为了使代码看起来更简便，如果代码里面的每一个请求都写一次url，那么整体代码看起来很乱，而且一旦某个服务器的端口号或者域名有变动，那么所有的url都需要改变，成本太大。为了让代码看起来更简便，修改起来更容易，所以要用配置文件去写url。如果想切换测试环境，代码只需要变动一行就可以。
// 获取cookie的信息,然后带cookies去发送请求
public class MyCookiesForGet {

    private String url;
    //这个工具类就是为了读取properties这样的配置文件的
    private ResourceBundle bundle;

    //用来存储cookies信息的变量
    private CookieStore store;


    @BeforeTest
    public void beforeTest(){
        //获取的文件里面的内容，只需要写文件名字就行，不需要写后缀
        bundle = ResourceBundle.getBundle("application",Locale.CHINA);
        //获取到属性 test.url，获取到这个属性以后，配置文件中的test.url自动变成了非灰色
        url = bundle.getString("test.url");
    }


    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        HttpGet get = new HttpGet(this.url + uri);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);

        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies的信息，因为cookie里面不只是一个，他是一个cookie类型的list
        store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();

        for(Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name = "+name+",value = "+value);
        }
    }

    @Test(dependsOnMethods = "testGetCookies")
    public void testGetWithCookies() throws IOException {
        //从配置文件中拼接测试的url
        String uri = bundle.getString("test.get.with.cookies");
        String testUrl=this.url+uri;
        //测试逻辑代码编写
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();

        //设置cookies信息
        client.setCookieStore(store);

        HttpResponse response = client.execute(get);

        //获取响应的状态码
        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("statusCode="+statusCode);

        if(statusCode==200){
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }
    }

}