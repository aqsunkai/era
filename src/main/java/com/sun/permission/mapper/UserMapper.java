package com.sun.permission.mapper;

import com.sun.permission.model.User;
import com.sun.entity.Bootgrid;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名获得用户
     * @param email
     * @return
     */
    User findByUserEmail(String email);
    //查询用户列表
    List<User> queryList(Bootgrid bootgrid);
    int countAll(Bootgrid bootgrid);
}