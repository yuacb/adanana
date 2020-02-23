package com.adanana.blog.service.impl;

import com.adanana.blog.dao.CommentMapper;
import com.adanana.blog.model.Comment;
import com.adanana.blog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentDao;
    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public void insert(Comment comment) {
        commentDao.insert(comment);
    }
}
