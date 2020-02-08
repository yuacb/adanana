package com.adanana.controller;

import com.adanana.core.ResponseObject;
import com.adanana.model.BlogArticle;
import com.adanana.service.BlogAticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@CrossOrigin(value = "http://127.0.0.1:8081", maxAge = 1800, allowedHeaders ="*",allowCredentials="true")
@Controller
public class BlogAticleController extends BaseController {
    @Autowired
    private BlogAticleService blogAticleService;

    /**
     * 草稿
     */
    /**
     * 发布
     */
    @ResponseBody
    @RequestMapping(value ="blogArticle/release")
    public Object release (@RequestBody BlogArticle blogArticle){

        return ResponseObject.success(blogArticle);
    }
    /**
     * 删除
     */
    /**
     * 查询(列表)
     */
    /**
     * 查看内容
     */

}
