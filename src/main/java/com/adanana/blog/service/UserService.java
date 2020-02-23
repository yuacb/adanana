package com.adanana.blog.service;

import com.adanana.blog.model.User;

public interface UserService {
    void insert(User user);
    User findById(Long id);
    User userLogin(User user);
}
