package com.adanana.blog.service;

import com.adanana.blog.model.BlogArticle;

import java.util.List;
import java.util.Map;

public interface  BlogAticleService {

    void insert(BlogArticle blogArticle);

    BlogArticle findById(String id);

    List<BlogArticle> find(Map<String,Object> params);
}
