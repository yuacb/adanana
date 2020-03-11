package com.adanana.blog.controller;

import com.adanana.blog.core.ResponseObject;
import com.adanana.blog.model.User;
import com.adanana.blog.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
 //首页Controller 登陆 & 注册
// @RestController json 返回
@Controller
public class IndexController extends  BaseController {
     @Resource
     private RedisTemplate redisTemplate;
     @Resource
     private UserService userService;

    /**
     * 注册
     */
     @RequestMapping(value ="/register", method = RequestMethod.POST)
     @ResponseBody
     public Object register(@RequestBody User user){
         user.setRegisteredTime(new Date());
         userService.insert(user);
         //存入redis
         return ResponseObject.success() ;
     }
     /**
      * 登陆
      */
     @ResponseBody
     @RequestMapping(value = "/login",method = RequestMethod.POST)
     public Object login(HttpServletRequest request,@RequestBody User loginUser){
         HttpSession httpSession = request.getSession(true);
         ValueOperations<String, User> operations=redisTemplate.opsForValue();
         User user = userService.userLogin(loginUser);
         if(!(null == user))
         {   operations.set("sessionId@"+httpSession.getId(),user);
             return  ResponseObject.success(user);
         }else{
             return  ResponseObject.fail("未注册");
         }
     }

     /**
      * 留言
      */
     @ResponseBody
     @RequestMapping(value = "/comment",method = RequestMethod.POST)
     public String comment(){
         return "index.html";
     }


}

 