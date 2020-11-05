package com.jjx.travel.service;

import com.jjx.travel.SysLog;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/11/3 16:36
 * @Version 1.0
 * @Describe
 */
public interface SysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll();
}
