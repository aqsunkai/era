package com.sun.springBoot.service;

import com.sun.springBoot.model.User;

import java.util.Map;

public interface UserService {

    User selectByPrimaryKey(Integer id);

    void insertSelective(User user);

    void deleteByPrimaryKey(Integer id);

    void insertUsers(Map<String, Object> param);

    void test();
}
