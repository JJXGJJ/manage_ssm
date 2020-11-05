package com.jjx.travel.service;

import com.jjx.travel.Order;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/10/29 15:47
 * @Version 1.0
 * @Describe
 */
public interface OrderService {

    List<Order> findAll(int page,int size);

    Order findById(String id);

}
