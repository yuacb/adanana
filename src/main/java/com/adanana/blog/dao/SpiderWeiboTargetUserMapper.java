package com.adanana.blog.dao;

import com.adanana.spider.model.SpiderWeiboTargetUser;

import java.util.List;

public interface SpiderWeiboTargetUserMapper {
    void batchInsert(List<SpiderWeiboTargetUser> list);
}
