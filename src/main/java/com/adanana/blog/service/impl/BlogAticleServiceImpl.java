package com.adanana.blog.service.impl;

import com.adanana.blog.dao.BlogArticleMapper;
import com.adanana.blog.model.BlogArticle;
import com.adanana.blog.service.BlogAticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "blogAticleService")
public class BlogAticleServiceImpl implements BlogAticleService {
    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Override
    public void insert(BlogArticle blogArticle) {
        blogArticleMapper.insert(blogArticle);
    }

    @Override
    public BlogArticle findById(String id) {
        return blogArticleMapper.findById(id);
    }

    @Override
    public List<BlogArticle> find(Map<String, Object> params) {
        return blogArticleMapper.find(params);
    }

}
