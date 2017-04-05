package com.sun.permission.mapper;

import com.sun.permission.model.PermissionInit;

public interface PermissionInitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionInit record);

    int insertSelective(PermissionInit record);

    PermissionInit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionInit record);

    int updateByPrimaryKey(PermissionInit record);
}