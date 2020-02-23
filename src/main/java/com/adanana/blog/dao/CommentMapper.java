package com.adanana.blog.dao;

import com.adanana.blog.model.Comment;

import java.util.List;

public interface CommentMapper {
    List<Comment> findAll();
    void insert(Comment comment);
}
