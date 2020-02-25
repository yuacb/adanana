package com.adanana.blog.service.impl;

import com.adanana.blog.dao.SpiderWeboInfoMapper;
import com.adanana.blog.service.SpiderWeboInfoService;
import com.adanana.spider.model.SpiderWeboInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
@Component
public class SpiderWeboInfoServiceImpl implements SpiderWeboInfoService {
    @Resource
    private SpiderWeboInfoMapper spiderWeboInfoMapper;
    @Override
    public void BatchInsert(List<SpiderWeboInfo> list) {
        spiderWeboInfoMapper.BatchInsert(list);
    }
}
