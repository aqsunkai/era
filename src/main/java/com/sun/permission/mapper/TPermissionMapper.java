package com.sun.permission.mapper;

import com.sun.permission.model.TPermission;

public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    TPermission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);
}