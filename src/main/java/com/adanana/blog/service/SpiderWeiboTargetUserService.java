package com.adanana.blog.service;

import com.adanana.spider.model.SpiderWeiboTargetUser;

import java.util.List;

public interface SpiderWeiboTargetUserService {
    void batchInsert(List<SpiderWeiboTargetUser> list);
}
