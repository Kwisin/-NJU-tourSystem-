package com.nju.tourSystem.controller;


import com.nju.tourSystem.entity.JsonResponse;
import com.nju.tourSystem.entity.Login;
import com.nju.tourSystem.entity.User;
import com.nju.tourSystem.service.UserService;
import io.swagger.annotations.Api;
//import net.sf.json.JSONObject;
//import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;



@Api("获取用户信息")
@RestController
@RequestMapping("/login/*")
public class LoginController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.POST)
    public ResponseEntity<JsonResponse> decodeUserInfo(String code, String appid, String appSecret) {
//        System.out.println(code);
//        System.out.println(appid);
//        System.out.println(appSecret);
        JsonResponse r = new JsonResponse();
        HttpURLConnection con = null;

        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;
        String urlParam = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code";
        System.out.println(urlParam);
        try {
            URL url = new URL(urlParam);
            //得到连接对象
            con = (HttpURLConnection) url.openConnection();
            //设置请求类型
            con.setRequestMethod("GET");
            //设置请求需要返回的数据类型和字符集类型
            con.setRequestProperty("Content-Type", "application/json;charset=GBK");
            //允许写出
            con.setDoOutput(true);
            //允许读入
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);
            //得到响应码
            int responseCode = con.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                //得到响应流
                InputStream inputStream = con.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
                while ((line = buffer.readLine()) != null) {
                    resultBuffer.append(line);
                }
                System.out.println(resultBuffer);
//                JSONObject jsonObject = JSONObject.fromObject(）
                int first = resultBuffer.indexOf(":");
                int second = resultBuffer.indexOf(",");
                int third = resultBuffer.indexOf(":",first+1);
                int forth = resultBuffer.indexOf("}");
                System.out.println(first);
                System.out.println(second);
                System.out.println(third);

                String s_key = resultBuffer.substring(first+2,second-1);
                String o_id = resultBuffer.substring(third+2,forth-1);
                System.out.println(s_key);
                System.out.println(o_id);

                //登录时存取用户信息到数据库
//                User user = new User();
//                user.setId(o_id);//用户Id
//                user.setUsername("黄勇");//用户昵称
//                user.setPortrait("111");//用户头像url
//                userService.addUser(user);

                Login login = new Login();
                login.setS_key(s_key);
                login.setO_id(o_id);
                r.setData(login);

                return ResponseEntity.ok(r);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(r);

    }


    @ResponseBody
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
    public void addUserInfo(String uid, String nickname , String url) {
        System.out.println("save");
        User user = new User();
        System.out.println("~~~~~~~~~");
        System.out.println(uid);
        user.setId(uid);//用户Id
        user.setUsername(nickname);//用户昵称
        user.setPortrait(url);//用户头像url
        userService.addUser(user);

    }


    }
