package com.adanana.blog.service.impl;

import com.adanana.blog.dao.UserMapper;
import com.adanana.blog.model.User;
import com.adanana.blog.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userDao;

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User userLogin(User user) {
        return userDao.userLogin(user);
    }
}
