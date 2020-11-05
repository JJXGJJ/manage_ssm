package com.jjx.travel.dao;

import com.jjx.travel.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/11/2 16:23
 * @Version 1.0
 * @Describe
 */
@Repository
public interface PermissionDao {

    @Select("select * from permission where id in(select permissionid from role_permission where roleid=#{roleId})")
    List<Permission> findPermissionByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission (id,permissionName,url) values(permission_seq.nextVal,#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission where id=#{id}")
    Permission findById(String id);

    @Select("select * from permission where id in (select permissionid from role_permission where roleid=#{roleId})")
    List<Permission> findByIdRoleId(String roleId);
}
