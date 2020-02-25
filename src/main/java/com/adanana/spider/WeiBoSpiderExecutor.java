package com.adanana.spider;

import com.adanana.spider.tools.CandidateQueue;
import com.adanana.spider.tools.WeiboURLTool;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * 从某一用户开始爬取
 * 遍历他的微博内容
 * 同时获取他 微博下的所有 评论用户
 * 重复以上动作
 *
 * 改了下 这里算是启动
 */
@Component
public class WeiBoSpiderExecutor  {
    @Resource
    private ThreadPoolExecutor weiboSpiderTreadPool;

    public void executorSpiderThread() throws InterruptedException {
        WeiBoSpiderThread weiBoSpiderThread =new WeiBoSpiderThread();
//        int corePoolSize = 1;
//        int maximumPoolSize = 3;
//        long keepAliveTime = 30;

        CandidateQueue.offer("3920678482");
        // p c
        WeiboURLTool.mapAdd("3920678482","ROOT");
        //递交第一个线程
        weiboSpiderTreadPool.submit(weiBoSpiderThread);

//        BlockingQueue workQueue = new ArrayBlockingQueue<Runnable>(6);
//        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
//        ThreadPoolExecutor weiboSpiderTreadPool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit.MINUTES,workQueue, handler);
//        while(true){
//            weiboSpiderTreadPool.submit(weiBoSpiderThread);
//            Thread.sleep(12000L);
//        }
    }


}
