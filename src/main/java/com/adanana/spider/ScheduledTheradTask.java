package com.adanana.spider;

import com.adanana.blog.core.Constant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 这里定时任务
 * 去 取队列里的 提交到 线程
 */
@Component
public class ScheduledTheradTask {
    @Resource
    private ThreadPoolExecutor weiboSpiderTreadPool;
    @Resource
    private WeiBoSpiderThread weiBoSpiderThread;
    @Scheduled(cron="0/10 * *  * * ? ")   //每10
    public void task(){
        if(Constant.SPIDER_SWITCH.equals(Constant.SPIDER_OFF))
        {
            return;
        }
        System.out.println("定时 提交线程,当前队列大小："+weiboSpiderTreadPool.getQueue().size());
        weiboSpiderTreadPool.submit(weiBoSpiderThread);
    }

}
