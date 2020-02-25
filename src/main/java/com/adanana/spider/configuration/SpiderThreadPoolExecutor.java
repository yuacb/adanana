package com.adanana.spider.configuration;

import com.adanana.spider.tools.CandidateQueue;
import com.adanana.spider.tools.WeiboURLTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池
 */
@Configuration
public class SpiderThreadPoolExecutor {
    @Bean
    public ThreadPoolExecutor weiboSpiderTreadPool (){
        int corePoolSize = 1;
        int maximumPoolSize = 3;
        long keepAliveTime = 30;

        CandidateQueue.offer("3920678482");
        // p c
        WeiboURLTool.mapAdd("3920678482","ROOT");
        BlockingQueue workQueue = new ArrayBlockingQueue<Runnable>(6);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor weiboSpiderTreadPool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.MINUTES,workQueue, handler);
        return weiboSpiderTreadPool;
    }
}
