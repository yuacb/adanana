package com.adanana.dao;

import com.adanana.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    void insert(User user);
    User findById(Long id);
    User userLogin(User user);
}

 