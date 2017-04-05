package com.sun.permission.mapper;

import com.sun.permission.model.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    /**
     * 根据用户ID获得该用户的角色名集合
     * @param roleId
     * @return
     */
    List<String> getPermissionsName(Integer roleId);
}