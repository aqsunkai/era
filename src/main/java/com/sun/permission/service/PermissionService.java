package com.sun.permission.service;

import com.sun.permission.model.TRole;
import com.sun.permission.model.TUser;

import java.util.List;
import java.util.Set;

/**
 * Created by sun on 2017-4-2.
 */
public interface PermissionService {

    TUser findByUsername(String userName);
    Set<String> getRolesName(Integer userId);
    List<TRole> getRoleList(Integer userId);
    List<String> getPermissionsName(Integer roleId);
    int insertSelective(TUser user);
}
