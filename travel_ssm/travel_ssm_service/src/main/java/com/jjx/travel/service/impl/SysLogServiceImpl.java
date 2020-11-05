package com.jjx.travel.service.impl;

import com.jjx.travel.SysLog;
import com.jjx.travel.dao.SysLogDao;
import com.jjx.travel.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/11/3 16:36
 * @Version 1.0
 * @Describe
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
