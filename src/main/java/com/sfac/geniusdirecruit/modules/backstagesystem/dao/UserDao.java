package com.sfac.geniusdirecruit.modules.backstagesystem.dao;

import com.sfac.geniusdirecruit.modules.backstagesystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: yzs
 * @Date: 2020/12/31 16:19
 * 概要：
 * XXXXX
 */
@Repository
@Mapper
public interface UserDao {

    //用户名密码登录
    @Select("select user_name,user_pwd from user where user_name = #{userName} and user_pwd =#{userPwd}")
    User selectUserByUserNameAndPwd(String userName,String userPwd);
}
