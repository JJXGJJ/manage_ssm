package com.jjx.travel.service;

import com.jjx.travel.Product;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/10/28 16:32
 * @Version 1.0
 * @Describe
 */
public interface ProductService {

    List<Product> findAll(int page,int size);

    void save(Product product);


    Product update4q(String id);

    void update(Product product);
}
