package com.jjx.travel.service;

import com.jjx.travel.Permission;
import com.jjx.travel.Role;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/11/2 16:39
 * @Version 1.0
 * @Describe
 */
public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    List<Permission> findRoleByIdAndAllPermission(String id);

    void addRoleToUser(String roleId, String[] permissionIds);
}
