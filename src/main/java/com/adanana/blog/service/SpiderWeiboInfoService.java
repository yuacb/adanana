package com.adanana.blog.service;

import com.adanana.spider.model.SpiderWeiboInfo;

import java.util.List;

public interface SpiderWeiboInfoService {
    void batchInsert(List<SpiderWeiboInfo> list);
}
