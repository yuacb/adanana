package com.adanana.controller;

import com.adanana.model.Comment;
import com.adanana.model.User;
import com.adanana.service.CommentService;
import com.adanana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

public class CommentController  extends  BaseController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value ="/comment", method = RequestMethod.POST)
    @ResponseBody
    public String register(Comment comment){
        comment.setCommentTime(new Date());
        commentService.insert(comment);
       return "";
    }

}
