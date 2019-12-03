package com.adanana.service.impl;

import com.adanana.dao.CommentDao;
import com.adanana.model.Comment;
import com.adanana.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;
    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public void insert(Comment comment) {
        commentDao.insert(comment);
    }
}
