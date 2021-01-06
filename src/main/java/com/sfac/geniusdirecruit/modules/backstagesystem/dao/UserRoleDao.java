package com.sfac.geniusdirecruit.modules.backstagesystem.dao;

import com.sfac.geniusdirecruit.modules.backstagesystem.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:19
 * 概要：
 * XXXXX
 */
@Repository
@Mapper
public interface UserRoleDao {
    @Select("select * from user_role where user_id=#{userId}")
    List<UserRole> selectByUserId(Integer userId);

    @Delete("delete from user_role where user_id = #{userId}")
    void deleteByUserId(Integer userId);
}
