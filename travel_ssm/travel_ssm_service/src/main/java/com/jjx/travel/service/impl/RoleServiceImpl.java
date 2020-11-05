package com.jjx.travel.service.impl;

import com.jjx.travel.Permission;
import com.jjx.travel.Role;
import com.jjx.travel.dao.RoleDao;
import com.jjx.travel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/11/2 16:39
 * @Version 1.0
 * @Describe
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String id) {
        return roleDao.findRoleByIdAndAllPermission(id);
    }

    @Override
    public void addRoleToUser(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addRoleToUser(roleId,permissionId);
        }
    }
}
