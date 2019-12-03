package com.adanana.service;

import com.adanana.model.User;

public interface UserService {
    void insert(User user);
    User findById(Long id);
    User userLogin(User user);
}
