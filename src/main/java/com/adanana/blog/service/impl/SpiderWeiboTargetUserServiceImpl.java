package com.adanana.blog.service.impl;

import com.adanana.blog.dao.SpiderWeiboTargetUserMapper;
import com.adanana.blog.service.SpiderWeiboTargetUserService;
import com.adanana.spider.model.SpiderWeiboTargetUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SpiderWeiboTargetUserServiceImpl implements SpiderWeiboTargetUserService {
    @Resource
    private SpiderWeiboTargetUserMapper spiderWeiboTargetUserMapper;
    @Override
    public void batchInsert(List<SpiderWeiboTargetUser> list) {
        spiderWeiboTargetUserMapper.batchInsert(list);
    }
}
