package com.jjx.travel.controller;

import com.jjx.travel.Role;
import com.jjx.travel.UserInfo;
import com.jjx.travel.service.UserService;
import com.jjx.travel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author JJ
 * @Date 2020/10/31 16:42
 * @Version 1.0
 * @Describe
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("findAll")
    public ModelAndView findAll(){
        List<UserInfo> userInfos =  userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userInfos);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("save")
    @PreAuthorize("authentication.principal.username=='jjx' or hasAnyRole('ROLR_ADMIN')")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("findById")
    public ModelAndView findById(String id){
        UserInfo user =  userService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    @RequestMapping("findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id){
        List<Role> roles =  userService.findUserByIdAndAllRole(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList",roles);
        modelAndView.addObject("userId",id);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    @RequestMapping("addRoleToUser")
    public String addRoleToUser(@RequestParam(value = "userId",required = true) String userId,@RequestParam(value = "ids",required = true) String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
