package com.adanana.blog.core.listener;

import com.adanana.spider.tools.CandidateQueue;
import com.adanana.spider.tools.WeiboURLTool;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class InitWeiboUserTarget implements org.springframework.context.ApplicationListener<ApplicationReadyEvent> {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
            System.out.println("==========================启动 加载用户==================================");
            if(CandidateQueue.size() == 0){
                //FIXME 这个 应该往数据库里面找；
                CandidateQueue.offer("3920678482");
                //这个存 已经查询的 用户
                redisTemplate.opsForSet().add("targetUser","3920678482");
                //这个存关系
                redisTemplate.opsForSet().add("targetUserRelationship","3920678482@3920678482");
            }

    }
}
