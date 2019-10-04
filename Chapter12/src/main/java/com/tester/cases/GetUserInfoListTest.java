package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.GetUserListCase;
import com.tester.model.User;
import com.tester.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetUserInfoListTest {

//比对接口返回的信息 和直接查询数据库返回的信息是否相同
//    查询数据库 我们定义了一个查询语句。
//    getUserInfoCase表，是自己创建的，包含测试数据，除了expected值外，都是入参。（json格式类型）。
//    数据库里面设置的expected为调用的查询语句的id


    @Test(dependsOnGroups="loginTrue",description = "获取性别为男的用户信息")
    public void getUserListInfo() throws IOException, InterruptedException {

        SqlSession session = DatabaseUtil.getSqlSession();
//        GetUserListCase getUserListCase = session.selectOne("getUserListCase",1);执行第一条用例，id为1
        GetUserListCase getUserListCase = session.selectOne("getUserListCase",2);//执行第二条用例，id为2。s代表string，o代表是object
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);





        //下边为写完接口的代码
        JSONArray resultJson = getJsonResult(getUserListCase);//接口返回的结果
        /**
         * 可以先讲
         */
        Thread.sleep(2000);
        List<User> userList = session.selectList(getUserListCase.getExpected(),getUserListCase);//直接查库返回的结果
        for(User u : userList){
            System.out.println("list获取的user:"+u.toString());
        }
        JSONArray userListJson = new JSONArray(userList);

        Assert.assertEquals(userListJson.length(),resultJson.length());//比较接口返回的结果和直接查库返回的结果长度是否相等
        for(int i = 0;i<resultJson.length();i++){
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(), actual.toString());//长度相等后，一条记录一条记录进行比较。
        }


    }

    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
//        HttpPost post = new HttpPost(TestConfig.getUserListUrl);//如果使用moco的话，用这个接口地址
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);//使用http的话，用这个接口地址
        JSONObject param = new JSONObject();
        param.put("userName",getUserListCase.getUserName());
        param.put("sex",getUserListCase.getSex());
        param.put("age",getUserListCase.getAge());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("调用接口infoList 返回的结果类型"+result.getClass());
        JSONArray jsonArray = new JSONArray(result);

        System.out.println("调用接口list result:"+result);
        System.out.println("调用接口list result转换为JsonArray:"+jsonArray);

        return jsonArray;

    }

}
