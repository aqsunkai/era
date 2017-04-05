package com.sun.permission.mapper;

import com.sun.permission.model.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据用户ID获得该用户的角色名集合
     * @param userId
     * @return
     */
    List<String> getRolesName(Integer userId);

    /**
     * 根据用户ID获得该用户的角色集合
     * @param userId
     * @return
     */
    List<Role> getRoleList(Integer userId);
}