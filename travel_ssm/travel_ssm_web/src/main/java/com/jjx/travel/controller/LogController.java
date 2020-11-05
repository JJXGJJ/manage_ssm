package com.jjx.travel.controller;

import com.jjx.travel.SysLog;
import com.jjx.travel.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/11/3 16:40
 * @Version 1.0
 * @Describe
 */
@Controller
@RequestMapping("/sysLog")
public class LogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ModelAndView findAllLog(){
        List<SysLog> sysLogList = sysLogService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sysLogs",sysLogList);
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }

}
