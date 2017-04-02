package com.sun.permission.mapper;

import com.sun.permission.model.TPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    TPermission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    /**
     * 根据用户ID获得该用户的角色名集合
     * @param roleId
     * @return
     */
    List<String> getPermissionsName(Integer roleId);
}