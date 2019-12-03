package com.adanana.service.impl;

import com.adanana.dao.UserDao;
import com.adanana.model.User;
import com.adanana.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

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
