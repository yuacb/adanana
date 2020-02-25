package com.adanana.spider.tools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 微博 的候选人队列
 */
public class CandidateQueue  {
    private static BlockingQueue<String> candidateQueue = new LinkedBlockingQueue<String>();

    public static Boolean contains(String value){
       return candidateQueue.contains(value);
    }
    public static int size(){
        return candidateQueue.size();
    }
    public static String poll(Long timeout, TimeUnit unit)  {
        try {
            return candidateQueue.poll(timeout,unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Boolean offer(String e) {
        try {
            return candidateQueue.offer(e,5,TimeUnit.MINUTES);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
