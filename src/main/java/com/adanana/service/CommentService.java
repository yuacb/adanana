package com.adanana.service;

import com.adanana.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    void insert(Comment comment);
}
