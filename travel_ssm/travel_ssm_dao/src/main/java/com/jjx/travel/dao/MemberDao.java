package com.jjx.travel.dao;

import com.jjx.travel.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author JJ
 * @Date 2020/10/29 18:20
 * @Version 1.0
 * @Describe
 */
@Repository
public interface MemberDao {

    @Select("select * from member where id = #{id} ")
    Member findById(String id);

}
