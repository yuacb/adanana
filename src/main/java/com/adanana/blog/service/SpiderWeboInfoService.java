package com.adanana.blog.service;

import com.adanana.spider.model.SpiderWeboInfo;

import java.util.List;

public interface SpiderWeboInfoService {
    void BatchInsert(List<SpiderWeboInfo> list);
}
