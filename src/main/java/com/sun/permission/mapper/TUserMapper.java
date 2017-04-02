package com.sun.permission.mapper;

import com.sun.permission.model.TUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    /**
     * 根据用户名获得用户
     * @param userName
     * @return
     */
    TUser findByUsername(String userName);
}