package com.jjx.travel.controller;

import com.jjx.travel.Permission;
import com.jjx.travel.Role;
import com.jjx.travel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/11/2 16:36
 * @Version 1.0
 * @Describe
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<Role> roles = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList",roles);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        Role roles = roleService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role",roles);
        modelAndView.setViewName("role-show");
        return modelAndView;
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id){
        List<Permission> permissionList = roleService.findRoleByIdAndAllPermission(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleId",id);
        modelAndView.addObject("permissionList",permissionList);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    @RequestMapping("addPermissionToRole")
    public String addPermissionToRole(@RequestParam(value = "roleId",required = true) String roleId, @RequestParam(value = "ids",required = true) String[] permissionIds){
        roleService.addRoleToUser(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
