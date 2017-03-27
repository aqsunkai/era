package com.sun.springBoot.mapper;

import com.sun.springBoot.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface UserMapper {
    void deleteByPrimaryKey(Integer id);

    int insert(User record);

    void insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    void insertUsers(Map<String, Object> param);
}