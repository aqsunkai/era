package com.sun.permission.service.impl;

import com.sun.permission.mapper.PermissionMapper;
import com.sun.permission.mapper.RoleMapper;
import com.sun.permission.mapper.UserMapper;
import com.sun.permission.model.Role;
import com.sun.permission.model.User;
import com.sun.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限
 * Created by sun on 2017-4-2.
 */
@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    private UserMapper tUserMapper;
    @Autowired
    private RoleMapper tRoleMapper;
    @Autowired
    private PermissionMapper tPermissionMapper;
    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    public User findByUserEmail(String userName){
        return tUserMapper.findByUserEmail(userName);
    }

    /**
     * 根据用户ID查询角色名集合
     * @param userId
     * @return
     */
    public Set<String> getRolesName(Integer userId){
        List<String> list = tRoleMapper.getRolesName(userId);
        Set<String> set = new HashSet<String>();
        for (String roleName : list) {
            set.add(roleName);
        }
        return set;
    }

    /**
     * 根据用户ID查询角色集合
     * @param userId
     * @return
     */
    public List<Role> getRoleList(Integer userId){
        return tRoleMapper.getRoleList(userId);
    }

    /**
     * 根据角色ID查询权限名称
     * @param roleId
     * @return
     */
    @Override
    public List<String> getPermissionsName(Integer roleId) {
        return tPermissionMapper.getPermissionsName(roleId);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    public  int insertSelective(User user){
        return tUserMapper.insertSelective(user);
    }
}
