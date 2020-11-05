package com.jjx.travel.dao;

import com.jjx.travel.Traveller;
import org.apache.ibatis.annotations.Select;

/**
 * @Author JJ
 * @Date 2020/10/29 18:36
 * @Version 1.0
 * @Describe
 */
public interface TravellerDao {

    @Select("select * from traveller where id in (select travellerid from order_traveller  where  orderid = #{id}) ")
    Traveller findByOrderId(String id);

}
