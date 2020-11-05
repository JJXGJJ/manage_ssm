package com.jjx.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.jjx.travel.Order;
import com.jjx.travel.dao.OrderDao;
import com.jjx.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/10/29 15:47
 * @Version 1.0
 * @Describe
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }

    @Override
    public Order findById(String id) {
        return orderDao.findById(id);
    }
}
