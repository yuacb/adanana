package com.adanana.blog.core.scheduledTask;

import com.adanana.blog.core.Constant;
import com.adanana.blog.service.SpiderWeiboTargetUserService;
import com.adanana.spider.model.SpiderWeiboTargetUser;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ScheduledSaveTargetUser {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private SpiderWeiboTargetUserService spiderWeiboTargetUserService;

    private static final int MAX =1;
    @Scheduled(cron="0/50 * *  * * ? ")   //每10
    public void task() throws NoSuchFieldException, IllegalAccessException {
        if(Constant.SPIDER_SWITCH.equals(Constant.SPIDER_OFF))
        {
            return;
        }
        ListOperations listOperations = redisTemplate.opsForList();
        if( listOperations.size("completeUserList")<MAX){
            return;
        }
        Class<SpiderWeiboTargetUser> spiderWeiboTargetUserClass = SpiderWeiboTargetUser.class;
        List<SpiderWeiboTargetUser> list = new ArrayList<>();
        SpiderWeiboTargetUser spiderWeiboTargetUser = null;
        //取出所有用户
        List<Map<String,String>> objectMapList = listOperations.range("completeUserList",0,-1);
        for(Map<String,String> objectMap :objectMapList)
        {
            spiderWeiboTargetUser = new SpiderWeiboTargetUser();
            //构造对象
            for(Map.Entry<String,String>  object :objectMap.entrySet()){
                Field field = spiderWeiboTargetUserClass.getDeclaredField(object.getKey());
                field.setAccessible(true);
                if("status".equals(object.getKey()))
                {
                    field.set(spiderWeiboTargetUser,Integer.valueOf(object.getValue()));
                    continue;
                }
                field.set(spiderWeiboTargetUser,object.getValue());
            }
            if(null!=spiderWeiboTargetUser)
            {
                list.add(spiderWeiboTargetUser);
            }
        }
        spiderWeiboTargetUserService.batchInsert(list);
        //原来的删掉
        redisTemplate.delete("completeUserList");
    }

}

