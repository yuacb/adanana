package com.adanana.blog.dao;

import com.adanana.blog.model.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();
    void insert(User user);
    User findById(Long id);
    User userLogin(User user);
}

 