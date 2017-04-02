package com.sun.permission.mapper;

import com.sun.permission.model.TRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(TRole record);

    int insertSelective(TRole record);

    TRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);

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
    List<TRole> getRoleList(Integer userId);
}