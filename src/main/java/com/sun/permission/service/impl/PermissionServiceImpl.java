package com.sun.permission.service.impl;

import com.sun.permission.mapper.TPermissionMapper;
import com.sun.permission.mapper.TRoleMapper;
import com.sun.permission.mapper.TUserMapper;
import com.sun.permission.model.TRole;
import com.sun.permission.model.TUser;
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
    private TUserMapper tUserMapper;
    @Autowired
    private TRoleMapper tRoleMapper;
    @Autowired
    private TPermissionMapper tPermissionMapper;
    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    public TUser findByUsername(String userName){
        return tUserMapper.findByUsername(userName);
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
    public List<TRole> getRoleList(Integer userId){
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
    public  int insertSelective(TUser user){
        return tUserMapper.insertSelective(user);
    }
}
