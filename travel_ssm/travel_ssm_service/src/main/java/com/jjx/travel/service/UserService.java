package com.jjx.travel.service;

import com.jjx.travel.Role;
import com.jjx.travel.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/10/30 16:46
 * @Version 1.0
 * @Describe
 */
public interface UserService extends UserDetailsService {

    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findUserByIdAndAllRole(String id);

    void addRoleToUser(String userId, String[] roleIds);
}
