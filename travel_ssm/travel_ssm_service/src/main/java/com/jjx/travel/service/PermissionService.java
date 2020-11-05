package com.jjx.travel.service;

import com.jjx.travel.Permission;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/11/2 17:23
 * @Version 1.0
 * @Describe
 */
public interface PermissionService {
    List<Permission> findAll();

    void save(Permission permission);

    Permission findById(String id);
}
