package com.jjx.travel.dao;

import com.jjx.travel.Role;
import com.jjx.travel.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/10/30 16:51
 * @Version 1.0
 * @Describe
 */
@Repository
public interface UserDao {

    @Select("select * from users where username=#{name}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(
                    select = "com.jjx.travel.dao.RoleDao.findRolesByUserId"
            ))

    })
    UserInfo findByName(String name);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(username,password,email,phoneNum,status) values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "roles",column = "id",many = @Many(
                    select = "com.jjx.travel.dao.RoleDao.findRolesByUserId"
            ))
    })
    UserInfo findById(String id);

    @Select("select * from role where id not in(select roleid from users_role  where userid=#{id})")
    List<Role> findUserByIdAndAllRole(String id);


    @Select("insert into users_role(userid,roleid) values(#{userid},#{roleid})")
    void addRoleToUser(@Param("userid") String userId, @Param("roleid")String roleId);
}
