package com.adanana.service.impl;

import com.adanana.model.User;
import com.adanana.service.ShStockeXchangeService;
import com.adanana.dao.UserDao;
import com.adanana.model.ShStockeXchange;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service(value = "shStockeXchangeService")
public class ShStockeXchangeServiceImpl implements ShStockeXchangeService {
    @Resource
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    public ShStockeXchange getBeanField(String[] values) {
        Class<ShStockeXchange> shStockeXchangeClass = null;
        ShStockeXchange shStockeXchangeObject = null;
        try {
            shStockeXchangeClass = (Class<ShStockeXchange>) Class.forName("com.adanana.demo.model.ShStockeXchange");
            shStockeXchangeObject = shStockeXchangeClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field[] fields = shStockeXchangeClass.getDeclaredFields();
        try {
            fields[1].setAccessible(true);
            fields[1].set(shStockeXchangeObject,values[0]);
            fields[2].setAccessible(true);
            fields[2].set(shStockeXchangeObject,values[1]);
            fields[3].setAccessible(true);
            fields[3].set(shStockeXchangeObject, new Date());
            for (int i = 4; i < fields.length-1; i++) {
                fields[i].setAccessible(true);
                if(values[i].equals("N/A")){
                    fields[i].set(shStockeXchangeObject, BigDecimal.ZERO);
                }else{
                    fields[i].set(shStockeXchangeObject, BigDecimal.valueOf(Double.valueOf(values[i])));
                }
             }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return shStockeXchangeObject;
    }
}

 