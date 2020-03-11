package com.adanana.spider.configuration;

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

        BlockingQueue workQueue = new ArrayBlockingQueue<Runnable>(6);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor weiboSpiderTreadPool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.MINUTES,workQueue, handler);
        return weiboSpiderTreadPool;
    }
}
