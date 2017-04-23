package com.sun.permission.service;

import com.sun.permission.model.User;
import com.sun.entity.Bootgrid;

import java.util.List;

public interface UserService {

    User selectByPrimaryKey(Integer id);
    User selectByPrimaryKey1(Integer id);

    void insertSelective(User user);

    void deleteByPrimaryKey(Integer id);

    //void insertUsers(Map<String, Object> param);

    void test();
    //查询用户列表
    List<User> queryList(Bootgrid bootgrid);
    int countAll(Bootgrid bootgrid);
}
