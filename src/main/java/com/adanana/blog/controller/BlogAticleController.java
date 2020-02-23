package com.adanana.blog.controller;

import com.adanana.blog.core.GlobalBean;
import com.adanana.blog.core.ResponseObject;
import com.adanana.blog.model.BlogArticle;
import com.adanana.blog.service.BlogAticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@CrossOrigin(value = "http://127.0.0.1:8081", maxAge = 1800, allowedHeaders ="*",allowCredentials="true")
@Controller
public class BlogAticleController extends BaseController {
    @Autowired
    private BlogAticleService blogAticleService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询(列表)
     */
    @ResponseBody
    @RequestMapping(value ="blogArticle/find")
    public Object find(@RequestBody Map<String,Object> params){
        return ResponseObject.success(blogAticleService.find(params));
    }

    /**
     * 查看内容
     */
    @ResponseBody
    @RequestMapping(value ="blogArticle/findById")
    public Object find(@RequestParam String id){
        System.out.println("入参"+id);
        return ResponseObject.success(blogAticleService.findById(id));
    }
    /**
     * 草稿
     */
    /**
     * 发布
     */
    @ResponseBody
    @RequestMapping(value ="blogArticle/release")
    public Object release (@RequestBody BlogArticle blogArticle){
        System.out.println(GlobalBean.GLOBAL_USER().getId());
        blogArticle.setCreateUserId(GlobalBean.GLOBAL_USER().getId());
        blogArticle.setCreateUserName(GlobalBean.GLOBAL_USER().getUserNickname());
        blogArticle.setCreateTime(new Date());
        //保存
        blogAticleService.insert(blogArticle);
        return ResponseObject.success();
    }
    /**
     * 删除
     */


}
