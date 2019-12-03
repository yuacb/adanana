package com.adanana.service;

import com.adanana.model.User;
import com.adanana.model.ShStockeXchange;

import java.util.List;

public interface ShStockeXchangeService {
    List<User> findAll();
    ShStockeXchange getBeanField(String[] values);

}

 