package com.sun.permission.service;

import com.sun.permission.model.Role;
import com.sun.permission.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by sun on 2017-4-2.
 */
public interface PermissionService {

    User findByUserEmail(String email);
    Set<String> getRolesName(Integer userId);
    List<Role> getRoleList(Integer userId);
    List<String> getPermissionsName(Integer roleId);
    int insertSelective(User user);
}
