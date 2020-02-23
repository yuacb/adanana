package com.adanana.blog.service;

import com.adanana.blog.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    void insert(Comment comment);
}
