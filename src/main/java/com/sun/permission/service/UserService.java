package com.sun.permission.service;

import com.sun.permission.model.User;

import java.util.Map;

public interface UserService {

    User selectByPrimaryKey(Integer id);
    User selectByPrimaryKey1(Integer id);

    void insertSelective(User user);

    void deleteByPrimaryKey(Integer id);

    //void insertUsers(Map<String, Object> param);

    void test();
}
