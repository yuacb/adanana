package com.adanana.dao;

import com.adanana.model.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> findAll();
    void insert(Comment comment);
}
