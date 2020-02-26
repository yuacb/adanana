package com.adanana.blog.dao;

import com.adanana.spider.model.SpiderWeiboInfo;

import java.util.List;

public interface SpiderWeiboInfoMapper {
   void batchInsert(List<SpiderWeiboInfo> list);
}
