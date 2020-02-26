package com.adanana.blog.core.scheduledTask;

import com.adanana.blog.service.SpiderWeboInfoService;
import com.adanana.spider.model.SpiderWeboInfo;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ScheduledSaveWeiboData {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private SpiderWeboInfoService spiderWeboInfoService;
    private static  final int MAX =200;
    @Scheduled(cron="0/10 * *  * * ? ")   //每10
    public void task(){
        System.out.println("执行了 数据库的 定时");
        ListOperations<String, SpiderWeboInfo> listOperations = redisTemplate.opsForList();
        Long spiderWeboInfoListSize = listOperations.size("spiderWeboInfoList");
        System.out.println("当前数量"+spiderWeboInfoListSize);
        if(Long.valueOf(MAX).compareTo(spiderWeboInfoListSize) >0)
        {
            return;
        }
        //超过 阀值 就都存一下
        List<SpiderWeboInfo> spiderWeboInfoLists = listOperations.range("spiderWeboInfoList",0,-1);
        //原来的删掉
        redisTemplate.delete("spiderWeboInfoList");
        System.out.println("保存数据 ："+spiderWeboInfoLists.size()+"条");
        spiderWeboInfoService.BatchInsert(spiderWeboInfoLists);
    }
}
