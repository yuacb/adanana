package com.adanana.blog.service.impl;

import com.adanana.blog.dao.SpiderWeiboInfoMapper;
import com.adanana.blog.service.SpiderWeiboInfoService;
import com.adanana.spider.model.SpiderWeiboInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
@Component
public class SpiderWeiboInfoServiceImpl implements SpiderWeiboInfoService {
    @Resource
    private SpiderWeiboInfoMapper spiderWeiboInfoMapper;
    @Override
    public void batchInsert(List<SpiderWeiboInfo> list) {
        spiderWeiboInfoMapper.batchInsert(list);
    }
}
