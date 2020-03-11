package com.adanana.blog.controller;

import com.adanana.blog.core.Constant;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpiderController extends BaseController {
    private Object loockObject;
    /**
     * 注册
     * SPIDER_SWITCH
     * on off
     */
    @RequestMapping(value ="/spider/switch", method = RequestMethod.GET)
    @ResponseBody
    public Object spiderSwitch(String switchValue){
        synchronized(loockObject){
            Constant.SPIDER_SWITCH = switchValue;
        }
        //存入redis
        return JSON.toJSONString("success") ;
    }
 }
