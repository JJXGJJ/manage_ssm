package com.jjx.travel.dao;

import com.jjx.travel.Member;
import com.jjx.travel.Order;
import com.jjx.travel.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author JJ
 * @Date 2020/10/29 15:48
 * @Version 1.0
 * @Describe
 */
@Repository
public interface OrderDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "product",column = "PRODUCTID",javaType = Product.class,one = @One(
                    select = "com.jjx.travel.dao.ProductDao.findById"
            ))
    })
    List<Order> findAll();

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "product",column = "PRODUCTID",javaType = Product.class,one = @One(
                    select = "com.jjx.travel.dao.ProductDao.findById"
            )),
            @Result(property = "member",column = "MEMBERID",javaType = Member.class,one = @One(
                    select = "com.jjx.travel.dao.MemberDao.findById"
            )),
            @Result(property = "travellers",column = "id",many = @Many(
                    select = "com.jjx.travel.dao.TravellerDao.findByOrderId"
            ))
    })
    Order findById(String id);

}
