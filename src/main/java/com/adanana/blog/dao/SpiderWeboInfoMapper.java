package com.adanana.blog.dao;

import com.adanana.spider.model.SpiderWeboInfo;

import java.util.List;

public interface SpiderWeboInfoMapper {
   void BatchInsert(List<SpiderWeboInfo> list);
}
