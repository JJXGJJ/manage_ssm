package com.jjx.travel.dao;

import com.jjx.travel.Permission;
import com.jjx.travel.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/10/31 15:45
 * @Version 1.0
 * @Describe
 */
public interface RoleDao {


    @Select("select * from role where id in(select roleid from users_role where userid=#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName" ,column = "roleName"),
            @Result(property = "roleDesc" ,column = "roleDesc"),
            @Result(property = "permissions" ,column = "id",javaType = List.class,many = @Many(
                    select = "com.jjx.travel.dao.PermissionDao.findPermissionByRoleId"
            )),
    })
    List<Role> findRolesByUserId(String id);


    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role (id,roleName,roleDesc) values(role_seq.nextVal,#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(
                    select = "com.jjx.travel.dao.PermissionDao.findByIdRoleId"
            )),
    })
    Role findById(String id);

    @Select("select * from permission where id not in(select permissionid from role_permission  where roleid=#{id})")
    List<Permission> findRoleByIdAndAllPermission(String id);

    @Insert("insert into role_permission(roleid,permissionId) values (#{roleid},#{permissionId})")
    void addRoleToUser(@Param("roleid") String roleId, @Param(("permissionId")) String permissionId);
}
