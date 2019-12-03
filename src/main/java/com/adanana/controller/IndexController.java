package com.adanana.controller;

import com.adanana.model.ShStockeXchange;
import com.adanana.model.User;
import com.adanana.service.ShStockeXchangeService;
import com.adanana.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 //首页Controller 登陆 & 注册
// @RestController json 返回
@Controller
public class IndexController extends  BaseController {
     @Autowired
     private StringRedisTemplate stringRedisTemplate;
     @Autowired
     private RedisTemplate redisTemplate;

    @Autowired
    private ShStockeXchangeService shStockeXchangeService;
    @Autowired
    private UserService userService;
    @RequestMapping("/index")
    public void index(){
        System.out.println("1");
    }
    /**
     * 注册
     */
     @RequestMapping(value ="/register", method = RequestMethod.POST)
     @ResponseBody
     public String register(User user){
         user.setRegisteredTime(new Date());
         userService.insert(user);
         //存入redis
         ValueOperations<String, User> operations=redisTemplate.opsForValue();
         operations.set("register_USER"+System.currentTimeMillis(),user);
         return JSON.toJSONString("success") ;
     }

     /**
      * 登陆
      */
     @ResponseBody
     @RequestMapping(value = "/login",method = RequestMethod.POST)
     public Object login(User loginUser){
         User user = userService.userLogin(loginUser);
         return user;
     }

     /**
      * 留言
      */
     @ResponseBody
     @RequestMapping(value = "/comment",method = RequestMethod.POST)
     public String comment(){
         return "index.html";
     }




    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        List<ShStockeXchange> shStockeXchangeList = new ArrayList<ShStockeXchange>();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader  in = new BufferedReader(new InputStreamReader(file.getInputStream(),"gb2312"));
            while ((in.readLine()) != null)
            {
                String[] values = in.readLine().replaceAll("\\[", "").split(",");
                shStockeXchangeList.add(shStockeXchangeService.getBeanField(values));
            }
            in.close();
         } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
        //存到数据库

        return "index.html";
    }
}

 